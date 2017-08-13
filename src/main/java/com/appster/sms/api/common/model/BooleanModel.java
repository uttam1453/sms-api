package com.appster.sms.api.common.model;

import com.appster.sms.api.common.response.Payload;

/**
 * created by atul on 11/01/17.
 */
public class BooleanModel implements Payload {
    boolean status;

    public BooleanModel(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
