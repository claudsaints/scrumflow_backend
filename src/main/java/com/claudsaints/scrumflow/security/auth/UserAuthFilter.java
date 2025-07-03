package com.claudsaints.scrumflow.security.auth;
import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.controllers.exceptions.TokenInvalid;
import com.claudsaints.scrumflow.entities.User;
import com.claudsaints.scrumflow.repositories.UserRepository;
import com.claudsaints.scrumflow.security.config.SecurityConfiguration;
import com.claudsaints.scrumflow.security.details.UserDetailsImpl;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class UserAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;


    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain) throws ServletException, IOException
    {
        String requestURI = request.getRequestURI();

        System.out.println(requestURI);

        if (requestURI.startsWith("/h2-console") || requestURI.startsWith("/favicon.ico") ||  requestURI.contains("/swagger") || requestURI.contains("/v3/api-docs"))  {
            filterChain.doFilter(request, response);
            return;
        }


        if (!checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);
            if (token != null) {
                try {
                    String subject = jwtTokenService.getSubjectFromToken(token);

                    Optional<User> optionalUser = userRepository.findByEmail(subject);

                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        UserDetailsImpl userDetails = new UserDetailsImpl(user);
                        Authentication authentication = new UsernamePasswordAuthenticationToken(
                                userDetails.getUsername(), null, userDetails.getAuthorities()
                        );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        resolver.resolveException(request, response, null, new ObjectNotFound("User not found"));
                        throw new RuntimeException("ONão autorizado.");
                    }
                }catch (TokenInvalid e){
                    resolver.resolveException(request, response, null, e);
                    throw new RuntimeException("O token está inválido.");
                }
            } else {
                resolver.resolveException(request, response, null, new TokenInvalid("O token está ausente"));
                throw new RuntimeException("O token está ausente." + requestURI);
            }
        }

        filterChain.doFilter(request, response);
    }
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        boolean x = Arrays.asList(SecurityConfiguration.PUBLIC_ENDPOINTS).contains(requestURI);
        System.out.println("BOOLEANO: " + x);
        return x;
    }

}


