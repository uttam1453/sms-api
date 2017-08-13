package com.appster.sms.api.auth.model;

import com.appster.sms.api.common.model.UserBaseModel;

/**
 * Created by ishu on 06/01/17.
 */
public class LoginModel extends AuthTokenModel {
    private UserBaseModel userBaseModel;

    public LoginModel(UserBaseModel userBaseModel, String token, String notificationSetting) {
        super(token);
        this.userBaseModel = userBaseModel;
    }

    public UserBaseModel getUserBaseModel() {
        return userBaseModel;
    }

    public void setUserBaseModel(UserBaseModel userBaseModel) {
        this.userBaseModel = userBaseModel;
    }
}
