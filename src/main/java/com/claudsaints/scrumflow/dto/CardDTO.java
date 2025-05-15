package com.claudsaints.scrumflow.dto;

import com.claudsaints.scrumflow.entities.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CardDTO {
    private String title;
    private String description;
    CardDTO(Card obj){
        this.title = obj.getTitle();
        this.description = obj.getDescription();;
    }
}
