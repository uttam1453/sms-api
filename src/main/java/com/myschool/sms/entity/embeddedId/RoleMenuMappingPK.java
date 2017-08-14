package com.myschool.sms.entity.embeddedId;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ROLE_MENU_MAPPING database table.
 * 
 */
@Embeddable
public class RoleMenuMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="role_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int roleId;

	@Column(name="menu_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int menuId;

	public RoleMenuMappingPK() {
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getMenuId() {
		return this.menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RoleMenuMappingPK)) {
			return false;
		}
		RoleMenuMappingPK castOther = (RoleMenuMappingPK)other;
		return 
			(this.roleId == castOther.roleId)
			&& (this.menuId == castOther.menuId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId;
		hash = hash * prime + this.menuId;
		
		return hash;
	}
}