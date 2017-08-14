package com.myschool.sms.api.auth.service.social;

import com.myschool.sms.api.common.constants.SocialSignInType;

/**
 * Created by lokesh on 25/01/17.
 */
public interface AuthTokenValidatable {
    String tokenToValidate();

    SocialSignInType getSocialSignInTyep();
}
