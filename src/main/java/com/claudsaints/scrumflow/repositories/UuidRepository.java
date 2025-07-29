package com.claudsaints.scrumflow.repositories;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UuidRepository<T> {
    Optional<T> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
