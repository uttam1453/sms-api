/**
 * 
 */
package com.appster.sms.api.student.room.model;

import java.util.List;

import com.appster.sms.api.master.model.ListModel;
import com.appster.sms.entity.Room.ROOM_STATUS;

/**
 * @author lokesh created on 04-Aug-2017
 *
 */
public class RoomInfoModel extends RoomListModel{
	private boolean memberCanPost;
	private boolean searchable;
	private List<ListModel> tags;
	private List<ListModel> interests;
	private ROOM_STATUS status;
	
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
	public List<ListModel> getTags() {
		return tags;
	}
	public void setTags(List<ListModel> tags) {
		this.tags = tags;
	}
	public List<ListModel> getInterests() {
		return interests;
	}
	public void setInterests(List<ListModel> interests) {
		this.interests = interests;
	}
	public ROOM_STATUS getStatus() {
		return status;
	}
	public void setStatus(ROOM_STATUS status) {
		this.status = status;
	}
}
