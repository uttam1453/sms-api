package com.myschool.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myschool.sms.entity.UserSessionDetail;

/**
 * created by atul on 28/02/17.
 */
@Repository
public interface SessionRepo extends JpaRepository<UserSessionDetail, Integer> {

    @Query("SELECT S FROM UserSessionDetail  S where S.userEntity.id=:userId")
    UserSessionDetail findByUserId(@Param("userId") int userId);

    @Modifying
    @Query("delete from UserSessionDetail u  where  u.deviceToken=:deviceToken")
    void deleteSessionDetailsByToken(@Param("deviceToken") String deviceToken);


    @Modifying
    @Query("delete from UserSessionDetail u  where  u.userEntity.id=:userId")
    void deleteSessionDetailsByUserId(@Param("userId") int userId);
}
