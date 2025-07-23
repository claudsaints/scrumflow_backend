package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.section.CreateSectionDTO;
import com.claudsaints.scrumflow.entities.Section;
import com.claudsaints.scrumflow.repositories.SectionRepository;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    @Autowired
    private ProjectService projectService;


    public Section create(CreateSectionDTO sectionDTO, Long projectId) {

        Section newSection = Section.builder()
                .title(sectionDTO.title())
                .description(sectionDTO.description())
                .project(projectService.findEntityById(projectId))
                .build();

        return repository.save(newSection);
    }

    public Section findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFound("Section Not Found"));
    }

    public List<Section> findAll(Long projectId) {
        return repository.findAllByProjectId(projectId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
