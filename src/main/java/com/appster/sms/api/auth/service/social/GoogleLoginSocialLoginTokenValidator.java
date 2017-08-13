package com.appster.sms.api.auth.service.social;

import com.appster.sms.api.common.AppException;
import com.appster.sms.api.common.constants.SocialSignInType;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

/**
 * Created by atul on 07/02/17.
 */
@Component
public class GoogleLoginSocialLoginTokenValidator implements SocialLoginTokenValidator {
    public static final Logger LOG = LoggerFactory.getLogger(GoogleLoginSocialLoginTokenValidator.class);
    /**
     * Default HTTP transport to use to make HTTP requests.
     */
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    /**
     * Default JSON factory to use to deserialize JSON.
     */
    private static final JacksonFactory JSON_FACTORY = new JacksonFactory();
    @Autowired
    private Environment env;

    @Override
    public ValidatedToken validatedDetails(AuthTokenValidatable authTokenValidatable) {
        TurtleValidatedToken validatedToken = new TurtleValidatedToken();
        validatedToken.setSrc(SocialSignInType.GOOGLE.toString());
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(TRANSPORT, JSON_FACTORY)
                    .setAudience(Collections.singletonList(env.getProperty("GOOGLE.CLIENT_ID"))).setIssuer("https://accounts.google.com")
                    .build();
            String[] tokens = authTokenValidatable.tokenToValidate().split("\\$#\\$");
            if (tokens.length != 2)
                throw new AppException("INVALID_TOKEN");
            GoogleIdToken idToken = verifier.verify(tokens[0]);


            if (idToken == null)
                throw new AppException("INVALID.G_ID_TOKEN");

            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            if (email == null || email.trim().isEmpty())
                throw new AppException("EMAIL_PERMISSION");
            validatedToken.setSrc(SocialSignInType.GOOGLE.toString());
            validatedToken.setValid(true);
            validatedToken.setSocialId(payload.getSubject());
            validatedToken.setEmail(email);
        } catch (IOException io) {
            LOG.error("Error validation token for  " + authTokenValidatable.getSocialSignInTyep() + " and token = " + authTokenValidatable.tokenToValidate(), io);
        } catch (GeneralSecurityException ge) {
            LOG.error("Security exception for   " + authTokenValidatable.getSocialSignInTyep() + " and token = " + authTokenValidatable.tokenToValidate(), ge);

        }
        return validatedToken;
    }


}
