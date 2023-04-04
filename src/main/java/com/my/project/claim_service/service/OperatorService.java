package com.my.project.claim_service.service;

import com.my.project.claim_service.dto.operator.AcceptRequestResponseDto;
import com.my.project.claim_service.dto.operator.RejectRequestResponseDto;
import com.my.project.claim_service.dto.user.GetAllRequestsResponseDto;
import org.springframework.data.domain.Page;

public interface OperatorService {
    Page<GetAllRequestsResponseDto> getAllRequests(Integer page, Integer size, String sorting, String name);

    AcceptRequestResponseDto acceptRequestByDraftId(Long draftId);

    RejectRequestResponseDto rejectRequestByDraftId(Long draftId);
}
