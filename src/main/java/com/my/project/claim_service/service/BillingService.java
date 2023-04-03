package com.my.project.claim_service.service;

import com.my.project.claim_service.dto.user.EditDraftRequestDto;
import com.my.project.claim_service.dto.user.EditDraftResponseDto;
import com.my.project.claim_service.dto.user.UserCreateRequestRequestDto;
import com.my.project.claim_service.dto.user.UserCreateRequestResponseDto;
import com.my.project.claim_service.model.Requests;
import com.my.project.claim_service.model.Users;

public interface BillingService {
    UserCreateRequestResponseDto createAndSaveRequest(Users userById, UserCreateRequestRequestDto dto);

    UserCreateRequestResponseDto createAndSaveDraftRequest(Users userById, UserCreateRequestRequestDto dto);

    UserCreateRequestResponseDto createAndSaveRequestFromDraftRequest(Users userById, Requests requests);

    EditDraftResponseDto editAndSaveDraft(Requests request, EditDraftRequestDto dto);
}
