/**
 * 
 */
package com.appster.sms.api.student.room.form;

import java.util.List;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.appster.sms.api.master.model.ListModel;
import com.appster.sms.validation.FirstLevelValidation;
import com.appster.sms.validation.SecondLevelValidation;

/**
 * @author lokesh created on 04-Aug-2017
 *
 */
@GroupSequence({FirstLevelValidation.class, SecondLevelValidation.class, AddEditRoomForm.class})
public class AddEditRoomForm {
	
	private int roomId;
	
	private boolean publicFlag=Boolean.FALSE;

	@NotNull(message="{V.ROOM.NAME.CANNOT_NULL}",groups=FirstLevelValidation.class)
    @Size(max = 100, message = "{V.ROOM.NAME.MAX_SIZE}", groups = SecondLevelValidation.class)
	private String roomName;
	
	private List<ListModel> tags;
	
	private List<ListModel> interests;
	
	private boolean memberCanPost=Boolean.FALSE;
	
	private boolean searchable=Boolean.FALSE;
	
	private List<Integer> memberIds;
	
////	@NotNull(message="{V.ROOM.IMAGE.CANNOT_NULL}",groups=FirstLevelValidation.class)
//	private MultipartFile roomImage;

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public boolean isPublicFlag() {
		return publicFlag;
	}

	public void setPublicFlag(boolean publicFlag) {
		this.publicFlag = publicFlag;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<ListModel> getTags() {
		return tags;
	}

	public void setTags(List<ListModel> tags) {
		this.tags = tags;
	}

	public boolean isMemberCanPost() {
		return memberCanPost;
	}

	public void setMemberCanPost(boolean memberCanPost) {
		this.memberCanPost = memberCanPost;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public List<Integer> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(List<Integer> memberIds) {
		this.memberIds = memberIds;
	}

//	public MultipartFile getRoomImage() {
//		return roomImage;
//	}
//
//	public void setRoomImage(MultipartFile roomImage) {
//		this.roomImage = roomImage;
//	}

	public List<ListModel> getInterests() {
		return interests;
	}

	public void setInterests(List<ListModel> interests) {
		this.interests = interests;
	}
	
	
}
