package com.claudsaints.scrumflow.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_section")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private Long id;
    private String title;
    private String description;

    @OneToMany(mappedBy = "section",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectList> lists = new HashSet<>();

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


}
