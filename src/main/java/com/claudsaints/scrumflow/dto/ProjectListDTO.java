package com.claudsaints.scrumflow.dto;

import com.claudsaints.scrumflow.entities.ProjectList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ProjectListDTO {
    private Long id;
    private String title;
    private Integer position;
    private Instant create_at;
    private Set<CardDTO> cardList = new HashSet<CardDTO>();

    ProjectListDTO(ProjectList obj){
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.position = obj.getPosition();
        this.create_at = obj.getCreate_at();

        obj.getCardList().forEach(c -> cardList.add(new CardDTO(c)));

    }
}
