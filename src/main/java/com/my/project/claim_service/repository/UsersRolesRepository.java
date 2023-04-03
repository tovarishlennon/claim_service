package com.my.project.claim_service.repository;

import com.my.project.claim_service.model.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRoles, Long> {
}
