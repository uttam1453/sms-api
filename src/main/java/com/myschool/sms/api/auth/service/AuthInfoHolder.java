package com.myschool.sms.api.auth.service;

import com.myschool.sms.entity.UserEntity;

/**
 * created by lokesh on 20/01/17.
 */
public class AuthInfoHolder {
    private UserEntity userEntity;
    private String token;

    public AuthInfoHolder(UserEntity userEntity, String token) {
        this.userEntity = userEntity;
        this.token = token;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
