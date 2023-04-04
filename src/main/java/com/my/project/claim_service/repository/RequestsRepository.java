package com.my.project.claim_service.repository;

import com.my.project.claim_service.model.Requests;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RequestsRepository extends JpaRepository<Requests, Long> {
    // тут я решил, что пользователь вправе отслеживать заявки как созданные, так с другим статусом для истории
    @Query(value = "SELECT r FROM Requests r WHERE r.users.id = :userId and r.status NOT IN (1)",
    countQuery = "SELECT COUNT(r) FROM Requests r WHERE r.users.id = :userId and r.status NOT IN (1)")
    Page<Requests> findAllCreatedRequestsByUserIdAndOrderBy(@Param("userId") Long userId, Pageable pageable);

    // тут пользователь видит только черновки от которых может создавать новые заявки
    @Query(value = "SELECT r FROM Requests r WHERE r.users.id = :userId and r.status = 1",
    countQuery = "SELECT COUNT(r) FROM Requests r WHERE r.users.id = :userId and r.status = 1")
    Page<Requests> findAllCreatedDraftsByUserIdAndOrderBy(@Param("userId") Long userId, Pageable pageable);

    // тут я принял решение, что оператор вправе увидеть все отправленнные на рассмотрение заявки со всеми статусами кроме черновиков,
    // так как могут возникнуть ситуации когда оператору придется смотреть историю всех заявок по пользователю и принимать их снова
    // черновики доступны только пользователям, потому что они считаются "мусорными" и особой ценности не имеют
    @Query(value = "SELECT r FROM Requests r WHERE r.status NOT IN (1) AND (:userName IS null OR (r.users.name LIKE %:userName%))",
    countQuery = "SELECT COUNT(r) FROM Requests r WHERE r.status NOT IN (1) AND (:userName IS null OR (r.users.name LIKE %:userName%))")
    Page<Requests> findAllCreatedRequests(@Param("userName") String userName, Pageable pageable);

    // поиск заявки завязанной под конкретного пользвателя
    Optional<Requests> findByIdAndUsers_Id(Long id, Long userId);
}
