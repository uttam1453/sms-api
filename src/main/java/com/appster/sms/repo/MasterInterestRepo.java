/**
 * 
 */
package com.appster.sms.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appster.sms.entity.MasterInterest;

/**
 * @author lokesh created on 09-Aug-2017
 *
 */
public interface MasterInterestRepo extends JpaRepository<MasterInterest, Integer> {

	String INTEREST_SEARCH ="SELECT	I.id,I.name"
			+ " FROM master_interest I"
			+ " WHERE I.is_deleted = '0' and"
			+ " (MATCH (I.name) AGAINST (:keyword IN BOOLEAN MODE)) \n#pageable\n";
		/*
		* Native query for counting total matched result for returning interest
		*/
		String INTEREST_SEARCH_COUNT ="SELECT count(*)"
			+ " FROM master_interest I"
			+ " WHERE I.is_deleted = '0' and"
			+ " (MATCH (I.name) AGAINST (:keyword IN BOOLEAN MODE)) \n#pageable\n";
		@Query(value = INTEREST_SEARCH, countQuery=INTEREST_SEARCH_COUNT, nativeQuery=true)
		public Page<Object[]> getPagedInterestByCriteria(@Param("keyword") String keyword,Pageable pageable);
		
		/**
		 * @param interest
		 * @return
		 */
		@Query("SELECT I from MasterInterest I WHERE I.name=(:interest) AND I.isDeleted='0'")
		public MasterInterest findByInterest(@Param("interest")String interest);
}
