package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.projectMember.ProjectMemberDTO;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.entities.enums.ProjectMemberRole;
import com.claudsaints.scrumflow.repositories.ProjectMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMembersService {

    @Autowired
    private ProjectMembersRepository repository;


    public ProjectMembers addMember(ProjectMembers member) {
        return repository.save(member);
    }


    public ProjectMembers findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFound("Project Member not found"));
    }

    public List<ProjectMembers> findAllByProjectId(Long projectId) {
        return repository.findAllByIdProjectId(projectId);
    }

    public ProjectMemberDTO updateRole(Long id, ProjectMemberRole newRole) {
        ProjectMembers member = findById(id);

        member.setRole(newRole);

        repository.save(member);

        return new ProjectMemberDTO(member);
    }

    public void removeMember(Long id) {
        repository.deleteById(id);
    }
}
