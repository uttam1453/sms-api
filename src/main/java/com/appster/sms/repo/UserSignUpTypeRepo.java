package com.appster.sms.repo;

import com.appster.sms.entity.UserSignupTypeEntity;
import com.appster.sms.entity.embeddedId.UserSignupTypePK;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by atul on 27/01/17.
 */
public interface UserSignUpTypeRepo extends JpaRepository<UserSignupTypeEntity, UserSignupTypePK> {

}
