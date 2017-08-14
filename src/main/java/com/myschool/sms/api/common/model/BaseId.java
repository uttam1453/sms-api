package com.myschool.sms.api.common.model;

import com.myschool.sms.api.common.response.Payload;

/**
 * created by lokesh on 09/01/17.
 */
public class BaseId implements Payload {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
