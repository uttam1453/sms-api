package com.appster.sms.api.master.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appster.sms.api.common.AppException;
import com.appster.sms.api.common.model.Builder.MasterModelsBuilder;
import com.appster.sms.api.common.response.envelope.Pagination;
import com.appster.sms.api.common.response.envelope.SuccessResponseListEnvelope;
import com.appster.sms.api.master.model.ListModel;
import com.appster.sms.config.AppConst;
import com.appster.sms.entity.MasterInterest;
import com.appster.sms.entity.MasterSignupTypeEntity;
import com.appster.sms.entity.UserEntity;
import com.appster.sms.repo.MasterInterestRepo;
import com.appster.sms.repo.MasterSignUpTypeRepo;

/**
 * Created by atul on 02/06/17.
 */
@Service
public class MasterService {
	public static final Logger log = LoggerFactory.getLogger(MasterService.class);
	
    @Autowired
    private MasterSignUpTypeRepo masterSignUpTypeRepo;

    @Autowired
    private MasterInterestRepo masterInterestRepo;

    @Autowired
    private MasterModelsBuilder masterModelsBuilder;

    public MasterSignupTypeEntity getMasterSignupTypeByName(String sourceName) {
        MasterSignupTypeEntity masterSignupTypeEntity = masterSignUpTypeRepo.getActiveSignUpType(sourceName);
        if (masterSignupTypeEntity == null)
            throw new AppException("INVALID_SIGNUP_TYPE");
        return masterSignupTypeEntity;
    }

	/**
	 * @param query
	 * @param size 
	 * @param page 
	 * @return ListModel
	 */
	public SuccessResponseListEnvelope<ListModel> getInterest(String query, int page, int size) {
		log.info("In getInterest : query - "+query);
		Pageable pageable = new PageRequest(page - 1, size);
		Page<Object[]> pagedInterestList = masterInterestRepo.getPagedInterestByCriteria(query, pageable);
		List<ListModel> allMatchedInterest = new ArrayList<>();
		if(!pagedInterestList.getContent().isEmpty()){
			pagedInterestList.getContent().forEach(interestEntity->{
				allMatchedInterest.add(masterModelsBuilder.getListModel(Integer.parseInt(String.valueOf(interestEntity[0])), String.valueOf(interestEntity[1])));
			});
		}
		return new SuccessResponseListEnvelope<>(allMatchedInterest,new Pagination(pagedInterestList.getTotalPages(), pagedInterestList.getNumberOfElements(), pageable.getPageNumber(), pageable.getPageSize()));
	}

	public MasterInterest validateMasterInterest(int interestId,String interest){
		if(AppConst.ZERO==interestId){
			return masterInterestRepo.findByInterest(interest);
		}
		return masterInterestRepo.findOne(interestId);
	}
	
	@Transactional
	public MasterInterest addMasterInterst(String interest,UserEntity createdBy){
		MasterInterest masterInterest = new MasterInterest();
		masterInterest.setIsDeleted(Boolean.FALSE);
		masterInterest.setName(interest);
		masterInterest.setUser(createdBy);
		return masterInterestRepo.save(masterInterest);
	}
}
