package com.claudsaints.scrumflow.repositories;

import com.claudsaints.scrumflow.entities.Role;
import com.claudsaints.scrumflow.entities.User;
import com.claudsaints.scrumflow.entities.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

}
