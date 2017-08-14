package com.myschool.sms.api.common.model.Builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myschool.sms.api.common.model.UserBaseModel;
import com.myschool.sms.api.common.model.UserCompleteInfoModel;
import com.myschool.sms.entity.UserEntity;

/**
 * Created by ishu on 09/01/17.
 */
@Component
public class UserInfoBuilder {
    @Autowired
    private MasterModelsBuilder masterModelsBuilder;

    public UserBaseModel userInfo(UserEntity userEntity) {
        UserBaseModel userInfo = new UserBaseModel();
        userInfo.setName(userEntity.getUserName());
        userInfo.setUserId(userEntity.getId());
//        userInfo.setAge(userEntity.getAge());
        userInfo.setEmail(userEntity.getEmailId());
        userInfo.setActiveFlag(userEntity.getActiveFlag());
//        userInfo.setGender(userEntity.get);
        userEntity.getUserRoleMappings().forEach(userRoleEntity -> userInfo.getRoles().add(userRoleEntity.getRoleMst().getId()));
        return userInfo;
    }

    public UserCompleteInfoModel userCompleteInfoModel(UserEntity userEntity) {
        UserCompleteInfoModel userInfo = new UserCompleteInfoModel();

        userInfo.setName(userEntity.getUserName());
        userInfo.setUserId(userEntity.getId());
//        userInfo.setAge(userEntity.getAge());
        userInfo.setEmail(userEntity.getEmailId());
        userInfo.setActiveFlag(userEntity.getActiveFlag());
        userEntity.getUserRoleMappings().forEach(userRoleEntity -> userInfo.getRoles().add(userRoleEntity.getRoleMst().getId()));
        return userInfo;
    }
}
