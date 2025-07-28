package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.projectList.CreateProjectListDTO;
import com.claudsaints.scrumflow.dto.projectList.ProjectListDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.ProjectList;
import com.claudsaints.scrumflow.entities.Section;
import com.claudsaints.scrumflow.repositories.ListRepository;
import com.claudsaints.scrumflow.repositories.SectionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectListService {

    private final ListRepository repository;

    @Autowired
    private SectionService sectionService;

    public ProjectListService(ListRepository repository) {
        this.repository = repository;
    }


    public ProjectList createList(Long sectionId, CreateProjectListDTO list) {

        Section section = sectionService.findById(sectionId);

        int newPosition = repository.findTopBySectionIdOrderByPositionDesc(sectionId).map( l -> l.getPosition() + 1 ).orElse(0);

        ProjectList list1 = new ProjectList(null, section, list.getTitle(), newPosition, Instant.now());

        return repository.save(list1);
    }

    public ProjectList updatePosition(Long listId, Long sectionId, int newPos) {
        ProjectList targetList = this.findById(listId);

        Section section = sectionService.findById(sectionId);

        Optional<ProjectList> listAlreadyExist = section.getLists().stream()
                .filter(e -> e.getPosition() == newPos).findFirst();

        if (!listAlreadyExist.isEmpty()) {
            listAlreadyExist.get().setPosition(targetList.getPosition());
            targetList.setPosition(newPos);
            repository.saveAll(Arrays.asList(listAlreadyExist.get(), targetList));
            return targetList;
        }

        targetList.setPosition(newPos);
        return repository.save(targetList);

    }

    public ProjectList updateTitle(Long listId, String title){
        ProjectList projectList = findById(listId);

        projectList.setTitle(title);

        return repository.save(projectList);
    }


    public ProjectList findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFound("List not found"));
    }

    public void deleteById(Long listId){
        repository.deleteById(listId);
    }


}
