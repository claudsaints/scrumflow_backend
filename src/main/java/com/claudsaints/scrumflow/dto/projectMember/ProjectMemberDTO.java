package com.claudsaints.scrumflow.dto.projectMember;

import com.claudsaints.scrumflow.dto.user.UserDTO;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.entities.enums.ProjectMemberRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class ProjectMemberDTO {
    private ProjectMemberRole role;
    private UserDTO user;
    private Instant join_at;

    public ProjectMemberDTO(ProjectMembers obj){
        this.role = obj.getRole();
        this.user = new UserDTO(obj.getUser());
        this.join_at = obj.getJoin_at();
    }
}
