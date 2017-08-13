package com.appster.sms.api.auth.service.social;

/**
 * Created by atul on 25/01/17.
 */
public interface SocialLoginTokenValidator {
    <T extends ValidatedToken> T validatedDetails(AuthTokenValidatable authTokenValidatable);
}
