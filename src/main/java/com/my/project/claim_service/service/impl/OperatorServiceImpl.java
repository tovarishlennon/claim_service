package com.my.project.claim_service.service.impl;

import com.my.project.claim_service.constant.RequestStatuses;
import com.my.project.claim_service.constant.ResultCodes;
import com.my.project.claim_service.dto.exception.ApiException;
import com.my.project.claim_service.dto.operator.AcceptRequestResponseDto;
import com.my.project.claim_service.dto.operator.RejectRequestResponseDto;
import com.my.project.claim_service.dto.user.GetAllRequestsResponseDto;
import com.my.project.claim_service.mapper.RequestsMapper;
import com.my.project.claim_service.model.Requests;
import com.my.project.claim_service.repository.RequestsRepository;
import com.my.project.claim_service.service.OperatorService;
import com.my.project.claim_service.utils.ConvertText;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {
    private final RequestsRepository requestsRepository;
    private final RequestsMapper requestsMapper;
    @Override
    public Page<GetAllRequestsResponseDto> getAllRequests(Integer page, Integer size, String sorting, String name) {
        List<GetAllRequestsResponseDto> response = new ArrayList<>();
        Pageable paging = sort(sorting, page, size);
        Page<Requests> pageableRequests = requestsRepository.findAllCreatedRequests(name, paging);

        if(!pageableRequests.isEmpty()) {
            response = pageableRequests.stream().map( req -> {
                GetAllRequestsResponseDto n = requestsMapper.toMapRequestToDtoWithDashes(req);
                n.setText(ConvertText.convertToDashSeparatedString(req.getText()));
                return n;
            }).collect(Collectors.toList());
            return new PageImpl<>(response, paging, pageableRequests.getTotalElements());
        } else {
            return new PageImpl<>(response, paging, 0);
        }
    }

    // тут я решил добавить валидацию на то, что заявка не является случайно черновиком или не в статусе "accepted"
    @Override
    public AcceptRequestResponseDto acceptRequestByDraftId(Long draftId) {
        Requests request = requestsRepository.findById(draftId).orElseThrow(() -> new ApiException(ResultCodes.FAIL, "Draft was not found!"));

        if (Objects.equals(request.getStatus(), RequestStatuses.DRAFT.getStatus())) {
            throw new ApiException(ResultCodes.FAIL, "This request is a draft!");
        } else if (Objects.equals(request.getStatus(), RequestStatuses.ACCEPTED.getStatus())) {
            throw new ApiException(ResultCodes.FAIL, "Request was already accepted!");
        }

        request.setStatus(RequestStatuses.ACCEPTED.getStatus());
        requestsRepository.save(request);

        return new AcceptRequestResponseDto(draftId, ResultCodes.OK.getCode(), "Draft was accepted");
    }

    // тут я решил добавить валидацию на то, что заявка не является случайно черновиком или не в статусе "rejected"
    @Override
    public RejectRequestResponseDto rejectRequestByDraftId(Long draftId) {
        Requests request = requestsRepository.findById(draftId).orElseThrow(() -> new ApiException(ResultCodes.FAIL, "Draft was not found!"));

        if (Objects.equals(request.getStatus(), RequestStatuses.DRAFT.getStatus())) {
            throw new ApiException(ResultCodes.FAIL, "This request is a draft!");
        } else if (Objects.equals(request.getStatus(), RequestStatuses.REJECTED.getStatus())) {
            throw new ApiException(ResultCodes.FAIL, "Request was already rejected!");
        }
        request.setStatus(RequestStatuses.REJECTED.getStatus());
        requestsRepository.save(request);
        return new RejectRequestResponseDto(draftId, ResultCodes.OK.getCode(), "Draft was rejected");
    }

    private Pageable sort(String sorting, Integer page, Integer size) {
        Sort sort = sorting.equalsIgnoreCase("DESC") ? Sort.by("dateCreated").descending() : Sort.by("dateCreated").ascending();
        return PageRequest.of(page, size, sort);
    }
}
