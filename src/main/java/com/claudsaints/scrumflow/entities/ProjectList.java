package com.claudsaints.scrumflow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_project_list")
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class ProjectList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private Long id;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnore
    private Section section;


    @Setter
    private String title;
    @Setter
    private Integer position;
    @Setter
    private Instant create_at;

    @OneToMany(mappedBy = "list",  cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Card> cardList = new HashSet<>();

    public ProjectList(Long id, Section section, String title, Integer position, Instant create_at) {
        this.id = id;
        this.section = section;
        this.title = title;
        this.position = position;
        this.create_at = create_at;
    }
}
