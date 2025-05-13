package com.claudsaints.scrumflow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_card")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Card  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_list_id")
    @JsonIgnore
    private ProjectList list;
    private String title;
    private String description;
    private Instant create_at;
    private Instant end_at;
    private String type;
    private Integer story_point;
    @ManyToMany
    @JoinTable(
            name = "tb_card_labels",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<Label> labels = new HashSet<>();

    public Card(Long id, ProjectList projectList, String title, String description, Instant create_at, Instant end_at, String type, Integer story_point) {
        this.id = id;
        this.list = projectList;
        this.title = title;
        this.description = description;
        this.create_at = create_at;
        this.end_at = end_at;
        this.type = type;
        this.story_point = story_point;
    }



}
