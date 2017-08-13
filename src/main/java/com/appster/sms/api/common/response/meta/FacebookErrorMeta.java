package com.appster.sms.api.common.response.meta;

/**
 * Created by atul on 26/01/17.
 */
public class FacebookErrorMeta extends ErrorMeta {
    private int fbErrorCode;
    private String fbMsg;
    private String token;

    public FacebookErrorMeta(int code, String message, String type, int fbErrorCode, String fbMsg, String token) {
        super(code, message, type);
        this.fbErrorCode = fbErrorCode;
        this.fbMsg = fbMsg;
        this.token = token;
    }

    public int getFbErrorCode() {
        return fbErrorCode;
    }

    public void setFbErrorCode(int fbErrorCode) {
        this.fbErrorCode = fbErrorCode;
    }

    public String getFbMsg() {
        return fbMsg;
    }

    public void setFbMsg(String fbMsg) {
        this.fbMsg = fbMsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
