package com.my.project.claim_service.service;

import com.my.project.claim_service.dto.GetLoginTokenRequestDto;
import com.my.project.claim_service.dto.GetLoginTokenResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    GetLoginTokenResponseDto login(GetLoginTokenRequestDto dto);
}
