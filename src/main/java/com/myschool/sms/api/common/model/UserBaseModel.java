package com.myschool.sms.api.common.model;

import java.util.ArrayList;
import java.util.List;

import com.myschool.sms.api.common.response.Payload;
import com.myschool.sms.config.AppConst;

//import com.appster.api.common.model.master.MasterCampusModel;

//import com.appster.api.common.model.master.MasterCampusModel;

/**
 * Created by ishu on 09/01/17.
 */
public class UserBaseModel implements Payload {
    private int userId;
    private String name;
    private String email;
    private boolean activeFlag;
    private Double age;
    private AppConst.GENDER gender;
    private List<Integer> roles = new ArrayList<>();

    public UserBaseModel() {
    }

    public UserBaseModel(String email) {
        this.email = email;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public AppConst.GENDER getGender() {
        return gender;
    }

    public void setGender(AppConst.GENDER gender) {
        this.gender = gender;
    }


}
