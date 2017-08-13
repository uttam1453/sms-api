package com.appster.sms.api.ping;

import com.appster.sms.api.common.response.Payload;

/**
 * created by atul on 29/12/16.
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
