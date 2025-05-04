package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMembersRepository extends JpaRepository<ProjectMembers,Long> {
}
