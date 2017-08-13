package com.appster.sms.api.auth.service.social;

/**
 * Created by atul on 25/01/17.
 */
public interface ValidatedToken {
    String email();

    long expiresAt();

    String socialId();

    boolean isValid();

    String source();
}
