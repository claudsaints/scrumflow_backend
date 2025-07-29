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
import java.util.UUID;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/project/lists")
public class ProjectListController {
    @Autowired
    private ProjectListService service;

    //todo
    @PostMapping("/{sectionId}")
    public ResponseEntity<ProjectList> create(@RequestBody CreateProjectListDTO listDTO, @PathVariable UUID sectionId) {
        ProjectList list = service.createList(sectionId, listDTO);

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProjectList> updatePosition(@RequestParam UUID sectionId, UUID listId, int newPos) {

        ProjectList list = service.updatePosition( listId,sectionId, newPos);

        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @PutMapping( value = "/{id}")
    public ResponseEntity<ProjectList> updateTitle(@PathVariable UUID listId, @RequestBody String title) {

        ProjectList list = service.updateTitle( listId, title);

        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id ){
        service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
