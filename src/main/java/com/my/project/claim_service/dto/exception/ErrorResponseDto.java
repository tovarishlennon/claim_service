package com.my.project.claim_service.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private Integer code;
    private String message;
}
