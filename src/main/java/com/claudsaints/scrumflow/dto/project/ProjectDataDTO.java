package com.claudsaints.scrumflow.dto.project;

import com.claudsaints.scrumflow.dto.projectList.ProjectListDTO;
import com.claudsaints.scrumflow.dto.ProjectMemberDTO;
import com.claudsaints.scrumflow.dto.user.UserDTO;
import com.claudsaints.scrumflow.entities.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

//projeto_com_embedding_lista_members

@NoArgsConstructor
@Setter
@Getter
public class ProjectDataDTO {
    private Long id;
    private String title;
    private String description;
    private Instant create_at;
    private UserDTO owner;

    private Set<ProjectMemberDTO> members = new HashSet<>();

    private Set<ProjectListDTO> lists = new HashSet<ProjectListDTO>();

    private Set<Label> labels = new HashSet<>();

    private Set<Sprint> sprints = new HashSet<>();

    public ProjectDataDTO(Project obj) {
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.description = obj.getDescription();
        this.create_at = obj.getCreate_At();
        this.owner = new UserDTO(obj.getOwner());

        obj.getMembers().forEach(m -> members.add(new ProjectMemberDTO(m)));
        obj.getLists().forEach(l -> this.lists.add(new ProjectListDTO(l)));
;
    }
}
