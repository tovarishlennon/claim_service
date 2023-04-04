package com.my.project.claim_service.dto.operator;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RejectRequestResponseDto {
    private Long draftId;
    private Integer code;
    private String status;

    public RejectRequestResponseDto(Long draftId, Integer code, String status) {
        this.draftId = draftId;
        this.code = code;
        this.status = status;
    }
}
