package com.my.project.claim_service.service.impl;

import com.my.project.claim_service.constant.RequestStatuses;
import com.my.project.claim_service.constant.ResultCodes;
import com.my.project.claim_service.dto.exception.ApiException;
import com.my.project.claim_service.dto.token.GenerateTokenResponseDto;
import com.my.project.claim_service.dto.user.*;
import com.my.project.claim_service.mapper.RequestsMapper;
import com.my.project.claim_service.model.Requests;
import com.my.project.claim_service.model.Users;
import com.my.project.claim_service.repository.RequestsRepository;
import com.my.project.claim_service.repository.UsersRepository;
import com.my.project.claim_service.security.PrincipalUser;
import com.my.project.claim_service.service.BillingService;
import com.my.project.claim_service.service.UserService;
import com.my.project.claim_service.util.JwtGenerator;
import org.springframework.data.domain.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final BillingService billingService;
    private final RequestsRepository requestsRepository;
    private final RequestsMapper requestsMapper;

    public UserServiceImpl(UsersRepository usersRepository, BillingService billingService,
                           RequestsRepository requestsRepository, RequestsMapper requestsMapper) {
        this.usersRepository = usersRepository;
        this.billingService = billingService;
        this.requestsRepository = requestsRepository;
        this.requestsMapper = requestsMapper;
    }

    @Override
    public UserCreateRequestResponseDto createRequest(UserCreateRequestRequestDto dto) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users userById = usersRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found!"));
        return billingService.createAndSaveRequest(userById, dto);
    }

    @Override
    public UserCreateRequestResponseDto createDraftRequest(UserCreateRequestRequestDto dto) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users userById = usersRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found!"));
        return billingService.createAndSaveDraftRequest(userById, dto);
    }

    @Override
    public UserCreateRequestResponseDto createRequestFromDraft(Long draftId) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users userById = usersRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found!"));
        Requests requests = requestsRepository.findByIdAndUsers_Id(draftId, user.getId()).orElseThrow(() -> new ApiException(ResultCodes.FAIL, "Draft was not found!"));
        if (!Objects.equals(requests.getStatus(), RequestStatuses.DRAFT.getStatus())) {
            throw new ApiException(ResultCodes.FAIL, "Sorry, it is not a draft");
        }
        return billingService.createAndSaveRequestFromDraftRequest(userById, requests);
    }

    @Override
    public Page<GetAllRequestsResponseDto> getAllRequests(Integer page, Integer size, String sorting) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<GetAllRequestsResponseDto> response = new ArrayList<>();
        Pageable paging = sort(sorting, page, size);
        Page<Requests> pageableRequests = requestsRepository.findAllCreatedRequestsByUserIdAndOrderBy(user.getId(), paging);

        if(!pageableRequests.isEmpty()) {
            response = pageableRequests.stream().map(requestsMapper::toMapRequestToDto).collect(Collectors.toList());
            return new PageImpl<>(response, paging, pageableRequests.getTotalElements());
        } else {
            return new PageImpl<>(response, paging, 0);
        }

    }

    @Override
    public EditDraftResponseDto editDraft(EditDraftRequestDto dto) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Requests request = requestsRepository.findByIdAndUsers_Id(dto.getDraftId(), user.getId()).orElseThrow(() -> new ApiException(ResultCodes.FAIL, "Draft was not found!"));
        if (!Objects.equals(request.getStatus(), RequestStatuses.DRAFT.getStatus())) {
            throw new ApiException(ResultCodes.FAIL, "Sorry, it is not a draft");
        }
        return billingService.editAndSaveDraft(request, dto);
    }

    @Override
    public Page<GetAllDraftsResponseDto> getAllDrafts(Integer page, Integer size, String sorting) {
        PrincipalUser user = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<GetAllDraftsResponseDto> response = new ArrayList<>();
        Pageable paging = sort(sorting, page, size);
        Page<Requests> pageableRequests = requestsRepository.findAllCreatedDraftsByUserIdAndOrderBy(user.getId(), paging);

        if(!pageableRequests.isEmpty()) {
            response = pageableRequests.stream().map(requestsMapper::toMapRequestToDraftDto).collect(Collectors.toList());
            return new PageImpl<>(response, paging, pageableRequests.getTotalElements());
        } else {
            return new PageImpl<>(response, paging, 0);
        }
    }

    private Pageable sort(String sorting, Integer page, Integer size) {
        Sort sort = sorting.equalsIgnoreCase("DESC") ? Sort.by("dateCreated").descending() : Sort.by("dateCreated").ascending();
        return PageRequest.of(page, size, sort);
    }

}
