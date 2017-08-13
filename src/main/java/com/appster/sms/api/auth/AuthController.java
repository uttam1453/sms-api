package com.appster.sms.api.auth;

import io.swagger.annotations.Api;

import java.nio.CharBuffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appster.sms.api.auth.form.CredentialsForm;
import com.appster.sms.api.auth.form.SignUpCredentialForm;
import com.appster.sms.api.auth.form.SocialSignInForm;
import com.appster.sms.api.auth.model.LoginModel;
import com.appster.sms.api.auth.service.AuthInfoHolder;
import com.appster.sms.api.auth.service.AuthService;
import com.appster.sms.api.common.model.Builder.UserInfoBuilder;
import com.appster.sms.api.common.response.envelope.SuccessResponseEnvelope;
import com.appster.sms.api.student.room.service.ProfileService;
import com.appster.sms.config.AppConst;
import com.appster.sms.entity.UserEntity;
import com.appster.sms.repo.UserRepo;


/**
 * created by atul on 28/12/16.
 */
@RestController
@RequestMapping("/api/v1/auth")
@Api(value = "Auth", description = "Authentication Related APIs")
public class AuthController {


    @Autowired
    ProfileService userProfileService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserInfoBuilder userInfoBuilder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Environment env;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public SuccessResponseEnvelope<LoginModel> signup(@RequestBody @Valid SignUpCredentialForm credentialsForm) {
        String password = credentialsForm.getPassword();
        String email = credentialsForm.getEmail();
        authService.signup(credentialsForm);
        AuthInfoHolder holder = authService.signin(email, password, credentialsForm.getDeviceToken());
        LoginModel authTokenModelModel = new LoginModel(userInfoBuilder.userInfo(holder.getUserEntity()), holder.getToken(), holder.getUserEntity().getFirstName());
        return new SuccessResponseEnvelope<>(authTokenModelModel);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public SuccessResponseEnvelope<LoginModel> login(@RequestBody @Valid CredentialsForm credentialsForm) {
        AuthInfoHolder holder = authService.signin(credentialsForm.getEmail(), credentialsForm.getPassword(), credentialsForm.getDeviceToken());

        LoginModel authTokenModelModel = new LoginModel(userInfoBuilder.userInfo(holder.getUserEntity()), holder.getToken(), holder.getUserEntity().getFirstName());

        return new SuccessResponseEnvelope<>(authTokenModelModel);
    }

    @RequestMapping(value = "/socialSignIn", method = RequestMethod.POST)
    public SuccessResponseEnvelope<LoginModel> socialSignIn(@RequestBody @Valid SocialSignInForm socialSignInForm) {
        AuthInfoHolder holder = authService.socialSignIn(socialSignInForm);
        LoginModel authTokenModelModel = new LoginModel(userInfoBuilder.userInfo(holder.getUserEntity()), holder.getToken(), holder.getUserEntity().getFirstName());
        return new SuccessResponseEnvelope<>(authTokenModelModel);
    }

    @RequestMapping(value = "/updatePassword",  method = RequestMethod.POST)
    @Transactional
    public ModelAndView updatePassword(HttpServletRequest request,
                                       HttpServletResponse res, HttpSession session) {
        if (session.getAttribute(env.getProperty("RESET_PASS_SESSION_ATTRIBUTE.KEY")) != null) {
            int id = (int) session.getAttribute(env.getProperty("RESET_PASS_SESSION_ATTRIBUTE.KEY"));
            String password = request.getParameter("newPassword");
            UserEntity userEntity = userRepo.findOne(id);
            if (userEntity != null) {
                userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(password)));
                userEntity.getForgotPassword().setVerificationKey(AppConst.EMPTY_STRING);
                userEntity.setPassResetRequired(Boolean.FALSE);
                userRepo.save(userEntity);
                session.setAttribute(env.getProperty("RESET_PASS_SESSION_ATTRIBUTE.KEY"), null);
            }
            return new ModelAndView("verifyPassword");
        }
        return new ModelAndView("verifyEmailError");
    }


}
