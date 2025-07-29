package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SprintRepository extends JpaRepository<Sprint, Long>, UuidRepository<Sprint> {
    List<Sprint> findAllByProjectUuid(UUID uuid);

    @Override
    Optional<Sprint> findByUuid(UUID uuid);

    @Override
    void deleteByUuid(UUID uuid);
}
