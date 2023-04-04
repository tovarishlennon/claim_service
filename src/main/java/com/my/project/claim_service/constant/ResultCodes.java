package com.my.project.claim_service.constant;

public enum ResultCodes {

    OK(0),
    FAIL(-100),
    VALIDATION_EXCEPTION(-200),
    SYSTEM_FAILURE(-300);

    final private Integer code;

    ResultCodes(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
