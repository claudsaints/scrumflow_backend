package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface SectionRepository extends JpaRepository<Section, Long>, UuidRepository<Section> {
    List<Section> findAllByProjectId(Long id);

    List<Section> findAllByProjectUuid(UUID uuid);

    @Override
    Optional<Section> findByUuid(UUID uuid);

    @Override
    void deleteByUuid(UUID uuid);
}
