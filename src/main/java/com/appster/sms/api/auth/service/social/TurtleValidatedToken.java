package com.appster.sms.api.auth.service.social;

public class TurtleValidatedToken implements ValidatedToken {
    private String email;
    private long expiresAt;
    private String socialId;
    private String src;
    private boolean isValid;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public long expiresAt() {
        return expiresAt;
    }

    @Override
    public String socialId() {
        return socialId;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String source() {
        return src;
    }
}
