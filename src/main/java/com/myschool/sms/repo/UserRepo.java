/**
 * 
 */
package com.myschool.sms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myschool.sms.entity.UserEntity;

/**
 * @author lokesh created on 13-Aug-2017
 *
 */
public interface UserRepo extends JpaRepository<UserEntity, Integer>{

	/**
	 * @param id
	 * @return
	 */
	@Query("select u from UserEntity u where u.id=:userId")
	Optional<UserEntity> findById(@Param("userId")int userId);

	/**
	 * @param email
	 * @return
	 */
	@Query("select u from UserEntity u where lower(u.emailId)=lower(:emailId)")
	UserEntity checkEmailExistence(@Param("emailId")String email);

}
