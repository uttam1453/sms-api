package com.appster.sms.api.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appster.sms.api.auth.service.jwt.JwtUser;
import com.appster.sms.api.common.response.envelope.SuccessResponseListEnvelope;
import com.appster.sms.api.master.model.ListModel;
import com.appster.sms.api.master.service.MasterService;


/**
 * Created by atul on 02/06/17.
 */
@RestController
@RequestMapping("/api/v1/master/")
public class MasterController {
	@Autowired
	private MasterService masterService;
	
	
	/**
	 * This method is used to get details of selected room
	 * @param id
	 * @return SuccessResponseEnvelope<RoomListModel>
	 */
	@GetMapping("/getInterest")
    public SuccessResponseListEnvelope<ListModel> getInterest(@RequestParam(value="q",required=true) String query,
    		@RequestParam(value="page",required=true) int page,
    		@RequestParam(value="size",required=true) int size, JwtUser userDetails) {
        return masterService.getInterest(query,page,size);
    }
}
