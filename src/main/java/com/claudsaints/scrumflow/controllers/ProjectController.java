package com.claudsaints.scrumflow.controllers;


import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
