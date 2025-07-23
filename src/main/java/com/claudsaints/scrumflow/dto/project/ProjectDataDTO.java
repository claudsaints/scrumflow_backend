package com.claudsaints.scrumflow.dto.project;

import com.claudsaints.scrumflow.dto.projectMember.ProjectMemberDTO;
import com.claudsaints.scrumflow.dto.user.UserDTO;
import com.claudsaints.scrumflow.entities.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProjectDataDTO {
    private Long id;
    private String title;
    private String description;
    private Instant create_at;
    private UserDTO owner;

    private Set<ProjectMemberDTO> members = new HashSet<>();

    private Set<Section> sections = new HashSet<>();

    private Set<Label> labels = new HashSet<>();

    private Set<Sprint> sprints = new HashSet<>();

    public ProjectDataDTO(Project obj) {
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.description = obj.getDescription();
        this.create_at = obj.getCreate_At();
        this.owner = new UserDTO(obj.getOwner());

        obj.getMembers().forEach(m -> members.add(new ProjectMemberDTO(m)));

        obj.getSections().forEach(s -> this.sections.add(s));

    }
}
