package com.appster.sms.api.common.constants;

/**
 * Created by atul on 24/01/17.
 */
public enum SocialSignInType {
    FACEBOOK("FB"),
    GOOGLE("GOOGLE");
    private final String signInType;

    SocialSignInType(String signInType) {
        this.signInType = signInType;
    }

    public static SocialSignInType fromString(String signInType) {
        if (signInType != null) {
            for (SocialSignInType sst : SocialSignInType.values()) {
                if (signInType.equalsIgnoreCase(sst.signInType)) {
                    return sst;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.signInType;
    }
}
