package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository  extends JpaRepository<Project,Long> {
}
