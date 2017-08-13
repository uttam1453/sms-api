package com.appster.sms.api.common.excecption;

import com.appster.sms.api.common.AppException;

/**
 * Created by atul on 26/01/17.
 */
public class FacebookTokenValidationException extends AppException {
    private int fbErrorCode;
    private String message;
    private String token;

    public FacebookTokenValidationException(String errorCode, int fbErrorCode, String message, String token) {
        super(errorCode);
        this.fbErrorCode = fbErrorCode;
        this.message = message;
        this.token = token;
    }

    public int getFbErrorCode() {
        return fbErrorCode;
    }

    public void setFbErrorCode(int fbErrorCode) {
        this.fbErrorCode = fbErrorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
