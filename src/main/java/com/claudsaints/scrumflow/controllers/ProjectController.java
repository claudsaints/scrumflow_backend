package com.claudsaints.scrumflow.controllers;


import com.claudsaints.scrumflow.dto.ProjectDTO;
import com.claudsaints.scrumflow.dto.ProjectDataDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.services.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project obj){
        obj = service.create(obj);
        return  ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id){
        Project obj = service.findById(id);
        return new ResponseEntity<>(obj,HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDataDTO>> findAll(){
        List<ProjectDataDTO> obj = service.findAll();
        return  new ResponseEntity<>( obj,HttpStatus.OK);
    }

    @GetMapping(value = "/owner/")
    public ResponseEntity<List<ProjectDTO>> findUserProjects(@RequestParam String email){
        List<ProjectDTO> obj = service.findByOwnerEmail(email);
        return  new ResponseEntity<>( obj ,HttpStatus.OK);
    }

}
