package com.claudsaints.scrumflow.dto.project;

import com.claudsaints.scrumflow.dto.user.UserDTO;
import com.claudsaints.scrumflow.entities.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

//projeto_padrão_sem_embedding

@NoArgsConstructor
@Getter
@Setter
public class ProjectDTO {
    private UUID id;
    private String title;
    private String description;
    private String backgroundImage;
    private Instant create_at;
    private UserDTO owner;


    public ProjectDTO(Project obj) {
        this.id = obj.getUuid();
        this.title = obj.getTitle();
        this.description = obj.getDescription();
        this.create_at = obj.getCreate_At();
        this.owner = new UserDTO(obj.getOwner());
        this.backgroundImage = obj.getBackgroundImage();
    }
}
