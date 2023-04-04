package com.my.project.claim_service.repository;

import com.my.project.claim_service.model.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {
    Optional<UsersRoles> findByUserId_IdAndRoleId_Name(Long userId, String roleName);
}
