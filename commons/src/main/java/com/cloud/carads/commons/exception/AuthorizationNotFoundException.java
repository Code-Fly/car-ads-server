package com.cloud.carads.commons.exception;

public class AuthorizationNotFoundException extends Exception {
    public AuthorizationNotFoundException() {
        super();
    }

    public AuthorizationNotFoundException(String message) {
        super(message);
    }

    public AuthorizationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AuthorizationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
