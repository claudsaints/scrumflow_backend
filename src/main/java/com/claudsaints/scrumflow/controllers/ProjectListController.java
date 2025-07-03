package com.claudsaints.scrumflow.controllers;


import com.claudsaints.scrumflow.dto.projectList.CreateProjectListDTO;
import com.claudsaints.scrumflow.entities.ProjectList;
import com.claudsaints.scrumflow.services.ProjectListService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/projects/lists")
public class ProjectListController {
    @Autowired
    private ProjectListService service;

    @GetMapping
    public ResponseEntity<List<ProjectList>> findAll(@RequestParam Long projectId){
        List<ProjectList> projectLists = service.findAll(projectId);
        return new ResponseEntity<>(projectLists, HttpStatus.FOUND);
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<ProjectList> create(@RequestBody CreateProjectListDTO listDTO, @PathVariable Long projectId){
        ProjectList list = service.createList(projectId,listDTO);

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestParam Long projectId, Long listId, int newPos){

        service.updatePosition(projectId, listId, newPos);

        return new ResponseEntity<>( "Posição da lista alterada com sucesso", HttpStatus.ACCEPTED);
    }




}
