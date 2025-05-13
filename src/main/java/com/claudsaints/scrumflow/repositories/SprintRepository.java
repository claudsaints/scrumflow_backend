package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Card;
import com.claudsaints.scrumflow.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint,Long> {
}
