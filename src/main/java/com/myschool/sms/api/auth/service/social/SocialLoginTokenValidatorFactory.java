package com.myschool.sms.api.auth.service.social;

import com.myschool.sms.api.common.AppException;
import com.myschool.sms.api.common.constants.SocialSignInType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lokesh on 25/01/17.
 */
@Component
public class SocialLoginTokenValidatorFactory {
    @Autowired
    private FacebookLoginSocialLoginTokenValidator facebookLoginSocialLoginTokenValidator;
    @Autowired
    private GoogleLoginSocialLoginTokenValidator googleLoginSocialLoginTokenValidator;

    public SocialLoginTokenValidator getValidator(SocialSignInType socialSignInType) {
        if (socialSignInType.equals(SocialSignInType.FACEBOOK))
            return facebookLoginSocialLoginTokenValidator;
        else if (socialSignInType.equals(SocialSignInType.GOOGLE))
            return googleLoginSocialLoginTokenValidator;

        throw new AppException("INVALID_SOCIAL_LOGIN");

    }
}
