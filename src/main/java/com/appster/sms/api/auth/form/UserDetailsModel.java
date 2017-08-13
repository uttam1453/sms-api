package com.appster.sms.api.auth.form;

import com.appster.sms.api.common.response.Payload;
import com.appster.sms.entity.UserEntity.GENDER;

import javax.validation.constraints.NotNull;

public class UserDetailsModel implements Payload {
    @NotNull
    private String name;
    @NotNull
    private Double age;
    @NotNull
    private GENDER gender;


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

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }


}
