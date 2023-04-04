package com.my.project.claim_service.controller;

import com.my.project.claim_service.dto.user.GetLoginTokenRequestDto;
import com.my.project.claim_service.dto.user.GetLoginTokenResponseDto;
import com.my.project.claim_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // эндпоинт для входа в систему
    // выдается JWT токен по которому осуществляется дальнейшая авторизация по системе
    // срок действия токена = 1 час
    @PostMapping("/login")
    public ResponseEntity<GetLoginTokenResponseDto> adminEndpoint(@RequestBody GetLoginTokenRequestDto dto) {
        GetLoginTokenResponseDto responseDto = authService.login(dto);
        return ResponseEntity.ok(responseDto);
    }
}
