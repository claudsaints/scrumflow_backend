package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.ProjectList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListRepository extends JpaRepository<ProjectList, Long> {
    List<ProjectList> findAllBySectionId(Long sectionId);
}
