package com.myschool.sms.api.auth.form;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.myschool.sms.config.AppConst;

/**
 * Created by dheeraj on 23/03/17.
 */
public class SignUpCredentialForm extends EmailForm {

    @NotEmpty
    @Length(min = 6, max = 20)
    private String password;
    private String deviceToken;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private double age;
    @NotNull
    private AppConst.GENDER gender;
    private List<Integer> segmentIds;
    @NotNull
    private int universityId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public AppConst.GENDER getGender() {
        return gender;
    }

    public void setGender(AppConst.GENDER gender) {
        this.gender = gender;
    }

    public List<Integer> getSegmentIds() {
        return segmentIds;
    }

    public void setSegmentIds(List<Integer> segmentIds) {
        this.segmentIds = segmentIds;
    }
}
