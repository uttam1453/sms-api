package com.myschool.sms.api.common.constants;

/**
 * created by lokesh on 05/01/17.
 */
public enum UserRoleEnum {
    STUDENT("STUDENT"),
    SUPER_ADMIN("ADVERTISER"),
    ADMIN("ADMIN");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
