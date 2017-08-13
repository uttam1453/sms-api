package com.appster.sms.repo;

import com.appster.sms.entity.MasterSignupTypeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by atul on 26/01/17.
 */
public interface MasterSignUpTypeRepo extends JpaRepository<MasterSignupTypeEntity, Integer> {
    @Query("select ST from MasterSignupTypeEntity ST where ST.name=?1 and ST.status='ACTIVE'")
    public MasterSignupTypeEntity getActiveSignUpType(String signUpType);
}
