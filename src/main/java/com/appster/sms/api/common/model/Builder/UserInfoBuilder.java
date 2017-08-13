package com.appster.sms.api.common.model.Builder;

import com.appster.sms.api.common.model.UserBaseModel;
import com.appster.sms.api.common.model.UserCompleteInfoModel;
import com.appster.sms.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ishu on 09/01/17.
 */
@Component
public class UserInfoBuilder {
    @Autowired
    private MasterModelsBuilder masterModelsBuilder;

    public UserBaseModel userInfo(UserEntity userEntity) {
        UserBaseModel userInfo = new UserBaseModel();
        userInfo.setName(userEntity.getFirstName());
        userInfo.setUserId(userEntity.getId());
//        userInfo.setAge(userEntity.getAge());
        userInfo.setEmail(userEntity.getEmail());
        userInfo.setProfileStatus(userEntity.getProfileStatus());
        userInfo.setGender(userEntity.getGender());
        userEntity.getUserRoles().forEach(userRoleEntity -> userInfo.getRoles().add(userRoleEntity.getUserRolePK().getMasterRoleEntity().getId()));
        return userInfo;
    }

    public UserCompleteInfoModel userCompleteInfoModel(UserEntity userEntity) {
        UserCompleteInfoModel userInfo = new UserCompleteInfoModel();

        userInfo.setName(userEntity.getFirstName());
        userInfo.setUserId(userEntity.getId());
        userInfo.setEmail(userEntity.getEmail());
//        userInfo.setAge(userEntity.getAge());
        userInfo.setGender(userEntity.getGender());
        userEntity.getUserRoles().forEach(userRoleEntity -> userInfo.getRoles().add(userRoleEntity.getUserRolePK().getMasterRoleEntity().getId()));
        userInfo.setProfileStatus(userEntity.getProfileStatus());

        return userInfo;


    }
}
