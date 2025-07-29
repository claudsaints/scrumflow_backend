package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.section.CreateSectionDTO;
import com.claudsaints.scrumflow.entities.Section;
import com.claudsaints.scrumflow.repositories.SectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    @Autowired
    private ProjectService projectService;


    public Section create(CreateSectionDTO sectionDTO, UUID projectId) {

        Section newSection = Section.builder()
                .title(sectionDTO.title())
                .description(sectionDTO.description())
                .project(projectService.findEntityByUuid(projectId))
                .build();

        return repository.save(newSection);
    }


    public Section findByUuid(UUID id) {
        return repository.findByUuid(id).orElseThrow(() -> new ObjectNotFound("Section Not Found"));
    }

    @Transactional
    public List<Section> findAll(UUID projectId) {
        return repository.findAllByProjectUuid(projectId);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteByUuid(id);
    }


}
