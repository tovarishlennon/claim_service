package com.my.project.claim_service.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EditDraftResponseDto {
    private Integer code;
    private String message;
    private Long draftId;

    public EditDraftResponseDto(Integer code, String message, Long draftId) {
        this.code = code;
        this.message = message;
        this.draftId = draftId;
    }
}
