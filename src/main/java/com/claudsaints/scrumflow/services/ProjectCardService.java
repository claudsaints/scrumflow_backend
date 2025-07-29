package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.card.CardBaseDTO;
import com.claudsaints.scrumflow.dto.card.CreateCardDTO;
import com.claudsaints.scrumflow.entities.Card;
import com.claudsaints.scrumflow.entities.Label;
import com.claudsaints.scrumflow.repositories.CardRepository;
import com.claudsaints.scrumflow.repositories.LabelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ProjectCardService {

    @Autowired
    private CardRepository repository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private ProjectListService projectListService;


    public Card createCard(UUID listId, CreateCardDTO cardDTO) {
        var list = projectListService.findByUuid(listId);

        Card card = new Card(null, list, cardDTO.title(), "", null,
                null, null, null);
        return repository.save(card);
    }

    @Transactional
    public Card updateCard(UUID cardId, CardBaseDTO dto) {
        Card card = this.findByUuid(cardId);

        if (dto.getTitle() != null) card.setTitle(dto.getTitle());
        if (dto.getDescription() != null) card.setDescription(dto.getDescription());
        if (dto.getStartAt() != null) card.setStart_at(dto.getStartAt());
        if (dto.getEndAt() != null) card.setEnd_at(dto.getEndAt());
        if (dto.getType() != null) card.setType(dto.getType());
        if (dto.getStoryPoint() != null) card.setStory_point(dto.getStoryPoint());

        if (dto.getLabelIds() != null) {
            Set<Label> labels = loadLabels(dto.getLabelIds());
            card.setLabels(labels);
        }

        return repository.save(card);
    }

    private Set<Label> loadLabels(Set<Long> labelIds) {
        if (labelIds == null || labelIds.isEmpty()) return new HashSet<>();

        return new HashSet<>(labelRepository.findAllById(labelIds));
    }

    public Card findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new ObjectNotFound("Card not found"));
    }

    @Transactional
    public void delete(UUID cardId) {
        repository.deleteByUuid(cardId);
    }
}
