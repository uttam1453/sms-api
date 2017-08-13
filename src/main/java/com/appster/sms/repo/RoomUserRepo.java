/**
 * 
 */
package com.appster.sms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appster.sms.entity.RoomUser;

/**
 * @author lokesh created on 04-Aug-2017
 *
 */
public interface RoomUserRepo extends JpaRepository<RoomUser, Integer>{
	
	@Query("select u from RoomUser u where u.roomUserPK.roomId=(:roomId) and u.roomUserPK.userId!=(:userId)")
	List<RoomUser> getAllRoomUsersExceptAdmin(@Param("roomId")int roomId,@Param("userId")int userId);

}
