package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.dto.CreateUserDTO;
import com.claudsaints.scrumflow.dto.LoginUserDTO;
import com.claudsaints.scrumflow.dto.RecoveryJwtDTO;
import com.claudsaints.scrumflow.entities.Role;
import com.claudsaints.scrumflow.entities.User;
import com.claudsaints.scrumflow.repositories.RoleRepository;
import com.claudsaints.scrumflow.repositories.UserRepository;
import com.claudsaints.scrumflow.security.auth.JwtService;
import com.claudsaints.scrumflow.security.config.SecurityConfiguration;
import com.claudsaints.scrumflow.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtTokenService;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public void create(CreateUserDTO createUserDto){
        User newUser = new User();
        newUser.setEmail(createUserDto.email());
        newUser.setPassword(securityConfiguration.passwordEncoder().encode(createUserDto.password()));
        newUser.setName(createUserDto.name());

        Role role = new Role();
        role.setName(createUserDto.role());

        newUser.setRoles(List.of(role));


        User savedUser = repository.save(newUser);

        new CreateUserDTO(savedUser.getName(), savedUser.getEmail(), null, createUserDto.role());
    }

    public RecoveryJwtDTO read(LoginUserDTO loginUserDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtDTO(jwtTokenService.generateToken(userDetails));
    }

}
