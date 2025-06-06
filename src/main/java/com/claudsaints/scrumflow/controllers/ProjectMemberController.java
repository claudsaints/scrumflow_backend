package com.claudsaints.scrumflow.controllers;

import com.claudsaints.scrumflow.dto.ProjectMemberDTO;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.entities.enums.ProjectMemberRole;
import com.claudsaints.scrumflow.services.ProjectMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/{id}")
    public  ResponseEntity<ProjectMemberDTO> updateMemberRole(@PathVariable Long id, @RequestParam ProjectMemberRole newRole){
        ProjectMemberDTO member = service.updateRole(id,newRole);
        return new ResponseEntity<>( member, HttpStatus.OK);
    }

}
