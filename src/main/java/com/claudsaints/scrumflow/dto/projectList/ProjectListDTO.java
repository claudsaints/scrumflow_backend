package com.claudsaints.scrumflow.dto.projectList;

import com.claudsaints.scrumflow.dto.card.CardDTO;
import com.claudsaints.scrumflow.entities.ProjectList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ProjectListDTO {
    private UUID id;
    private String title;
    private Integer position;
    private Instant create_at;
    private Set<CardDTO> cardList = new HashSet<CardDTO>();

    public ProjectListDTO(ProjectList obj) {
        this.id = obj.getUuid();
        this.title = obj.getTitle();
        this.position = obj.getPosition();
        this.create_at = obj.getCreate_at();

        obj.getCardList().forEach(c -> cardList.add(new CardDTO(c)));

    }
}
