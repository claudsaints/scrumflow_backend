package com.claudsaints.scrumflow.entities;

import com.claudsaints.scrumflow.entities.enums.ProjectMemberRole;
import com.claudsaints.scrumflow.pk.ProjectMembersPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_project_members")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectMembers implements Serializable {
    @EmbeddedId
    private ProjectMembersPk id = new ProjectMembersPk();

    private ProjectMemberRole role;

    private Instant join_at;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid = UUID.randomUUID();

    public ProjectMembers(User user, Project project, ProjectMemberRole role, Instant join_at) {
        this.id.setProject(project);
        this.id.setUser(user);
        this.role = role;
        this.join_at = join_at;
    }

    @JsonIgnore
    public User getUser() {
        return this.id.getUser();
    }

    @JsonIgnore
    public Project getProject() {
        return this.id.getProject();
    }


}
