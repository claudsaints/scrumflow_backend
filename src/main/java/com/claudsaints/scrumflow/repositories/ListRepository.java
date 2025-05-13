package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.ProjectList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ProjectList,Long> {
}
