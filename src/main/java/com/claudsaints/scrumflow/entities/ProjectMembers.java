package com.claudsaints.scrumflow.entities;

import com.claudsaints.scrumflow.pk.ProjectMembersPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_project_members")
public class ProjectMembers implements Serializable {
    @EmbeddedId
    private ProjectMembersPk id = new ProjectMembersPk();
    private String role;
    private Instant join_at;

    ProjectMembers(){}

    public ProjectMembers(User user , Project project, String role, Instant join_at) {
        this.id.setProject(project);
        this.id.setUser(user);
        this.role = role;
        this.join_at = join_at;
    }
    @JsonIgnore
    public User getUser(){
        return this.id.getUser();
    }
    @JsonIgnore
    public Project getProject(){
        return  this.id.getProject();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getJoin_at() {
        return join_at;
    }

    public void setJoin_at(Instant join_at) {
        this.join_at = join_at;
    }
}
