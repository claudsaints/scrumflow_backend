package com.claudsaints.scrumflow.controllers;


import com.claudsaints.scrumflow.dto.project.ProjectDTO;
import com.claudsaints.scrumflow.dto.project.ProjectDataDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping("/{ownerEmail}")
    public ResponseEntity<Project> create(@RequestBody Project obj, @PathVariable String ownerEmail) {
        obj = service.create(obj, ownerEmail);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectDataDTO> findById(@PathVariable UUID id) {
        ProjectDataDTO obj = service.findByUuid(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> findMemberProjects(@RequestParam String email) {
        List<ProjectDTO> obj = service.findByMemberEmail(email);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Project> updateProjectHeader(@RequestBody ProjectDTO projectDTO) {
        Project updatedProject = service.updateTitleAndDescription(projectDTO);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }


    @PutMapping(value = "/{id}/{imageUrl}")
    public ResponseEntity<String> updateBackgroundImage(@PathVariable UUID id, String imageUrl) {
        service.updateBackgroundImage(id, imageUrl);

        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.service.deleteByUuid(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
