package com.myschool.sms.api.common.model;

import com.myschool.sms.api.common.response.Payload;

/**
 * created by lokesh on 11/01/17.
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
