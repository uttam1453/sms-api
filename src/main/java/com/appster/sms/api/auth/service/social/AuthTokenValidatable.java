package com.appster.sms.api.auth.service.social;

import com.appster.sms.api.common.constants.SocialSignInType;

/**
 * Created by atul on 25/01/17.
 */
public interface AuthTokenValidatable {
    String tokenToValidate();

    SocialSignInType getSocialSignInTyep();
}
