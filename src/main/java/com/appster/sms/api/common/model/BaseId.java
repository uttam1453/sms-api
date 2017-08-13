package com.appster.sms.api.common.model;

import com.appster.sms.api.common.response.Payload;

/**
 * created by atul on 09/01/17.
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
