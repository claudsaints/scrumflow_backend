package com.claudsaints.scrumflow.controllers;
import com.claudsaints.scrumflow.dto.sprint.CreateSprintDTO;
import com.claudsaints.scrumflow.entities.Sprint;
import com.claudsaints.scrumflow.services.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/project/sprints")
public class SprintController {
    @Autowired
    private SprintService service;

    @PostMapping
    public ResponseEntity<Sprint> create(@RequestParam Long projectId, Long sectionId, @RequestBody CreateSprintDTO sprintDTO){
        Sprint sprint = service.create(projectId,sectionId, sprintDTO);

        return new ResponseEntity<>(sprint, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public  ResponseEntity<Sprint> findById(@PathVariable Long id){
        Sprint sprint = service.findById(id);

        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Sprint>> findAllProjectSprints(@RequestParam Long projectId){
        List<Sprint> sprints = service.sprintsByProject(projectId);

        return  new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public  ResponseEntity<Sprint> update(@PathVariable Long id , @RequestBody CreateSprintDTO sprintDTO){
        Sprint sprint = service.update(id,sprintDTO);

        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
