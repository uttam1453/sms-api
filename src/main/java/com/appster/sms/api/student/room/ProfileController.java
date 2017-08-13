package com.appster.sms.api.student.room;

import com.appster.sms.api.auth.form.UserDetailsModel;
import com.appster.sms.api.auth.model.AuthTokenModel;
import com.appster.sms.api.auth.service.jwt.JwtUser;
import com.appster.sms.api.common.model.UserBaseModel;
import com.appster.sms.api.common.model.UserCompleteInfoModel;
import com.appster.sms.api.common.model.Builder.UserInfoBuilder;
import com.appster.sms.api.common.response.envelope.SuccessResponseEnvelope;
import com.appster.sms.api.student.room.form.ChangePasswordForm;
import com.appster.sms.api.student.room.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user/profile/")
public class ProfileController {
    @Autowired
    ProfileService userProfileService;
    @Autowired
    UserInfoBuilder userInfoBuilder;

    @GetMapping("/{id}")
    public SuccessResponseEnvelope<UserBaseModel> getProfile(@PathVariable("id") int id) {
        return new SuccessResponseEnvelope<>(userProfileService.getProfile(id));
    }

    @PostMapping(value = {"{id}", "/"})
    public SuccessResponseEnvelope<UserCompleteInfoModel> updateProfile(@RequestBody @Valid UserDetailsModel userInfo, JwtUser userDetails) throws IOException {
        return new SuccessResponseEnvelope<>(userProfileService.updateProfile(userDetails.getId(), userInfo));
    }

    @PostMapping("/changePassword")
    public SuccessResponseEnvelope<AuthTokenModel> changePassword(@RequestBody @Valid ChangePasswordForm changePasswordForm, JwtUser userDetails) {
        String authToken = userProfileService.changePassword(userDetails.getId(), changePasswordForm.getOldPass().toCharArray(), changePasswordForm.getNewPass().toCharArray());
        AuthTokenModel authTokenModel = new AuthTokenModel(authToken);
        return new SuccessResponseEnvelope<>(authTokenModel);
    }


}
