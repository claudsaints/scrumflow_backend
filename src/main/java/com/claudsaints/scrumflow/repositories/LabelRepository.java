package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Card;
import com.claudsaints.scrumflow.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label,Long> {
}
