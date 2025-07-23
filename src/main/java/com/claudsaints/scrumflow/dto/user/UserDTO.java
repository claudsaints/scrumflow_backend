package com.claudsaints.scrumflow.dto.user;

import com.claudsaints.scrumflow.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;

    public UserDTO(User obj) {
        name = obj.getName();
        email = obj.getEmail();
    }
}
