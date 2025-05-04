package com.claudsaints.scrumflow.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_project")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String title;
    private  String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner_id;
    private Instant create_At;

    @OneToMany(mappedBy = "id.project")
    private Set<ProjectMembers> members = new HashSet<>();

    Project(){

    }

    public Project(Long id, String title, String description, User user_id, Instant create_At) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner_id = user_id;
        this.create_At = create_At;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(User owner_id) {
        this.owner_id = owner_id;
    }

    public Instant getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Instant create_At) {
        this.create_At = create_At;
    }

    public Set<ProjectMembers> getMembers(){
        return members;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(owner_id, project.owner_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner_id);
    }
}
