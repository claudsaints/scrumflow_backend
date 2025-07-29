package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.ProjectList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ListRepository extends JpaRepository<ProjectList, Long>, UuidRepository<ProjectList> {

    Optional<ProjectList> findTopBySectionIdOrderByPositionDesc(Long sectionId);

    List<ProjectList> findAllBySectionId(Long sectionId);

    Optional<ProjectList> findTopBySectionUuidOrderByPositionDesc(UUID sectionUuid);

    List<ProjectList> findAllBySectionUuid(UUID sectionUuid);

    @Override
    Optional<ProjectList> findByUuid(UUID uuid);

    @Override
    void deleteByUuid(UUID uuid);
}
