/**
 * 
 */
package com.appster.sms.api.student.room.form;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author lokesh created on 08-Aug-2017
 *
 */
public class ImageUploadForm {
	
	@NotNull
	private int id;
	
	@NotNull(message="{V.ROOM.IMAGE.CANNOT_NULL}")
	private MultipartFile image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
}
