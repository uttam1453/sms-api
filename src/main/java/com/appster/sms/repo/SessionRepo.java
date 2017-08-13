package com.appster.sms.repo;

import com.appster.sms.entity.SessionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * created by atul on 28/02/17.
 */
@Repository
public interface SessionRepo extends JpaRepository<SessionEntity, Integer> {

    @Query("SELECT S FROM SessionEntity  S where S.userEntity.id=:userId")
    SessionEntity findByUserId(@Param("userId") int userId);

    @Modifying
    @Query("delete from SessionEntity u  where  u.deviceToken=:deviceToken")
    void deleteSessionDetailsByToken(@Param("deviceToken") String deviceToken);


    @Modifying
    @Query("delete from SessionEntity u  where  u.userEntity.id=:userId")
    void deleteSessionDetailsByUserId(@Param("userId") int userId);
}
