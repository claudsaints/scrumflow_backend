package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.project.ProjectDTO;
import com.claudsaints.scrumflow.dto.project.ProjectDataDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.entities.User;
import com.claudsaints.scrumflow.entities.enums.ProjectMemberRole;
import com.claudsaints.scrumflow.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {


    private final ProjectRepository repository;

    private final UserService userService;

    private final ProjectMembersService membersService;

    public ProjectService(ProjectRepository repository, UserService userService, ProjectMembersService membersService) {
        this.repository = repository;
        this.userService = userService;
        this.membersService = membersService;
    }

    public Project create(Project obj, String userEmail) {
        User owner = userService.findByUserEmail(userEmail);

        obj.setCreate_At(Instant.now());

        obj.setOwner(owner);

        var saveProject = repository.save(obj);

        membersService.addMember(new ProjectMembers(owner, saveProject, ProjectMemberRole.ADMIN, Instant.now()));

        return saveProject;

    }

    public List<ProjectDTO> findByMemberEmail(String email) {
        try {
            List<Project> projects = repository.findByMembersIdUserEmail(email);

            List<ProjectDTO> projectDTOList = new ArrayList<>(projects.stream().map(p -> new ProjectDTO(p)).toList());

            return projectDTOList;

        } catch (RuntimeException e) {
            throw e;
        }
    }

    public ProjectDataDTO findById(Long id) {
        return new ProjectDataDTO(this.findEntityById(id));
    }

    public ProjectDataDTO findByUuid(UUID id) {return new ProjectDataDTO(this.findEntityByUuid(id));}


    public Project findEntityByUuid(UUID id){
        return repository.findByUuid(id).orElseThrow(() -> new ObjectNotFound("Project not found"));
    }

    public Project findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Project not found"));
    }

    public Project updateTitleAndDescription(ProjectDTO obj) {
        Project oldProject = this.findEntityByUuid(obj.getId());

        oldProject.setTitle(obj.getTitle());
        oldProject.setDescription(obj.getDescription());

        return repository.save(oldProject);
    }

    public void updateBackgroundImage(UUID projectId, String imageUrl) {
        Project project = this.findEntityByUuid(projectId);

        project.setBackgroundImage(imageUrl);
    }

    public void deleteByUuid(UUID id){
        repository.deleteByUuid(id);

    }
    public void delete(Long projectId) {
        repository.deleteById(projectId);
    }
}
