package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMembersRepository extends JpaRepository<ProjectMembers, Long> {
    List<ProjectMembers> findAllByIdProjectId(Long id);
}
