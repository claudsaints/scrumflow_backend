package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.sprint.CreateSprintDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.Section;
import com.claudsaints.scrumflow.entities.Sprint;
import com.claudsaints.scrumflow.repositories.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SprintService {
    @Autowired
    private SprintRepository repository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SectionService sectionService;

    public Sprint create(UUID projectId, UUID sectionId, CreateSprintDTO sprintDTO){

        Project project = projectService.findEntityByUuid(projectId);

        Section section = sectionService.findByUuid(sectionId);

        Sprint sprint = Sprint.builder()
                .project(project)
                .section(section)
                .title(sprintDTO.title())
                .start_date(sprintDTO.start())
                .end_date(sprintDTO.end())
                .goal(sprintDTO.goal())
                .build();

        return repository.save(sprint);

    }



    public Sprint findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ObjectNotFound("Sprint Not Found"));
    }

    public Sprint findByUuid(UUID uuid){
        return repository.findByUuid(uuid).orElseThrow(() -> new ObjectNotFound("Sprint not found"));
    }

    public List<Sprint> sprintsByProject(UUID projectId){
        return repository.findAllByProjectUuid(projectId);
    }

    public Sprint update(UUID id, CreateSprintDTO sprintDTO ){
        Sprint sprint = this.findByUuid(id);

        sprint.setTitle( sprintDTO.title());
        sprint.setStart_date( sprintDTO.start());
        sprint.setEnd_date( sprintDTO.end());
        sprint.setGoal( sprintDTO.goal());

        return repository.save(sprint);
    }


    public void delete(UUID uuid){
        repository.deleteByUuid(uuid);
    }
}
