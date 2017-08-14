package com.myschool.sms.api.common.model.master;

import com.myschool.sms.api.common.response.Payload;

/**
 * created by lokesh on 13/01/17.
 */
public class MasterBaseModel implements Payload {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
