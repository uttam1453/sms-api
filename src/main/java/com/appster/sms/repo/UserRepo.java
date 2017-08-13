package com.appster.sms.repo;

import com.appster.sms.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * created by atul on 05/01/17.
 */
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

    //put account status check
    @Query("select u from UserEntity u where u.email=?1")
    UserEntity checkEmailExistence(String email);

    @Query("select u from UserEntity u where u.id=?1")
    Optional<UserEntity> findById(int id);

}
