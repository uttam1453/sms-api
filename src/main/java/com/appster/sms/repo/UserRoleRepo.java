/**
 * 
 */
package com.appster.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appster.sms.entity.UserRoleEntity;

/**
 * @author lokesh created on 04-Aug-2017
 *
 */
public interface UserRoleRepo extends JpaRepository<UserRoleEntity, Integer> {

}
