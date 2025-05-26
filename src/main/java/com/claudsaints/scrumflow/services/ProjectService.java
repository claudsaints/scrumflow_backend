package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.dto.project.ProjectDTO;
import com.claudsaints.scrumflow.dto.project.ProjectDataDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public Project create(Project obj){
        obj.setCreate_At(Instant.now());
        return  repository.save(obj);
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
        Project project =  repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado"));
        return new ProjectDataDTO(project);
    }

    public Project findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado"));
    }

//    public Project update(Project obj){
//
//    }
//
//    public void delete(){
//
//    }

}
