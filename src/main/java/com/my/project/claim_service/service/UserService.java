package com.my.project.claim_service.service;

import com.my.project.claim_service.dto.user.*;
import org.springframework.data.domain.Page;

public interface UserService {
    GetLoginTokenResponseDto login(GetLoginTokenRequestDto dto);

    UserCreateRequestResponseDto createRequest(UserCreateRequestRequestDto dto);

    UserCreateRequestResponseDto createDraftRequest(UserCreateRequestRequestDto dto);

    UserCreateRequestResponseDto createRequestFromDraft(Long draftId);

    Page<GetAllRequestsResponseDto> getAllRequests(Integer page, Integer size, String orderBy);

    EditDraftResponseDto editDraft(EditDraftRequestDto dto);

    Page<GetAllDraftsResponseDto> getAllDrafts(Integer page, Integer size, String sorting);
}
