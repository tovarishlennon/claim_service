package com.my.project.claim_service.controller;

import com.my.project.claim_service.dto.operator.AcceptRequestResponseDto;
import com.my.project.claim_service.dto.operator.RejectRequestResponseDto;
import com.my.project.claim_service.dto.user.GetAllRequestsResponseDto;
import com.my.project.claim_service.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/operator")
@Validated
@RequiredArgsConstructor
public class OperatorController {
    private final OperatorService operatorService;

    // универсальный метод который позволяет просмотривать заявки пользователей
    // принимает в себя параметр "name" который не является обязательным
    // таким образом выполняется сразу два условия ТЗ по оператору
    @GetMapping("/find")
    @PreAuthorize("hasAuthority('OPERATOR')")
    public ResponseEntity<Page<GetAllRequestsResponseDto>> getAllRequests(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(defaultValue = "DESC", value = "sorting") String sorting) {
        Page<GetAllRequestsResponseDto> responseDto = operatorService.getAllRequests(page, size, sorting, name);
        return ResponseEntity.ok(responseDto);
    }

    // метод на изменение статуса заявки на "accepted"
    @PutMapping("/accept")
    @PreAuthorize("hasAuthority('OPERATOR')")
    public ResponseEntity<AcceptRequestResponseDto> acceptRequest(@RequestParam(name = "draft_id") Long draftId) {
        AcceptRequestResponseDto responseDto = operatorService.acceptRequestByDraftId(draftId);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/reject")
    @PreAuthorize("hasAuthority('OPERATOR')")
    public ResponseEntity<RejectRequestResponseDto> rejectRequest(@RequestParam(name = "draft_id") Long draftId) {
        RejectRequestResponseDto responseDto = operatorService.rejectRequestByDraftId(draftId);
        return ResponseEntity.ok(responseDto);
    }
}
