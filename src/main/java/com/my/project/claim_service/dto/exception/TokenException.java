package com.my.project.claim_service.dto.exception;

import com.my.project.claim_service.constant.ResultCodes;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {
    private ResultCodes code;

    public TokenException(ResultCodes code, String message) {
        super(String.format("Failed. %s", message));
        this.code = code;
    }

}
