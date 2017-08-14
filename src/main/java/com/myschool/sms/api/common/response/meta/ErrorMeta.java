package com.myschool.sms.api.common.response.meta;

/**
 * created by lokesh on 28/12/16.
 */

public class ErrorMeta extends Meta {
    private String message;
    private String type;

    public ErrorMeta(int code, String message, String type) {
        super(code);
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
