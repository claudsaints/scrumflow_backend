package com.claudsaints.scrumflow.controllers;


import com.claudsaints.scrumflow.dto.card.CardBaseDTO;
import com.claudsaints.scrumflow.dto.card.CreateCardDTO;
import com.claudsaints.scrumflow.entities.Card;
import com.claudsaints.scrumflow.services.ProjectCardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/projects/cards")
public class ProjectCardController {

    @Autowired
    private ProjectCardService service;


    @GetMapping("/{cardId}")
    public ResponseEntity<Card> findById(
            @PathVariable Long cardId
    ) {
        Card card = service.findById(cardId);

        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping("/{listId}")
    public ResponseEntity<Card> create(
            @RequestBody CreateCardDTO cardDTO,
            @PathVariable Long listId
    ) {
        Card newCard = service.createCard(listId, cardDTO);

        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<Card> update(
            @RequestBody CardBaseDTO card,
            @PathVariable Long cardId
    ) {
        Card newCard = service.updateCard(cardId, card);

        return new ResponseEntity<>(newCard, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{cardId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long cardId
    ) {
        service.delete(cardId);

        return new ResponseEntity<>( HttpStatus.OK);
    }

}
