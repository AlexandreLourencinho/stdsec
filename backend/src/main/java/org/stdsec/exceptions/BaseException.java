package org.stdsec.exceptions;

import jakarta.ws.rs.core.Response.Status;

public abstract class BaseException extends RuntimeException {

    private final Status status;
    private final String code;

    protected BaseException(Status status, String code, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
