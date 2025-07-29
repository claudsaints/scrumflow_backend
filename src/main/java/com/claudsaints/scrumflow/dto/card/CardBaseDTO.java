package com.claudsaints.scrumflow.dto.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CardBaseDTO {
    private String title;
    private String description;
    private Instant startAt;
    private Instant endAt;
    private String type;
    private Integer storyPoint;
    private Set<Long> labelIds;
}
