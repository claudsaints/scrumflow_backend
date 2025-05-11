package com.claudsaints.scrumflow.entities;

import com.claudsaints.scrumflow.pk.ProjectMembersPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_project_members")
@NoArgsConstructor
@AllArgsConstructor

public class ProjectMembers implements Serializable {
    @EmbeddedId
    private ProjectMembersPk id = new ProjectMembersPk();
    @Getter @Setter
    private String role;
    @Getter @Setter
    private Instant join_at;

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


}
