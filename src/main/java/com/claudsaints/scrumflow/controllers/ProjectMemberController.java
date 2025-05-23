package com.claudsaints.scrumflow.controllers;

import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.services.ProjectMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/members")
public class ProjectMemberController {
    @Autowired
    ProjectMembersService service;

    @PostMapping
    public ResponseEntity<ProjectMembers> addMember(@RequestBody ProjectMembers member){
        member = service.addMember(member);
        return new ResponseEntity<>(member , HttpStatus.CREATED);
    }
}
