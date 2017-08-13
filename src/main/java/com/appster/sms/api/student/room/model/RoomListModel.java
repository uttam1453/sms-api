/**
 * 
 */
package com.appster.sms.api.student.room.model;

import com.appster.sms.api.common.response.Payload;

/**
 * @author lokesh created on 04-Aug-2017
 *
 */
public class RoomListModel implements Payload{
	private int roomId;
	private String roomName;
	private String roomUrl;
	private boolean isPublic;
	private int membersCount;
	
	public RoomListModel(){}
	
	public RoomListModel(int roomId, String roomName, String roomUrl,
			boolean isPublic, int membersCount) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomUrl = roomUrl;
		this.isPublic = isPublic;
		this.membersCount = membersCount;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomUrl() {
		return roomUrl;
	}
	public void setRoomUrl(String roomUrl) {
		this.roomUrl = roomUrl;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public int getMembersCount() {
		return membersCount;
	}
	public void setMembersCount(int membersCount) {
		this.membersCount = membersCount;
	}
	
	
}
