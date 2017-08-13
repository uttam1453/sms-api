/**
 * 
 */
package com.appster.sms.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appster.sms.entity.Room;

/**
 * @author lokesh created on 04-Aug-2017
 *
 */
public interface RoomRepo extends JpaRepository<Room, Integer>{

	/**
	 * @param pageable
	 * @return Page
	 */
	@Query("select u from Room u where u.status='ACTIVE'")
	Page<Room> findAllActiveRooms(Pageable pageable);

	String ROOM_SEARCH_BY_NAME ="select u.*"
			+ " from room u"
			+ " where u.status = 'ACTIVE' and"
			+ " (MATCH (u.name) AGAINST (:query IN BOOLEAN MODE)) \n#pageable\n";
	/*
	* Native query for counting total matched result for returning interest
	*/
	String ROOM_SEARCH_BY_NAME_COUNT ="select count(*)"
			+ " from room u"
			+ " where u.status = 'ACTIVE' and"
			+ " (MATCH (u.name) AGAINST (:query IN BOOLEAN MODE)) \n#pageable\n";
	
	/**
	 * @param query
	 * @param pageable
	 * @return Page<Room>
	 */
	@Query(value = ROOM_SEARCH_BY_NAME, countQuery=ROOM_SEARCH_BY_NAME_COUNT, nativeQuery=true)
	public Page<Room> findAllActiveRoomsByName(@Param("query") String query,Pageable pageable);
}
