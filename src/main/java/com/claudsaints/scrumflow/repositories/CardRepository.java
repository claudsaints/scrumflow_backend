package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, Long>, UuidRepository<Card> {
    @Override
    Optional<Card> findByUuid(UUID uuid);

    @Override
    void deleteByUuid(UUID uuid);
}
