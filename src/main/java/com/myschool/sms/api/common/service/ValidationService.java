package com.myschool.sms.api.common.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myschool.sms.api.common.AppException;
import com.myschool.sms.entity.UserEntity;
import com.myschool.sms.repo.UserRepo;

/**
 * Created by ishu on 09/01/17.
 */
@Service
public class ValidationService {

    @Autowired
    private UserRepo userRepo;


    public UserEntity validateAndGetUser(final int id) {
        Optional<UserEntity> userEntity = userRepo.findById(id);
        return userEntity.orElseThrow(() -> new AppException("USER.NOT_FOUND"));
    }

    public UserEntity validateAndGetActiveUser(final String email) {
        UserEntity userEntity = userRepo.checkEmailExistence(email);
        validateUser(userEntity);
        return userEntity;
    }

    public void validateUser(UserEntity userEntity) {
        if (userEntity == null)
            throw new AppException("USER.NOT_FOUND");
        if (!userEntity.getActiveFlag())
            throw new AppException("ACCOUNT_INACTIVE");
    }





}
