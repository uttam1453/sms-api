package com.myschool.sms.entity.embeddedId;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the USER_ROLE_MAPPING database table.
 * 
 */
@Embeddable
public class UserRoleMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="role_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int roleId;

	@Column(name="user_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int userId;

	public UserRoleMappingPK() {
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserRoleMappingPK)) {
			return false;
		}
		UserRoleMappingPK castOther = (UserRoleMappingPK)other;
		return 
			(this.roleId == castOther.roleId)
			&& (this.userId == castOther.userId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.userId;
		
		return hash;
	}
}