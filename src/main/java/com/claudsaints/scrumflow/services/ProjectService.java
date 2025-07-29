package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.project.ProjectDTO;
import com.claudsaints.scrumflow.dto.project.ProjectDataDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.entities.User;
import com.claudsaints.scrumflow.entities.enums.ProjectMemberRole;
import com.claudsaints.scrumflow.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectMembersService membersService;


    public Project create(Project obj, String userEmail) {
        User owner = userService.findByUserEmail(userEmail);

        obj.setCreate_At(Instant.now());

        obj.setOwner(owner);

        var saveProject = repository.save(obj);

        membersService.addMember(new ProjectMembers(owner, saveProject, ProjectMemberRole.ADMIN, Instant.now()));

        return saveProject;

    }

    public List<ProjectDTO> findByMemberEmail(String email) {
        List<Project> projects = repository.findByMembersIdUserEmail(email);

        return new ArrayList<>(projects.stream().map(ProjectDTO::new).toList());

    }

    public ProjectDataDTO findByUuid(UUID id) {
        return new ProjectDataDTO(this.findEntityByUuid(id));
    }


    public Project findEntityByUuid(UUID id) {
        return repository.findByUuid(id).orElseThrow(() -> new ObjectNotFound("Project not found"));
    }

    @Transactional
    public Project updateTitleAndDescription(ProjectDTO obj) {
        Project oldProject = this.findEntityByUuid(obj.getUuid());

        oldProject.setTitle(obj.getTitle());
        oldProject.setDescription(obj.getDescription());

        return repository.save(oldProject);
    }

    @Transactional
    public void updateBackgroundImage(UUID projectId, String imageUrl) {
        Project project = this.findEntityByUuid(projectId);

        project.setBackgroundImage(imageUrl);
    }

    @Transactional
    public void deleteByUuid(UUID id) {
        repository.deleteByUuid(id);
    }

    public void delete(Long projectId) {
        repository.deleteById(projectId);
    }
}
