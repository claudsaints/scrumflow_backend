package com.claudsaints.scrumflow.controllers;

import com.claudsaints.scrumflow.dto.auth.RecoveryDataLogin;
import com.claudsaints.scrumflow.dto.user.CreateUserDTO;
import com.claudsaints.scrumflow.dto.user.LoginUserDTO;
import com.claudsaints.scrumflow.dto.auth.RecoveryUserDTO;
import com.claudsaints.scrumflow.entities.User;
import com.claudsaints.scrumflow.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<RecoveryDataLogin> authenticateUser(@RequestBody LoginUserDTO loginUserDto) {
        RecoveryDataLogin data = service.read(loginUserDto);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserDTO createUserDto) {
        service.create(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody CreateUserDTO obj, @PathVariable Long id) {
        User usr = service.update(id, obj);
        return ResponseEntity.ok().body(usr);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

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
    public ResponseEntity<List<User>> getAdminAuthenticationTest() {
        List<User> users = service.findAll();

        return ResponseEntity.ok().body(users);
    }
}
