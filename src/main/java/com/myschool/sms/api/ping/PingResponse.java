package com.myschool.sms.api.ping;

import com.myschool.sms.api.common.response.Payload;

/**
 * created by lokesh on 29/12/16.
 */

public class PingResponse implements Payload {
    private String message;

    public PingResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
