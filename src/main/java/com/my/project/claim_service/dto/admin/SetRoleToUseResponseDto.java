package com.my.project.claim_service.dto.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SetRoleToUseResponseDto {
    private Long userId;
    private Integer code;
    private String message;

    public SetRoleToUseResponseDto(Long userId, Integer code, String message) {
        this.userId = userId;
        this.code = code;
        this.message = message;
    }
}
