package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.controllers.exceptions.ObjectNotFound;
import com.claudsaints.scrumflow.dto.card.CardBaseDTO;
import com.claudsaints.scrumflow.dto.card.CreateCardDTO;
import com.claudsaints.scrumflow.entities.Card;
import com.claudsaints.scrumflow.entities.Label;
import com.claudsaints.scrumflow.repositories.CardRepository;
import com.claudsaints.scrumflow.repositories.LabelRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectCardService {

    private final CardRepository repository;
    private final LabelRepository labelRepository;
    private final ProjectListService projectListService;

    public ProjectCardService(CardRepository repository, LabelRepository labelRepository, ProjectListService projectListService) {
        this.repository = repository;
        this.labelRepository = labelRepository;
        this.projectListService = projectListService;
    }

    public Card createCard(Long listId, CreateCardDTO cardDTO) {
        var list = projectListService.findById(listId);

        Card card = new Card(null, list, cardDTO.title(), "", null,
                null, null, null);
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

    public Card findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFound("Card not found"));
    }

    public void  delete(Long cardId){
        repository.deleteById(cardId);
    }
}
