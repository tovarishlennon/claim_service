package com.my.project.claim_service.repository;

import com.my.project.claim_service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    // универсальный поиск всех пользователей в системе с возможностью поиска по имени/части имени
    @Query("SELECT u FROM Users u JOIN u.usersRoles ur JOIN ur.roleId r WHERE r.name IN (:user) AND (:name IS NULL OR (u.name LIKE %:name%)) AND ur.status = 1")
    List<Users> findAllByUsersWithRoles(@Param("user") String user, @Param("name") String name);
}
