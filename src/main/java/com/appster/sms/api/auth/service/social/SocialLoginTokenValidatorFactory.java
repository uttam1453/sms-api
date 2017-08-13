package com.appster.sms.api.auth.service.social;

import com.appster.sms.api.common.AppException;
import com.appster.sms.api.common.constants.SocialSignInType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by atul on 25/01/17.
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
