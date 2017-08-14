package com.myschool.sms.api.auth.service;


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

import com.myschool.sms.api.auth.service.jwt.JwtTokenUtil;
import com.myschool.sms.api.auth.service.jwt.JwtUserFactory;
import com.myschool.sms.api.auth.service.social.SocialLoginTokenValidatorFactory;
import com.myschool.sms.api.common.AppException;
import com.myschool.sms.api.common.service.ValidationService;
import com.myschool.sms.api.user.service.UserService;
import com.myschool.sms.entity.UserEntity;
import com.myschool.sms.entity.UserSessionDetail;
import com.myschool.sms.repo.SessionRepo;
import com.myschool.sms.repo.UserRepo;

/**
 * Created by lokesh on 05/01/17.
 */
@Service
public class AuthService {
    public static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private MasterRoleRepo masterRoleRepo;
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
//    @Autowired
//    private UserRoleRepo userRoleRepo;

//    @Transactional
//    public UserEntity signup(SignUpCredentialForm signUpCredentialForm) {
//        if (userRepo.checkEmailExistence(signUpCredentialForm.getEmail()) != null)
//            throw new AppException("AUTH.EMAIL_ALREADY_EXIST");
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail(signUpCredentialForm.getEmail());
//        userEntity.setPassword(passwordEncoder.encode(signUpCredentialForm.getPassword()));
//        userEntity.setProfileStatus(UserEntity.ACCOUNT_STATUS.IN_PROGRESS);
//        userEntity.setFirstName(signUpCredentialForm.getFirstName());
//        userEntity.setLastName(signUpCredentialForm.getLastName());
//        userEntity.setGender(signUpCredentialForm.getGender());
//        MasterRoleEntity userMasterRole = masterRoleRepo.getActiveRole(UserRoleEnum.STUDENT.name());
//        UserRoleEntity userRoleEntity = new UserRoleEntity(new UserRolePK(userEntity, userMasterRole));
//        userEntity.setUserRoles(new ArrayList<>(Arrays.asList(userRoleEntity)));
//        userEntity = userRepo.saveAndFlush(userEntity);
//        userRoleRepo.save(userRoleEntity);
//        return userEntity;
//    }

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
    public int signDetailToSessionEntity(String deviceToken, UserEntity user) {
        sessionRepo.deleteSessionDetailsByUserId(user.getId());
        sessionRepo.deleteSessionDetailsByToken(deviceToken);
        UserSessionDetail sessionEntity = new UserSessionDetail();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +14);
        sessionEntity.setDeviceToken(deviceToken);
        sessionEntity.setUserMst(user);
        sessionEntity = sessionRepo.save(sessionEntity);
        return sessionEntity.getId();
    }
}
