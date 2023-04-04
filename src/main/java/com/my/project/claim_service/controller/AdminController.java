package com.my.project.claim_service.controller;

import com.my.project.claim_service.dto.admin.GetAllUsersInfoRequestDto;
import com.my.project.claim_service.dto.admin.SetRoleToUseResponseDto;
import com.my.project.claim_service.service.AdminService;
import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@Validated
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<GetAllUsersInfoRequestDto>> getUsers(
            @RequestParam(name = "name", required = false) String name) {
        List<GetAllUsersInfoRequestDto> responseDto = adminService.getAllUsers(name);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/setRoleOperator")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SetRoleToUseResponseDto> setRoleOperatorToUser(
            @RequestParam(name = "user_id", required = false) Long userId
    ) {
        SetRoleToUseResponseDto responseDto = adminService.setRoleOperatorToUser(userId);
        return ResponseEntity.ok(responseDto);
    }

}
