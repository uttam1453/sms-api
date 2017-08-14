package com.myschool.sms.api.common.constants;

/**
 * Created by lokesh on 26/01/17.
 */
public enum MasterSignUpType {
    NORMAL("NORMAL"),
    FACEBOOK("FB"),
    GOOGLE("GOOGLE");

    private final String signUpType;

    MasterSignUpType(String signUpType) {
        this.signUpType = signUpType;
    }

    public static MasterSignUpType fromString(String signUpType) {
        if (signUpType != null) {
            for (MasterSignUpType sst : MasterSignUpType.values()) {
                if (signUpType.equalsIgnoreCase(sst.signUpType)) {
                    return sst;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.signUpType;
    }
}
