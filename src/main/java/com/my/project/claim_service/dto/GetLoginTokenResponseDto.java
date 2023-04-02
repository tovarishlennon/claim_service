package com.my.project.claim_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class GetLoginTokenResponseDto {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("token")
    private String accessToken;
    @JsonProperty("expires_at")
    private ZonedDateTime expiresAt;
}
