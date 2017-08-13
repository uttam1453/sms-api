package com.appster.sms.api.student.room.service;

import com.appster.sms.api.auth.form.UserDetailsModel;
import com.appster.sms.api.auth.service.jwt.JwtTokenUtil;
import com.appster.sms.api.auth.service.jwt.JwtUserFactory;
import com.appster.sms.api.common.AppException;
import com.appster.sms.api.common.model.UserBaseModel;
import com.appster.sms.api.common.model.UserCompleteInfoModel;
import com.appster.sms.api.common.model.Builder.UserInfoBuilder;
import com.appster.sms.api.common.service.ValidationService;
import com.appster.sms.config.AppConst;
import com.appster.sms.entity.SessionEntity;
import com.appster.sms.entity.UserEntity;
import com.appster.sms.repo.SessionRepo;
import com.appster.sms.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Created by Harsh on 03/06/17.
 */

/**
 * Created by Harsh on 03/06/17.
 */

@Service
public class ProfileService {
    @Autowired
    ValidationService validationService;
    @Autowired
    UserInfoBuilder userInfoBuilder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SessionRepo sessionRepo;


    @Transactional
    public UserBaseModel getProfile(int id) {
        UserEntity userEntity = validationService.validateAndGetUser(id);
        return userInfoBuilder.userInfo(userEntity);
    }


    /*@Transactional
    public UserCompleteInfoModel updateProfile2(int id,UserDetailsModel userDetailsModel) throws IOException {
        UserEntity userEntity = validationService.validateAndGetUser(id);

        String name = userDetailsModel.getName();
        if (name == null) name = AppConst.EMPTY_STRING;
        userEntity.setFirstName(name);

        System.out.println("new val:: "+userDetailsModel.getEmail() +" db val: "+ userEntity.getEmail() );
        System.out.println(":::db != new emai:"+(userDetailsModel.getEmail() != userEntity.getEmail()) );
        System.out.println(":::db == new emai:"+(userDetailsModel.getEmail() == userEntity.getEmail() ));
        System.out.println(":::db.equal( new emai):"+(userDetailsModel.getEmail().equals(userEntity.getEmail())) );

        if (userDetailsModel.getEmail() != null && !(userDetailsModel.getEmail().equals(userEntity.getEmail()))) {
            System.out.println("inside");

            if (userRepo.checkEmailExistence(userDetailsModel.getEmail()) != null)
                throw new AppException("AUTH.EMAIL_ALREADY_EXIST");
            userEntity.setEmail(userDetailsModel.getEmail());
        }
        userEntity.setAge(userDetailsModel.getAge());
        //userEntity.setGender(userDetailsModel.getGender());

        System.out.println("111");

        return userInfoBuilder.userCompleteInfoModel(userRepo.saveAndFlush(userEntity));

    }*/
    @Transactional
    public UserCompleteInfoModel updateProfile(int id, UserDetailsModel userDetailsModel) throws IOException {
        UserEntity userEntity = validationService.validateAndGetUser(id);
        System.out.println("11111");

        String name = userDetailsModel.getName();
        if (name == null) name = AppConst.EMPTY_STRING;
        userEntity.setFirstName(name);
        userEntity.setGender(userDetailsModel.getGender());
        System.out.println("2222");

        return userInfoBuilder.userCompleteInfoModel(userRepo.saveAndFlush(userEntity));

    }

    @Transactional
    public String changePassword(int id, char[] oldPassword, char[] newPass) {
        UserEntity userEntity = validationService.validateAndGetUser(id);
        if (!passwordEncoder.matches(CharBuffer.wrap(oldPassword), userEntity.getPassword()))
            throw new AppException("INCORRECT.OLD_PASSWORD");
        userEntity.setPassword(passwordEncoder.encode(CharBuffer.wrap(newPass)));
        userEntity = userRepo.save(userEntity);
        int deviceId = 0;
        SessionEntity sessionEntity = sessionRepo.findByUserId(userEntity.getId());
        if (sessionEntity != null)
            deviceId = sessionEntity.getId();
        return jwtTokenUtil.generateToken(JwtUserFactory.create(userEntity, deviceId));
    }


}
