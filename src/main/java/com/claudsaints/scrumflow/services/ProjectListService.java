package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.dto.projectList.CreateProjectListDTO;
import com.claudsaints.scrumflow.entities.ProjectList;
import com.claudsaints.scrumflow.entities.Section;
import com.claudsaints.scrumflow.repositories.ListRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class ProjectListService {

    @Autowired
    private ListRepository repository;

    @Autowired
    private SectionService sectionService;


    public ProjectList createList(UUID sectionId, CreateProjectListDTO list) {

        Section section = sectionService.findByUuid(sectionId);

        int newPosition = repository.findTopBySectionUuidOrderByPositionDesc(sectionId).map(l -> l.getPosition() + 1).orElse(0);

        ProjectList list1 = new ProjectList(null, section, list.getTitle(), newPosition, Instant.now());

        return repository.save(list1);
    }

    @Transactional
    public ProjectList updatePosition(UUID listId, UUID sectionId, int newPos) {
        ProjectList targetList = this.findByUuid(listId);

        Section section = sectionService.findByUuid(sectionId);

        Optional<ProjectList> listAlreadyExist = section.getLists().stream()
                .filter(e -> e.getPosition() == newPos).findFirst();

        if (listAlreadyExist.isPresent()) {
            listAlreadyExist.get().setPosition(targetList.getPosition());
            targetList.setPosition(newPos);
            repository.saveAll(Arrays.asList(listAlreadyExist.get(), targetList));
            return targetList;
        }

        targetList.setPosition(newPos);
        return repository.save(targetList);

    }

    @Transactional
    public ProjectList updateTitle(UUID listId, String title) {
        ProjectList projectList = findByUuid(listId);

        projectList.setTitle(title);

        return repository.save(projectList);
    }

    public ProjectList findByUuid(UUID id) {
        return repository.findByUuid(id)
                .orElseThrow(() -> new EntityNotFoundException("List not found"));

    }

    @Transactional
    public void deleteById(UUID listId) {
        repository.deleteByUuid(listId);
    }


}
