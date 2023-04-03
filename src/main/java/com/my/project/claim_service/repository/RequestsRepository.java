package com.my.project.claim_service.repository;

import com.my.project.claim_service.model.Requests;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RequestsRepository extends JpaRepository<Requests, Long> {
    @Query(value = "SELECT r FROM Requests r WHERE r.users.id = :userId and r.status = 0")
    Page<Requests> findAllCreatedRequestsByUserIdAndOrderBy(@Param("userId") Long userId, Pageable pageable);

    @Query(value = "SELECT r FROM Requests r WHERE r.users.id = :userId and r.status = 1")
    Page<Requests> findAllCreatedDraftsByUserIdAndOrderBy(@Param("userId") Long userId, Pageable pageable);

    Optional<Requests> findByIdAndUsers_IdAndStatus(Long id, Long userId, Integer status);

    Optional<Requests> findByIdAndUsers_Id(Long id, Long userId);
}
