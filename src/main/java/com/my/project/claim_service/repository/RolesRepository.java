package com.my.project.claim_service.repository;

import com.my.project.claim_service.model.Roles;
import liquibase.pro.packaged.S;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Roles findByName(String name);
}
