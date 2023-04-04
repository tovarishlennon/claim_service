package com.my.project.claim_service.service.impl;

import com.my.project.claim_service.constant.RequestStatuses;
import com.my.project.claim_service.constant.ResultCodes;
import com.my.project.claim_service.dto.user.EditDraftRequestDto;
import com.my.project.claim_service.dto.user.EditDraftResponseDto;
import com.my.project.claim_service.dto.user.UserCreateRequestRequestDto;
import com.my.project.claim_service.dto.user.UserCreateRequestResponseDto;
import com.my.project.claim_service.model.Requests;
import com.my.project.claim_service.model.Users;
import com.my.project.claim_service.repository.RequestsRepository;
import com.my.project.claim_service.repository.UsersRepository;
import com.my.project.claim_service.service.BillingService;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {
    private final UsersRepository usersRepository;
    private final RequestsRepository requestsRepository;

    public BillingServiceImpl(UsersRepository usersRepository, RequestsRepository requestsRepository) {
        this.usersRepository = usersRepository;
        this.requestsRepository = requestsRepository;
    }

    @Override
    public UserCreateRequestResponseDto createAndSaveRequest(Users userById, UserCreateRequestRequestDto dto) {
        Requests request = new Requests(dto.getText(), RequestStatuses.CREATED.getStatus(), userById);
        Requests save = requestsRepository.save(request);
        return new UserCreateRequestResponseDto(save.getId(), ResultCodes.OK.getCode(), "OK");
    }

    @Override
    public UserCreateRequestResponseDto createAndSaveDraftRequest(Users userById, UserCreateRequestRequestDto dto) {
        Requests request = new Requests(dto.getText(), RequestStatuses.DRAFT.getStatus(), userById);
        Requests save = requestsRepository.save(request);
        return new UserCreateRequestResponseDto(save.getId(), ResultCodes.OK.getCode(), "OK");
    }

    @Override
    public UserCreateRequestResponseDto createAndSaveRequestFromDraftRequest(Users userById, Requests requests) {
        Requests newRequest = new Requests(requests.getText(), RequestStatuses.CREATED.getStatus(), userById);
        Requests savedRequest = requestsRepository.save(newRequest);
        return new UserCreateRequestResponseDto(savedRequest.getId(), ResultCodes.OK.getCode(), "OK");
    }

    @Override
    public EditDraftResponseDto editAndSaveDraft(Requests request, EditDraftRequestDto dto) {
        request.setText(dto.getText());
        requestsRepository.save(request);
        return new EditDraftResponseDto(ResultCodes.OK.getCode(), "Draft was edited!", request.getId());
    }
}
