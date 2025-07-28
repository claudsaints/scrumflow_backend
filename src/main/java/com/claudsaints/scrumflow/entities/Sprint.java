package com.claudsaints.scrumflow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_sprints")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sprint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

    @OneToOne
    private  Section section;

    private String title;

    private Instant start_date;

    private Instant end_date;

    private String goal;

    public Sprint(Long id, Project project, Section section, String title, Instant start_date, Instant end_date, String goal) {
        this.id = id;
        this.project = project;
        this.section = section;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.goal = goal;
    }
}
