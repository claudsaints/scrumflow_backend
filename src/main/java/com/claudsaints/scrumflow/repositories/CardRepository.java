package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
