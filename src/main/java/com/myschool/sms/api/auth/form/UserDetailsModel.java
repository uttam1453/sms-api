package com.myschool.sms.api.auth.form;

import javax.validation.constraints.NotNull;

import com.myschool.sms.api.common.response.Payload;
import com.myschool.sms.config.AppConst;

public class UserDetailsModel implements Payload {
    @NotNull
    private String name;
    @NotNull
    private Double age;
    @NotNull
    private AppConst.GENDER gender;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
