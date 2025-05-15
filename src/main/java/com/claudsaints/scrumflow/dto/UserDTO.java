package com.claudsaints.scrumflow.dto;

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

    UserDTO(User obj){
        name = obj.getName();
        email = obj.getEmail();
    }
}
