package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.entities.Section;
import com.claudsaints.scrumflow.repositories.SectionRepository;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    //TODO: criar dto para section
    public Section create(Section section){
        return repository.save(section);
    }

    public Section findById(Long id){
       return repository.findById(id).orElseThrow(() -> new ObjectNotFound("Section Not Found"));
    }

    public void delete(Long id){
        repository.deleteById(id);
    }




}
