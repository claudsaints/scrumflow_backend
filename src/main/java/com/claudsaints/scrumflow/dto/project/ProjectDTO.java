package com.claudsaints.scrumflow.dto.project;

import com.claudsaints.scrumflow.dto.user.UserDTO;
import com.claudsaints.scrumflow.entities.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private Instant create_at;
    private UserDTO owner;



    public ProjectDTO(Project obj) {
        this.id = obj.getId();
        this.title = obj.getTitle();
        this.description = obj.getDescription();
        this.create_at = obj.getCreate_At();
        this.owner = new UserDTO(obj.getOwner());
    }
}
