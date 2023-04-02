package com.my.project.claim_service.dto;

import lombok.Data;

@Data
public class GetLoginTokenRequestDto {
    private String username;
    private String password;
}
