package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.repositories.ProjectRepository;
import com.claudsaints.scrumflow.security.auth.JwtService;
import com.claudsaints.scrumflow.security.auth.UserAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;


    public Project create(Project obj){
        obj.setCreate_At(Instant.now());
        return  repository.save(obj);
    }

}
