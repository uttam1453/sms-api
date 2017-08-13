package com.appster.sms.api.student.room.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appster.sms.api.common.model.Builder.MasterModelsBuilder;
import com.appster.sms.api.master.model.ListModel;
import com.appster.sms.api.student.room.model.RoomInfoModel;
import com.appster.sms.entity.MasterInterest;
import com.appster.sms.entity.Room;
import com.appster.sms.entity.RoomInterest;
import com.appster.sms.entity.RoomInterestPK;
import com.appster.sms.entity.RoomTag;
import com.appster.sms.entity.RoomTagPK;

/**
 * Created by atul on 08/08/17.
 */
@Component
public class RoomBuilder {
	
	@Autowired
	private MasterModelsBuilder masterModelsBuilder;
	
    public RoomInfoModel getRoomInfoModel(Room room, int membersCnt){
        RoomInfoModel roleInfoModel = new RoomInfoModel();
        roleInfoModel.setMemberCanPost(room.getMemberCanPost());
        roleInfoModel.setMembersCount(membersCnt);
        roleInfoModel.setPublic(room.getIsPublic());
        roleInfoModel.setRoomId(room.getId());
        roleInfoModel.setRoomName(room.getName());
        roleInfoModel.setRoomUrl(room.getRoomUrl());
        roleInfoModel.setSearchable(room.getSearchable());
        roleInfoModel.setStatus(room.getStatus());
        List<ListModel> roomInterests = new ArrayList<>();
        if(!room.getRoomInterests().isEmpty()){
        	room.getRoomInterests().forEach(roomInterest->{
        		roomInterests.add(masterModelsBuilder.getListModel(roomInterest.getMasterInterest().getId(), roomInterest.getMasterInterest().getName()));
        	});
        }
        roleInfoModel.setTags(roomInterests);
        List<ListModel> roomTags = new ArrayList<>();
        if(!room.getRoomTags().isEmpty()){
        	room.getRoomTags().forEach(roomTag->{
        		roomTags.add(masterModelsBuilder.getListModel(roomTag.getMasterInterest().getId(), roomTag.getMasterInterest().getName()));
        	});
        }
        roleInfoModel.setInterests(roomTags);
        return roleInfoModel;
    }
    
    public RoomTag getRoomTagEntity(Room room,MasterInterest masterInterest){
    	return new RoomTag(new RoomTagPK(room.getId(), masterInterest.getId()),masterInterest,room);
    }
    
    public RoomInterest getRoomInterestEntity(Room room,MasterInterest masterInterest){
    	return new RoomInterest(new RoomInterestPK(room.getId(), masterInterest.getId()), masterInterest, room);
    }
}
