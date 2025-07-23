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

@Service
public class SprintService {
    @Autowired
    private SprintRepository repository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SectionService sectionService;

    public Sprint create(Long projectId, Long sectionId, CreateSprintDTO sprintDTO){

        Project project = projectService.findEntityById(projectId);

        Section section = sectionService.findById(sectionId);

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


    public List<Sprint> sprintsByProject(Long projectId){
        return repository.findAllByProjectId(projectId);
    }

    public Sprint update(Long id, CreateSprintDTO sprintDTO ){
        Sprint sprint = this.findById(id);

        sprint.setTitle( sprintDTO.title());
        sprint.setStart_date( sprintDTO.start());
        sprint.setEnd_date( sprintDTO.end());
        sprint.setGoal( sprintDTO.goal());

        return repository.save(sprint);
    }


    public void delete(Long id){
        repository.deleteById(id);
    }
}
