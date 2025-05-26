package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.dto.project.ProjectDTO;
import com.claudsaints.scrumflow.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository  extends JpaRepository<Project,Long> {
    List<ProjectDTO> findByOwnerEmail(String email);
    List<ProjectDTO> findByMembersIdUserEmail(String email);
}
