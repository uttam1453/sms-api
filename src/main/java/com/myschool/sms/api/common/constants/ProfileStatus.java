package com.myschool.sms.api.common.constants;

/**
 * Created by lokesh on 17/02/17.
 */
public enum ProfileStatus {

    /**
     * CC=Card Complete
     * PF=Profile Complete
     * NC=Not Complete
     * BC=Bank Complete
     * FC=Full Complete
     */
    CC("CC"), PC("PF"), NC("NC"), BC("BC"), FC("FC");
    private final String userProfileStatus;

    ProfileStatus(String userProfileStatus) {
        this.userProfileStatus = userProfileStatus;
    }

    public static ProfileStatus fromString(String profileStatus) {
        if (profileStatus != null) {
            for (ProfileStatus ps : ProfileStatus.values()) {
                if (profileStatus.equalsIgnoreCase(ps.userProfileStatus)) {
                    return ps;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return userProfileStatus;
    }
}
