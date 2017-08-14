package com.myschool.sms.api.auth.form;

import com.myschool.sms.config.AppConst;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lokesh.
 */
public class CredentialsForm extends EmailForm {

	private int roleId;
	
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

    public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
