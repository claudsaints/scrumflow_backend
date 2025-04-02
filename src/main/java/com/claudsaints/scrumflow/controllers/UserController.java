package com.claudsaints.scrumflow.controllers;
import com.claudsaints.scrumflow.dto.CreateUserDTO;
import com.claudsaints.scrumflow.dto.LoginUserDTO;
import com.claudsaints.scrumflow.dto.RecoveryJwtDTO;
import com.claudsaints.scrumflow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtDTO> authenticateUser(@RequestBody LoginUserDTO loginUserDto) {
        RecoveryJwtDTO token = service.read(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDto) {
        service.create(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return ResponseEntity.ok().body("Autenticado");
    }

    @GetMapping("/test/customer")
    public ResponseEntity<String> getCustomerAuthenticationTest() {
        return ResponseEntity.ok().body("Customer Autenticado com Sucesso");
    }

    @GetMapping("/test/administrator")
    public ResponseEntity<String> getAdminAuthenticationTest() {
        return ResponseEntity.ok().body("Administrador autenticado com sucesso");
    }
}
