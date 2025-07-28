package com.claudsaints.scrumflow.controllers;
import com.claudsaints.scrumflow.dto.section.CreateSectionDTO;
import com.claudsaints.scrumflow.entities.Section;
import com.claudsaints.scrumflow.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/project/sections")
public class SectionController {

    @Autowired
    private SectionService service;

    @PostMapping(value = "/{projectId}")
    public ResponseEntity<Section> create(@RequestBody CreateSectionDTO sectionDTO, @PathVariable UUID projectId){
        var section = service.create(sectionDTO,projectId);

        return new ResponseEntity<>(section,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Section> findById(@PathVariable UUID id){
        Section section = service.findByUuid(id);

        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Section>> findAllByProject(@RequestParam UUID projectId){
       List<Section> sections =  service.findAll(projectId);

       return new ResponseEntity<>(sections, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
