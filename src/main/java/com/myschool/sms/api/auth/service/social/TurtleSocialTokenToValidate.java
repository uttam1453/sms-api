package com.myschool.sms.api.auth.service.social;

import com.myschool.sms.api.common.constants.SocialSignInType;

public class TurtleSocialTokenToValidate implements AuthTokenValidatable {
    private String tokenToValidate;
    private SocialSignInType socialSignInType;

    public TurtleSocialTokenToValidate(String tokenToValidate) {
        this.tokenToValidate = tokenToValidate;
    }

    public TurtleSocialTokenToValidate(String tokenToValidate, SocialSignInType socialSignInType) {
        this.tokenToValidate = tokenToValidate;
        this.socialSignInType = socialSignInType;
    }


    public String getTokenToValidate() {
        return tokenToValidate;
    }

    public void setTokenToValidate(String tokenToValidate) {
        this.tokenToValidate = tokenToValidate;
    }


    @Override
    public String tokenToValidate() {
        return tokenToValidate;
    }

    @Override
    public SocialSignInType getSocialSignInTyep() {
        return socialSignInType;
    }
}
