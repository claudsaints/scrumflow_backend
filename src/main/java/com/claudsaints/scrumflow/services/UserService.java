package com.claudsaints.scrumflow.services;
import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.user.CreateUserDTO;
import com.claudsaints.scrumflow.dto.user.LoginUserDTO;
import com.claudsaints.scrumflow.dto.RecoveryJwtDTO;
import com.claudsaints.scrumflow.entities.Role;
import com.claudsaints.scrumflow.entities.User;
import com.claudsaints.scrumflow.repositories.RoleRepository;
import com.claudsaints.scrumflow.repositories.UserRepository;
import com.claudsaints.scrumflow.security.auth.JwtService;
import com.claudsaints.scrumflow.security.config.SecurityConfiguration;
import com.claudsaints.scrumflow.security.details.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final RoleRepository roleRepository;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtTokenService;
    private final SecurityConfiguration securityConfiguration;

    public UserService(RoleRepository roleRepository, UserRepository repository, AuthenticationManager authenticationManager, JwtService jwtTokenService, SecurityConfiguration securityConfiguration) {
        this.roleRepository = roleRepository;
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.securityConfiguration = securityConfiguration;
    }

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
        User user = this.findByUserEmail(loginUserDto.email());

        if (!securityConfiguration.passwordEncoder().matches(loginUserDto.password(), user.getPassword())) {
            throw new BadCredentialsException("Senha inválida.");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        String token = jwtTokenService.generateToken(userDetails);

        return new RecoveryJwtDTO(token);
    }
    public User update(Long id, CreateUserDTO user){
       try{
           System.out.println("User" + user + id);
            User entity = repository.getReferenceById(id);

            updateData(entity,user);

            return repository.save(entity);
        }catch (RuntimeException e){
            throw new RuntimeException("Resource Not found");

        }
    }
    public void updateData(User entity, CreateUserDTO obj){
        entity.setName(obj.name());
        entity.setEmail(obj.email());
        entity.setPassword(securityConfiguration.passwordEncoder().encode(obj.password()));
    }

    public User findByUserEmail(String email){
        return  repository.findByEmail(email).orElseThrow(() -> new ObjectNotFound("usuário não encontrado"));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<User> findAll(){
        return repository.findAll();
    }


}
