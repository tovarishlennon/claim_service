package com.my.project.claim_service.controller;

import com.my.project.claim_service.dto.GetLoginTokenRequestDto;
import com.my.project.claim_service.dto.GetLoginTokenResponseDto;
import com.my.project.claim_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<GetLoginTokenResponseDto> adminEndpoint(@RequestBody GetLoginTokenRequestDto dto) {
        GetLoginTokenResponseDto responseDto = userService.login(dto);
        return ResponseEntity.ok(responseDto);
    }

}
