package com.claudsaints.scrumflow.repositories;
import com.claudsaints.scrumflow.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, Long>, UuidRepository<Project> {
    List<Project> findByOwnerEmail(String email);

    List<Project> findByMembersIdUserEmail(String email);

    @Override
    Optional<Project> findByUuid(UUID uuid);

    @Override
    void deleteByUuid(UUID uuid);
}
