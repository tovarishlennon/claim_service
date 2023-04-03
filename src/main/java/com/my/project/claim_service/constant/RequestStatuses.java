package com.my.project.claim_service.constant;

public enum RequestStatuses {
    CREATED(0),
    DRAFT(1),
    ACCEPTED(2),
    REJECTED(3);

    final private Integer status;

    RequestStatuses(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
