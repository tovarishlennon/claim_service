package com.my.project.claim_service.dto.exception;

import com.my.project.claim_service.constant.ResultCodes;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private ResultCodes code;

    public ApiException(ResultCodes code, String message) {
        super(String.format("Failed. %s", message));
        this.code = code;
    }
}
