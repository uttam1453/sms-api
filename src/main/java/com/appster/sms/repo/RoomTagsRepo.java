/**
 * 
 */
package com.appster.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appster.sms.entity.RoomTag;

/**
 * @author lokesh created on 10-Aug-2017
 *
 */
public interface RoomTagsRepo extends JpaRepository<RoomTag, Integer> {

}
