package com.appster.sms.api.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * created by atul on 15/02/17.
 */
public class ForgotPasswordEvent extends ApplicationEvent {

    private String verificationCode;
    private String userName;
    private String userMail;
    private int userId;

    public ForgotPasswordEvent(Object source) {
        super(source);
    }

    public ForgotPasswordEvent(Object source, String verificationCode, String userName, String userMail, int userId) {
        super(source);
        this.verificationCode = verificationCode;
        this.userName = userName;
        this.userMail = userMail;
        this.userId = userId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
