package com.claudsaints.scrumflow.dto;

import com.claudsaints.scrumflow.entities.ProjectMembers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class ProjectMemberDTO {
    private String role;
    private UserDTO user;
    private Instant join_at;

    ProjectMemberDTO(ProjectMembers obj){
        this.role = obj.getRole();
        this.user = new UserDTO(obj.getUser());
        this.join_at = obj.getJoin_at();
    }
}
