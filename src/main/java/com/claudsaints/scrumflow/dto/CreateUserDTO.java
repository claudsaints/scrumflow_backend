package com.claudsaints.scrumflow.dto;

import com.claudsaints.scrumflow.entities.enums.RoleName;

public record CreateUserDTO (String name, String email, String password, RoleName role) {

}
