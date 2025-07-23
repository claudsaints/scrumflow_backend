package com.claudsaints.scrumflow.entities;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_project")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private  Long id;
    private  String title;
    private  String description;
    private  String backgroundImage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    private Instant create_At;

    @OneToMany(mappedBy = "id.project")
    private Set<ProjectMembers> members = new HashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Section> sections = new HashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Label> labels = new HashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<Sprint> sprints = new HashSet<>();



    public Project(Long id, String title, String description, User user, Instant create_At) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = user;
        this.create_At = create_At;
    }
}
