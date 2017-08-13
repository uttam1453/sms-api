package com.appster.sms.api.auth.form;

import com.appster.sms.config.AppConst;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by atul.
 */
public class CredentialsForm extends EmailForm {

    @NotEmpty
    @Length(min = 6, max = 20)
    private String password;
    // private int campusId;
    private String deviceToken = AppConst.EMPTY_STRING;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /* public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }*/

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
