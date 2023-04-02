package com.my.project.claim_service.service.impl;

import com.my.project.claim_service.dto.token.GenerateTokenResponseDto;
import com.my.project.claim_service.dto.user.GetLoginTokenRequestDto;
import com.my.project.claim_service.dto.user.GetLoginTokenResponseDto;
import com.my.project.claim_service.service.UserService;
import com.my.project.claim_service.util.JwtGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtGenerator jwtGenerator;

    public UserServiceImpl(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public GetLoginTokenResponseDto login(GetLoginTokenRequestDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        GenerateTokenResponseDto responseFromJwtGenerator = jwtGenerator.generateToken(authentication);

        GetLoginTokenResponseDto responseDto = new GetLoginTokenResponseDto();
        responseDto.setAccessToken(responseFromJwtGenerator.getToken());
        responseDto.setCode(0);
        responseDto.setExpiresAt(responseFromJwtGenerator.getExpiresAt());

        return responseDto;
    }

}
