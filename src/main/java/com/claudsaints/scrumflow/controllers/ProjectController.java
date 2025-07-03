package com.claudsaints.scrumflow.controllers;


import com.claudsaints.scrumflow.dto.project.ProjectDTO;
import com.claudsaints.scrumflow.dto.project.ProjectDataDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.services.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = "/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping("/{ownerEmail}")
    public ResponseEntity<Project> create(@RequestBody Project obj, @PathVariable String ownerEmail){
        obj = service.create(obj, ownerEmail);
        return  ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectDataDTO> findById(@PathVariable Long id){
        ProjectDataDTO obj = service.findById(id);
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

    @GetMapping(value = "/members/")
    public ResponseEntity<List<ProjectDTO>> findMemberProjects(@RequestParam String email){
        List<ProjectDTO> obj = service.findByMemberEmail(email);
        return  new ResponseEntity<>( obj ,HttpStatus.OK);
    }

}
