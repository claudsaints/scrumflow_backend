package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.dto.projectList.CreateProjectListDTO;
import com.claudsaints.scrumflow.dto.projectList.ProjectListDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.ProjectList;
import com.claudsaints.scrumflow.repositories.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ProjectListService {

    @Autowired
    private ListRepository repository;

    @Autowired
    private ProjectService projectService;

    public ProjectList createList(Long projectId, CreateProjectListDTO list) {

        Project project = projectService.findEntityById(projectId);

        ProjectList list1 = new ProjectList( null,project, list.getTitle(), list.getPosition(), Instant.now());

        return repository.save(list1);
    }
}
