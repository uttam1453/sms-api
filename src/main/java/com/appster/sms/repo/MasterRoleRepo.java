package com.appster.sms.repo;

import com.appster.sms.entity.MasterRoleEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by ishu on 06/01/17.
 */
public interface MasterRoleRepo extends JpaRepository<MasterRoleEntity, Integer> {

    @Query("select mr from MasterRoleEntity mr where mr.name=?1 and mr.isDeleted=false")
    public MasterRoleEntity getActiveRole(String roleName);

}
