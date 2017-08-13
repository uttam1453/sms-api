package com.appster.sms.api.auth.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * created by atul on 05/01/17.
 */
public class EmailForm {
    @Email
    @Length(max = 40)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
