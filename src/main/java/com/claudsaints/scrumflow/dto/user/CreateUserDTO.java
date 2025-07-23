package com.claudsaints.scrumflow.dto.user;

import com.claudsaints.scrumflow.entities.enums.RoleName;
import jakarta.validation.constraints.*;

public record CreateUserDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 255)
        String name,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        @Size(min = 6, max = 255)
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 12, message = "A senha deve ter entre 6 e 12 caracteres")
        String password,

        @NotNull(message = "O papel é obrigatório")
        RoleName role
) {
}
