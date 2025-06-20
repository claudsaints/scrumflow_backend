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
import java.util.List;

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

    public Project create(Project obj, String userEmail){
        User owner = userService.findByUserEmail(userEmail);

        obj.setCreate_At(Instant.now());

        obj.setOwner(owner);

        var saveProject = repository.save(obj);

        membersService.addMember(new ProjectMembers(owner, saveProject, ProjectMemberRole.ADMIN, Instant.now()));

        return  saveProject;

    }

    public List<ProjectDataDTO> findAll(){
        return  repository.findAll().stream().map(ProjectDataDTO::new).toList();
    }

    public List<ProjectDTO> findByOwnerEmail(String email){
        return  repository.findByOwnerEmail(email);
    }

    public List<ProjectDTO> findByMemberEmail(String email){
        return  repository.findByMembersIdUserEmail(email);
    }

    public ProjectDataDTO findById(Long id){
        return new ProjectDataDTO(this.findEntityById(id));
    }

    public Project findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("Projeto não encontrado"));
    }

//    public Project update(Project obj){
//
//    }
//
//    public void delete(){
//
//    }

}
