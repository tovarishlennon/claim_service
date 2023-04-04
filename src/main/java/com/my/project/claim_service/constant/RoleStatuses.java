package com.my.project.claim_service.constant;

public enum RoleStatuses {
    ACTIVE(1),
    NON_ACTIVE(0);

    final private Integer code;
    RoleStatuses(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
