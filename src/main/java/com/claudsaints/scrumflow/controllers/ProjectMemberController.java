package com.claudsaints.scrumflow.controllers;

import com.claudsaints.scrumflow.dto.projectMember.ProjectMemberDTO;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.entities.enums.ProjectMemberRole;
import com.claudsaints.scrumflow.services.ProjectMembersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/project/members")
public class ProjectMemberController {
    @Autowired
    ProjectMembersService service;

    @PostMapping
    public ResponseEntity<ProjectMembers> addMember(@RequestBody ProjectMembers member) {
        member = service.addMember(member);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{projectId}")
    public ResponseEntity<List<ProjectMembers>> findAllByProject(@PathVariable Long projectId) {
        List<ProjectMembers> members = service.findAllByProjectId(projectId);

        return new ResponseEntity<>(members, HttpStatus.OK);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ProjectMemberDTO> updateMemberRole(@PathVariable Long id, @RequestParam ProjectMemberRole newRole) {
        ProjectMemberDTO member = service.updateRole(id, newRole);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{memberId}")
    public ResponseEntity<Void> delete(@PathVariable Long memberId) {

        service.removeMember(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
