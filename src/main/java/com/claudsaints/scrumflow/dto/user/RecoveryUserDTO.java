package com.claudsaints.scrumflow.dto.user;

import com.claudsaints.scrumflow.entities.Role;

import java.util.List;

public record RecoveryUserDTO(Long id,
                              String email,
                              List<Role> roles
) {
}
