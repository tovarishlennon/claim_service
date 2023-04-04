package com.my.project.claim_service.service;

import com.my.project.claim_service.dto.user.GetLoginTokenRequestDto;
import com.my.project.claim_service.dto.user.GetLoginTokenResponseDto;

public interface AuthService {
    GetLoginTokenResponseDto login(GetLoginTokenRequestDto dto);
}
