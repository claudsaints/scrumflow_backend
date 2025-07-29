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

import java.util.UUID;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/project/cards")
public class ProjectCardController {

    @Autowired
    private ProjectCardService service;


    @PostMapping("/{listId}")
    public ResponseEntity<Card> create(
            @RequestBody CreateCardDTO cardDTO,
            @PathVariable UUID listId
    ) {
        Card newCard = service.createCard(listId, cardDTO);

        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<Card> findById(
            @PathVariable UUID cardId
    ) {
        Card card = service.findByUuid(cardId);

        return new ResponseEntity<>(card, HttpStatus.OK);
    }


    @PutMapping("/{cardId}")
    public ResponseEntity<Card> update(
            @RequestBody CardBaseDTO card,
            @PathVariable UUID cardId
    ) {
        Card newCard = service.updateCard(cardId, card);

        return new ResponseEntity<>(newCard, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{cardId}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID cardId
    ) {
        service.delete(cardId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
