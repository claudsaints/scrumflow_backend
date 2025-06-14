package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.projectList.CreateProjectListDTO;
import com.claudsaints.scrumflow.dto.projectList.ProjectListDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.ProjectList;
import com.claudsaints.scrumflow.repositories.ListRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service

public class ProjectListService {

    private final ListRepository repository;
    private final ProjectService projectService;

    public ProjectListService(ListRepository repository, ProjectService projectService) {
        this.repository = repository;
        this.projectService = projectService;
    }

    public ProjectList createList(Long projectId, CreateProjectListDTO list) {

        Project project = projectService.findEntityById(projectId);

        ProjectList list1 = new ProjectList( null,project, list.getTitle(), list.getPosition(), Instant.now());

        return repository.save(list1);
    }
    public ProjectList updatePosition(Long id, int newPos){

        ProjectList list = findById(id);

        list.setPosition(newPos);

        return repository.save(list);
    }

    public ProjectList findById(Long id){
        return  repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("List not found"));
    }
}
