package com.claudsaints.scrumflow.dto.card;

import com.claudsaints.scrumflow.entities.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CardDTO {
    private UUID id;
    private String title;
    private String description;

    public CardDTO(Card obj) {

        this.title = obj.getTitle();
        this.description = obj.getDescription();
        this.id = obj.getUuid();
    }
}
