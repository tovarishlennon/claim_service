package com.my.project.claim_service.service;

import com.my.project.claim_service.dto.admin.GetAllUsersInfoRequestDto;
import com.my.project.claim_service.dto.admin.SetRoleToUseResponseDto;

import java.util.List;

public interface AdminService {
    List<GetAllUsersInfoRequestDto> getAllUsers(String name);

    SetRoleToUseResponseDto setRoleOperatorToUser(Long userId);
}
