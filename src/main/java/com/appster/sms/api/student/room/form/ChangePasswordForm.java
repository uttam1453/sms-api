package com.appster.sms.api.student.room.form;

import com.appster.sms.api.common.validator.FieldMatch;
import com.appster.sms.validation.FirstLevelValidation;
import com.appster.sms.validation.SecondLevelValidation;

import javax.validation.GroupSequence;
import javax.validation.constraints.Size;

/**
 * Created by atul on 11/01/17.
 */
@GroupSequence({FirstLevelValidation.class, SecondLevelValidation.class, ChangePasswordForm.class})
@FieldMatch.List({
        @FieldMatch(first = "newPass", second = "confirmPass", message = "{V.PASSWORD_MISMATCH}", groups = SecondLevelValidation.class)
})
public class ChangePasswordForm {

    @Size.List({
            @Size(min = 6, message = "{V.PASSWORD.MIN_SIZE}", groups = FirstLevelValidation.class),
            @Size(max = 45, message = "{V.PASSWORD.MAX_SIZE}", groups = FirstLevelValidation.class)
    })
    private String oldPass;
    @Size.List({
            @Size(min = 6, message = "{V.PASSWORD.MIN_SIZE}", groups = FirstLevelValidation.class),
            @Size(max = 45, message = "{V.PASSWORD.MAX_SIZE}", groups = FirstLevelValidation.class)
    })
    private String newPass;
    @Size.List({
            @Size(min = 6, message = "{V.PASSWORD.MIN_SIZE}", groups = FirstLevelValidation.class),
            @Size(max = 45, message = "{V.PASSWORD.MAX_SIZE}", groups = FirstLevelValidation.class)
    })
    private String confirmPass;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}
