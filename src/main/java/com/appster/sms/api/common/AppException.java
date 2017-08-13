package com.appster.sms.api.common;

/**
 * created by atul on 18/11/16.
 */
public class AppException extends RuntimeException {

    private final String errorCode;

    public AppException(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
