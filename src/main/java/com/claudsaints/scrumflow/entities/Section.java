package com.claudsaints.scrumflow.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Entity
@Table(name = "tb_section")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Section implements Comparable<Section>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private Long id;


    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid = UUID.randomUUID();

    private String title;

    private String description;

    @OneToMany(mappedBy = "section",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectList> lists = new TreeSet<>();

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

    public Section(Long id, String title, String description, Project project) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.project = project;
    }


    @Override
    public int compareTo(@NotNull Section o) {
        return o.getId().compareTo(this.id);
    }
}
