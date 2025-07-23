package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Set;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findAllByProjectId(Long id);
}
