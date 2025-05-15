package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.dto.ProjectDTO;
import com.claudsaints.scrumflow.dto.ProjectDataDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.repositories.ProjectRepository;
import com.claudsaints.scrumflow.security.auth.JwtService;
import com.claudsaints.scrumflow.security.auth.UserAuthFilter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private JwtService jwtService;

//    private UserAuthFilter filter = new UserAuthFilter();

    public Project create(Project obj){
        obj.setCreate_At(Instant.now());
        return  repository.save(obj);
    }

    public List<ProjectDataDTO> findAll(){
        return  repository.findAll().stream().map(ProjectDataDTO::new).toList();
    }

    public List<ProjectDTO> findByOwnerEmail(String email){
//        HttpServletRequest request;
//        String email = jwtService.getSubjectFromToken(filter.recoveryToken( request));
        return  repository.findByOwnerEmail(email);

    }

    public Project findById(Long id){

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
