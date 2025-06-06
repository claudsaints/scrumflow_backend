package com.claudsaints.scrumflow.services;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private RoleRepository roleRepository;
    private UserRepository repository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtTokenService;
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
        Optional<User> optionalUser = repository.findByEmail(loginUserDto.email());

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + loginUserDto.email());
        }

        User user = optionalUser.get();

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

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<User> findAll(){
        return repository.findAll();
    }


}
