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
@RequestMapping("/project/lists")
public class ProjectListController {
    @Autowired
    private ProjectListService service;


    @PostMapping("/{sectionId}")
    public ResponseEntity<ProjectList> create(@RequestBody CreateProjectListDTO listDTO, @PathVariable Long sectionId) {
        ProjectList list = service.createList(sectionId, listDTO);

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProjectList> update(@RequestParam Long sectionId, Long listId, int newPos) {

        ProjectList list = service.updatePosition( listId,sectionId, newPos);

        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id ){
        service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
