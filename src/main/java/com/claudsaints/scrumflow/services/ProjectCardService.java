package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.dto.card.CardBaseDTO;
import com.claudsaints.scrumflow.entities.Card;
import com.claudsaints.scrumflow.entities.Label;
import com.claudsaints.scrumflow.repositories.CardRepository;
import com.claudsaints.scrumflow.repositories.LabelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectCardService {

    private final CardRepository repository;
    private final LabelRepository labelRepository;
    private final ProjectListService projectListService;

    public Card createCard(Long listId, CardBaseDTO dto) {
        var list = projectListService.findById(listId);

        Set<Label> labels = loadLabels(dto.getLabelIds());

        Card card = new Card(null, list, dto.getTitle(), dto.getDescription(), dto.getStartAt(),
                dto.getEndAt(), dto.getType(), dto.getStoryPoint());

        card.setLabels(labels);

        return repository.save(card);
    }

    public Card updateCard(Long cardId, CardBaseDTO dto) {
        Card card = this.findById(cardId);

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

    private Card findById(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Card not found"));
    }
}
