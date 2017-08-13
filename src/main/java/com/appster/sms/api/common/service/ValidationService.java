package com.appster.sms.api.common.service;

import com.appster.sms.api.common.AppException;
import com.appster.sms.entity.UserEntity;
import com.appster.sms.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if (userEntity.getProfileStatus() == UserEntity.ACCOUNT_STATUS.SUSPENDED)
            throw new AppException("ACCOUNT_INACTIVE");
    }





}
