package com.claudsaints.scrumflow.security.auth;
import com.claudsaints.scrumflow.entities.User;
import com.claudsaints.scrumflow.repositories.UserRepository;
import com.claudsaints.scrumflow.security.config.SecurityConfiguration;
import com.claudsaints.scrumflow.security.details.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class UserAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtTokenService;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();


        if (requestURI.startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }


        if (checkIfEndpointIsNotPublic(request)) {
            String token = recoveryToken(request);
            if (token != null) {
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
                    throw new RuntimeException("Usuário não encontrado.");
                }
            } else {
                throw new RuntimeException("O token está ausente.");
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
        boolean x = !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
        System.out.println("BOOLEANO: " + x);
        return x;
    }

}


