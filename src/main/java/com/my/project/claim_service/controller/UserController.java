package com.my.project.claim_service.controller;

import com.my.project.claim_service.dto.user.GetLoginTokenRequestDto;
import com.my.project.claim_service.dto.user.GetLoginTokenResponseDto;
import com.my.project.claim_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> userEndpoint() {
        return ResponseEntity.ok("Hello, ADMIN!");
    }
}
