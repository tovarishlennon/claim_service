package com.my.project.claim_service.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserCreateRequestResponseDto {
    private Long requestId;
    private Integer code;
    private String status;

    public UserCreateRequestResponseDto(Long requestId, Integer code, String status) {
        this.requestId = requestId;
        this.code = code;
        this.status = status;
    }
}
