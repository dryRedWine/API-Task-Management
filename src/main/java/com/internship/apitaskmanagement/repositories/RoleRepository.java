package com.internship.apitaskmanagement.repositories;

import com.internship.apitaskmanagement.enums.ERole;
import com.internship.apitaskmanagement.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRole(ERole role);
}
