/**
 * 
 */
package com.appster.sms.api.student.room.service;

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

import com.appster.sms.api.auth.service.jwt.JwtUser;
import com.appster.sms.api.common.AppException;
import com.appster.sms.api.common.response.envelope.Pagination;
import com.appster.sms.api.common.response.envelope.SuccessResponseListEnvelope;
import com.appster.sms.api.common.service.ValidationService;
import com.appster.sms.api.common.util.S3StorageService;
import com.appster.sms.api.master.model.ListModel;
import com.appster.sms.api.master.service.MasterService;
import com.appster.sms.api.student.room.builder.RoomBuilder;
import com.appster.sms.api.student.room.form.AddEditRoomForm;
import com.appster.sms.api.student.room.form.ImageUploadForm;
import com.appster.sms.api.student.room.model.RoomInfoModel;
import com.appster.sms.api.student.room.model.RoomListModel;
import com.appster.sms.config.AppConst;
import com.appster.sms.entity.MasterInterest;
import com.appster.sms.entity.Room;
import com.appster.sms.entity.RoomInterest;
import com.appster.sms.entity.RoomTag;
import com.appster.sms.entity.RoomUser;
import com.appster.sms.entity.UserEntity;
import com.appster.sms.entity.Room.ROOM_STATUS;
import com.appster.sms.entity.embeddedId.RoomUserPK;
import com.appster.sms.repo.RoomInterestRepo;
import com.appster.sms.repo.RoomRepo;
import com.appster.sms.repo.RoomTagsRepo;
import com.appster.sms.repo.RoomUserRepo;
import com.appster.sms.repo.UserRepo;

/**
 * @author lokesh created on 04-Aug-2017
 *
 */
@Service
public class RoomService {
	public static final Logger LOG = LoggerFactory.getLogger(RoomService.class);
	
	@Autowired
	private RoomRepo roomRepo; 
	
	@Autowired
	private RoomUserRepo roomUserRepo;
	
	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private S3StorageService s3StorageService;
	
	@Autowired
	private RoomBuilder roomBuilder;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoomInterestRepo roomInterestRepo;
	
	@Autowired
	private RoomTagsRepo roomTagsRepo;
	
	@Autowired
	private MasterService masterService;
	/**
	 * @param
	 * @return RoomInfoModel
	 */
	@Transactional
	public RoomInfoModel addEditRoom(AddEditRoomForm addEditForm, JwtUser userDetails){
		LOG.info("In addEditRoom - addEditForm.getRoomId() : "+addEditForm.getRoomId());
		UserEntity createdBy = validationService.validateAndGetUser(userDetails.getId());
		Room room;
		boolean editFlag = false;
		List<RoomUser> allRoomUser;
		if(AppConst.ZERO==addEditForm.getRoomId()){
			room = new Room();
			room.setUser(createdBy);
			room.setMasterUniversity(createdBy.getMasterUniversity());
		}
		else{
			editFlag = true;
            room=getRoomById(addEditForm.getRoomId());
			
			//delete all already mapped users except admin
			allRoomUser = roomUserRepo.getAllRoomUsersExceptAdmin(addEditForm.getRoomId(),createdBy.getId());
			if(null!=allRoomUser){
				roomUserRepo.delete(allRoomUser);
				allRoomUser=null;
			}
			
			if(!room.getRoomInterests().isEmpty()){
				roomInterestRepo.delete(room.getRoomInterests());
			}
			
			if(!room.getRoomTags().isEmpty()){
				roomTagsRepo.delete(room.getRoomTags());
			}
		}
		room.setIsPublic(addEditForm.isPublicFlag());
		room.setMemberCanPost(addEditForm.isMemberCanPost());
		room.setName(addEditForm.getRoomName());
		room.setSearchable(addEditForm.isSearchable());
		room.setStatus(ROOM_STATUS.ACTIVE);
		room.setRoomUrl(AppConst.EMPTY_STRING);
		room = roomRepo.save(room);
		
		boolean updateEntity=false;
		List<RoomInterest> allRoomInterests = getRoomInterestList(addEditForm.getInterests(),room,createdBy);
		if(!allRoomInterests.isEmpty()){
			updateEntity=true;
			room.setRoomInterests(allRoomInterests);
			roomInterestRepo.save(allRoomInterests);
		}
		
		List<RoomTag> allRoomTags = getRoomTagsList(addEditForm.getTags(),room,createdBy);
		if(!allRoomTags.isEmpty()){
			updateEntity=true;
			room.setRoomTags(allRoomTags);
			roomTagsRepo.save(allRoomTags);
		}
		
		//Now prepare the users List
		allRoomUser = getRoomUsersList(addEditForm.getMemberIds(),room,createdBy,editFlag);
		if(!allRoomUser.isEmpty()){
			updateEntity=true;
			room.setRoomUsers(allRoomUser);
			roomUserRepo.save(allRoomUser);
		}
		
		if(updateEntity){
			room = roomRepo.saveAndFlush(room);
		}
		return roomBuilder.getRoomInfoModel(room,allRoomUser.size());
	}

	/**
	 * @param tags
	 * @param room
	 * @param createdBy
	 * @return
	 */
	private List<RoomTag> getRoomTagsList(List<ListModel> tags, Room room,UserEntity createdBy) {
		List<RoomTag> allRoomTags = new ArrayList<>();
		if(null!=tags){
			MasterInterest masterInterest=null;
			for(ListModel roomTag:tags){
				masterInterest = masterService.validateMasterInterest(roomTag.getId(), roomTag.getValue());
				if(null==masterInterest){
					masterInterest = masterService.addMasterInterst(roomTag.getValue(), createdBy);
				}
				allRoomTags.add(roomBuilder.getRoomTagEntity(room, masterInterest));
			};
		}
		return allRoomTags;
	}

	/**
	 * @param interests
	 * @param room
	 * @param createdBy
	 * @return
	 */
	private List<RoomInterest> getRoomInterestList(List<ListModel> interests,Room room, UserEntity createdBy) {
		List<RoomInterest> allRoomInterest = new ArrayList<>();
		if(null!=interests){
			MasterInterest masterInterest=null;
			for(ListModel roomInterest:interests){
				masterInterest = masterService.validateMasterInterest(roomInterest.getId(), roomInterest.getValue());
				if(null==masterInterest){
					masterInterest = masterService.addMasterInterst(roomInterest.getValue(), createdBy);
				}
				allRoomInterest.add(roomBuilder.getRoomInterestEntity(room, masterInterest));
			};
		}
		return allRoomInterest;
	}

	/**
	 * @param memberIds
	 * @return List<RoomUser>
	 */
	private List<RoomUser> getRoomUsersList(List<Integer> memberIds,Room room,UserEntity createdBy,boolean editFlag) {
		List<RoomUser> allRoomUsers = new ArrayList<>();
		
		if(!editFlag){
			//Making admin User when new room is created
			allRoomUsers.add(new RoomUser(new RoomUserPK(room.getId(),createdBy.getId()), Boolean.TRUE, room, createdBy));
		}
		
		if(null!=memberIds){
			List<UserEntity> allMembers = userRepo.findAll(memberIds);
			allMembers.forEach(memberEntity->{
				allRoomUsers.add(new RoomUser(new RoomUserPK(room.getId(),memberEntity.getId()), Boolean.FALSE, room, memberEntity));
			});
		}
		
		LOG.info("allRoomUsers : "+allRoomUsers.size());
		return allRoomUsers;
	}

	/**
	 * @param query 
	 * @param filter 
	 * @param pageable
	 * @return List<RoomListModel>
	 */
	@Transactional
	public SuccessResponseListEnvelope<RoomListModel> listRooms(int page, int size, int filter, String query) {
		Pageable pageable = new PageRequest(page - 1, size);
		LOG.info("In listRooms - pageable.getPageNumber() : "+pageable.getPageNumber()+"-  query : "+query);
		Page<Room> allRoomsList;
		if(null!=query && !"".equals(query)){
			allRoomsList = roomRepo.findAllActiveRoomsByName(query,pageable);
		}
		else{
			allRoomsList = roomRepo.findAllActiveRooms(pageable);	
		}
		
		List<RoomListModel> allRoomList = new ArrayList<>();
		if(!allRoomsList.getContent().isEmpty()){
			allRoomsList.getContent().forEach(rooms->{
				allRoomList.add(new RoomListModel(rooms.getId(), rooms.getName(), rooms.getRoomUrl(), rooms.getIsPublic(), rooms.getRoomUsers()==null?0:rooms.getRoomUsers().size()));
			});
		}
		return new SuccessResponseListEnvelope<>(allRoomList,new Pagination(allRoomsList.getTotalPages(), allRoomsList.getNumberOfElements(), pageable.getPageNumber(), pageable.getPageSize()));
	}

	/**
	 * @param id
	 * @param userDetails
	 * @return
	 */
	@Transactional
	public RoomInfoModel roomDetails(int roomId, JwtUser userDetails) {
		Room room = getRoomById(roomId);
		return roomBuilder.getRoomInfoModel(room,null==room.getRoomUsers()?0:room.getRoomUsers().size());
	}
	
	/**
	 * @param roomId
	 * @return
	 */
	public Room getRoomById(int roomId){
		Room room = roomRepo.findOne(roomId);
		if(null==room){
			throw new AppException("ROOM_NOT_FOUND");
		}
		return room;
	}

	/**
	 * @param imageUploadForm
	 * @return
	 */
	public RoomInfoModel uploadImage(ImageUploadForm imageUploadForm) {
		Room room = getRoomById(imageUploadForm.getId());
		room.setRoomUrl(s3StorageService.saveImages(imageUploadForm.getImage(), room.getId(), AppConst.UPLOAD_TYPE.ROOMS_IMAGE));
		room = roomRepo.saveAndFlush(room);
		return roomBuilder.getRoomInfoModel(room,null==room.getRoomUsers()?0:room.getRoomUsers().size());
	}
}
