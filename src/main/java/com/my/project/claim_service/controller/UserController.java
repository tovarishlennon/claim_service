package com.my.project.claim_service.controller;

import com.my.project.claim_service.dto.user.*;
import com.my.project.claim_service.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // создание заявки
    @PostMapping("/createRequest")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<UserCreateRequestResponseDto> userCreateRequest(@RequestBody UserCreateRequestRequestDto dto) {
        UserCreateRequestResponseDto responseDto = userService.createRequest(dto);
        return ResponseEntity.ok(responseDto);
    }

    // создание черновика
    @PostMapping("/createDraftRequest")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<UserCreateRequestResponseDto> userCreateDraftRequest(@RequestBody UserCreateRequestRequestDto dto) {
        UserCreateRequestResponseDto responseDto = userService.createDraftRequest(dto);
        return ResponseEntity.ok(responseDto);
    }

    // создание заявки по айди черновика
    @PostMapping("/createRequestFromDraft")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<UserCreateRequestResponseDto> createRequestFromDraft(@RequestParam(value = "draft_id") Long draftId) {
        UserCreateRequestResponseDto responseDto = userService.createRequestFromDraft(draftId);
        return ResponseEntity.ok(responseDto);
    }

    // просмотр всех заявок по пользователю
    @GetMapping("/getAllRequests")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Page<GetAllRequestsResponseDto>> getAllRequests(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC", value = "sorting") String sorting) {
        Page<GetAllRequestsResponseDto> responseDto = userService.getAllRequests(page, size, sorting);
        return ResponseEntity.ok(responseDto);
    }

    // просмотр всех черновиков по пользователю
    @GetMapping("/getAllDrafts")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Page<GetAllDraftsResponseDto>> getAllDrafts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC", value = "sorting") String sorting) {
        Page<GetAllDraftsResponseDto> responseDto = userService.getAllDrafts(page, size, sorting);
        return ResponseEntity.ok(responseDto);
    }

    // редактирование черновика по пользователю
    @PutMapping("/editDraft")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<EditDraftResponseDto> editDraft(@RequestBody EditDraftRequestDto dto) {
        EditDraftResponseDto responseDto = userService.editDraft(dto);
        return ResponseEntity.ok(responseDto);
    }

}
