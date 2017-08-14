package com.myschool.sms.api.common;

/**
 * created by lokesh on 18/11/16.
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
