package com.appster.sms.api.auth.model;

import com.appster.sms.api.common.response.Payload;

/**
 * Created by atul.
 */
public class AuthTokenModel implements Payload {
    private String token;

    public AuthTokenModel() {
        //need default constructor for spring for serialization
    }

    public AuthTokenModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
