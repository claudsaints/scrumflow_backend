package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.dto.ProjectDTO;
import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository  extends JpaRepository<Project,Long> {
    List<ProjectDTO> findByOwnerEmail(String email);
    List<ProjectDTO> findByMembersIdUserEmail(String email);
}
