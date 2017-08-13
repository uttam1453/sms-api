/**
 * 
 */
package com.appster.sms.api.student.room;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appster.sms.api.auth.service.jwt.JwtUser;
import com.appster.sms.api.common.response.envelope.SuccessResponseEnvelope;
import com.appster.sms.api.common.response.envelope.SuccessResponseListEnvelope;
import com.appster.sms.api.student.room.form.AddEditRoomForm;
import com.appster.sms.api.student.room.form.ImageUploadForm;
import com.appster.sms.api.student.room.model.RoomInfoModel;
import com.appster.sms.api.student.room.model.RoomListModel;
import com.appster.sms.api.student.room.service.RoomService;

/**
 * @author lokesh created on 04-Aug-2017
 *
 */
@RestController
@RequestMapping("/api/v1/user/rooms")
public class RoomController {
	
	@Autowired
	private RoomService roomService;

	/**
	 * This method is used to add/Edit details of selected room
	 * @param AddEditRoomForm
	 * @return SuccessResponseEnvelope<RoomInfoModel>
	 */
	@PostMapping("/")
    public SuccessResponseEnvelope<RoomInfoModel> addEditRoom(@RequestBody @Valid AddEditRoomForm addEditForm, JwtUser userDetails) {
        return new SuccessResponseEnvelope<>(roomService.addEditRoom(addEditForm,userDetails));
    }
	
	/**
	 * This method is used to get details of selected room
	 * @param id
	 * @return SuccessResponseEnvelope<RoomListModel>
	 */
	@GetMapping("/{id}")
    public SuccessResponseEnvelope<RoomInfoModel> roomDetails(@PathVariable("id") int id, JwtUser userDetails) {
        return new SuccessResponseEnvelope<>(roomService.roomDetails(id,userDetails));
    }
	
	/**
	 * This method is used to get paginated room list
	 * @param page
	 * @param size
	 * @return SuccessResponseListEnvelope<RoomListModel>
	 */
	@GetMapping("")
	public SuccessResponseListEnvelope<RoomListModel> listRooms(
			@RequestParam(value="filter",required=true) int filter,
    		@RequestParam(value="page",required=true) int page,
    		@RequestParam(value="size",required=true) int size,
    		@RequestParam(value="q") String query,JwtUser userDetails) {
		return roomService.listRooms(page,size,filter,query);
	}
	
	/**
	 * This method is used to upload image for room
	 * @param 
	 * @return SuccessResponseListEnvelope<RoomListModel>
	 */
	@PostMapping("/uploadImage")
	public SuccessResponseEnvelope<RoomInfoModel> uploadImage(@ModelAttribute @Valid ImageUploadForm imageUploadForm,JwtUser userDetails) {
		return new SuccessResponseEnvelope<>(roomService.uploadImage(imageUploadForm));
	}
}
