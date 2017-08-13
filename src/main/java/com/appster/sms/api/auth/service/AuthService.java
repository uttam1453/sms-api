package com.appster.sms.api.auth.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appster.sms.api.auth.form.SignUpCredentialForm;
import com.appster.sms.api.auth.form.SocialSignInForm;
import com.appster.sms.api.auth.service.jwt.JwtTokenUtil;
import com.appster.sms.api.auth.service.jwt.JwtUserFactory;
import com.appster.sms.api.auth.service.social.SocialLoginTokenValidator;
import com.appster.sms.api.auth.service.social.SocialLoginTokenValidatorFactory;
import com.appster.sms.api.auth.service.social.TurtleSocialTokenToValidate;
import com.appster.sms.api.auth.service.social.TurtleValidatedToken;
import com.appster.sms.api.common.AppException;
import com.appster.sms.api.common.constants.SocialSignInType;
import com.appster.sms.api.common.constants.UserRoleEnum;
import com.appster.sms.api.common.service.ValidationService;
import com.appster.sms.api.user.service.UserService;
import com.appster.sms.config.AppConst;
import com.appster.sms.entity.MasterRoleEntity;
import com.appster.sms.entity.SessionEntity;
import com.appster.sms.entity.UserEntity;
import com.appster.sms.entity.UserRoleEntity;
import com.appster.sms.entity.embeddedId.UserRolePK;
import com.appster.sms.repo.MasterRoleRepo;
import com.appster.sms.repo.SessionRepo;
import com.appster.sms.repo.UserRepo;
import com.appster.sms.repo.UserRoleRepo;

/**
 * Created by atul on 05/01/17.
 */
@Service
public class AuthService {
    public static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MasterRoleRepo masterRoleRepo;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SocialLoginTokenValidatorFactory socialLoginTokenValidatorFactory;
    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private UserRoleRepo userRoleRepo;

    @Transactional
    public UserEntity signup(SignUpCredentialForm signUpCredentialForm) {
        if (userRepo.checkEmailExistence(signUpCredentialForm.getEmail()) != null)
            throw new AppException("AUTH.EMAIL_ALREADY_EXIST");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpCredentialForm.getEmail());
        userEntity.setPassword(passwordEncoder.encode(signUpCredentialForm.getPassword()));
        userEntity.setProfileStatus(UserEntity.ACCOUNT_STATUS.IN_PROGRESS);
        userEntity.setFirstName(signUpCredentialForm.getFirstName());
        userEntity.setLastName(signUpCredentialForm.getLastName());
        userEntity.setGender(signUpCredentialForm.getGender());
        MasterRoleEntity userMasterRole = masterRoleRepo.getActiveRole(UserRoleEnum.STUDENT.name());
        UserRoleEntity userRoleEntity = new UserRoleEntity(new UserRolePK(userEntity, userMasterRole));
        userEntity.setUserRoles(new ArrayList<>(Arrays.asList(userRoleEntity)));
        userEntity = userRepo.saveAndFlush(userEntity);
        userRoleRepo.save(userRoleEntity);
        return userEntity;
    }

    @Transactional
    public AuthInfoHolder signin(String email, String password, String deviceToken) throws BadCredentialsException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            LOG.error("Invalid credential for email " + email, e);
            throw new AppException("AUTH.BAD_CREDENTIALS");
        }

        UserEntity userEntity = validationService.validateAndGetActiveUser(email);

        int deviceId = signDetailToSessionEntity(deviceToken, userEntity);
        String token = jwtTokenUtil.generateToken(JwtUserFactory.create(userEntity, deviceId));
        return new AuthInfoHolder(userEntity, token);
    }

    @Transactional
    public AuthInfoHolder socialSignIn(SocialSignInForm socialSignInForm) throws BadCredentialsException {
        //Create the SocialSignUp type enum from string and get the validator object
        SocialSignInType socialSignInType = SocialSignInType.fromString(socialSignInForm.getSrc());
        SocialLoginTokenValidator socialLoginTokenValidator = socialLoginTokenValidatorFactory.getValidator(socialSignInType);
        //Create the AuthTokenValidatable object
        TurtleSocialTokenToValidate turtleSocialTokenToValidate = new TurtleSocialTokenToValidate(socialSignInForm.getAccessToken(), socialSignInType);
        TurtleValidatedToken turtleValidatedToken = socialLoginTokenValidator.validatedDetails(turtleSocialTokenToValidate);
        // check if validated details matches the email and srcId
        if (!turtleValidatedToken.socialId().equals(socialSignInForm.getSrcId()) || !turtleValidatedToken.email().equals(socialSignInForm.getEmail()))
            throw new AppException("TOKEN_DETAILS_MISMATCHED");
        //check if email is registered with us
        UserEntity userEntity = userRepo.checkEmailExistence(socialSignInForm.getEmail());
        // if not registered then register
        if (userEntity == null) {
            userEntity = userService.createNewUser(socialSignInForm.getEmail(), AppConst.EMPTY_STRING, UserRoleEnum.STUDENT.toString());
            userEntity = userService.assignSignUpSource(userEntity, turtleValidatedToken.source(), turtleValidatedToken.socialId());
        } else if (!userService.hasAlreadySignedUp(userEntity, turtleValidatedToken.source(), turtleValidatedToken.socialId())) {
            userEntity = userService.assignSignUpSource(userEntity, turtleValidatedToken.source(), turtleValidatedToken.socialId());
            userRepo.save(userEntity);
        }
        validationService.validateUser(userEntity);
        int deviceId = signDetailToSessionEntity(socialSignInForm.getDeviceToken(), userEntity);
        String token = jwtTokenUtil.generateToken(JwtUserFactory.create(userEntity, deviceId));

        return new AuthInfoHolder(userEntity, token);
    }


    @Transactional
    public int signDetailToSessionEntity(String deviceToken, UserEntity user) {
        sessionRepo.deleteSessionDetailsByUserId(user.getId());
        sessionRepo.deleteSessionDetailsByToken(deviceToken);
        SessionEntity sessionEntity = new SessionEntity();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +14);
        sessionEntity.setDeviceToken(deviceToken);
        sessionEntity.setUserEntity(user);
        sessionEntity = sessionRepo.save(sessionEntity);
        return sessionEntity.getId();
    }
}
