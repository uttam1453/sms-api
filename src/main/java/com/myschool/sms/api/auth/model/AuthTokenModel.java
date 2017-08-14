package com.myschool.sms.api.auth.model;

import com.myschool.sms.api.common.response.Payload;

/**
 * Created by lokesh.
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
