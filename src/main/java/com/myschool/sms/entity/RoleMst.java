package com.myschool.sms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.myschool.sms.entity.base.BaseEntity;


/**
 * The persistent class for the ROLE_MST database table.
 * 
 */
@Entity
@Table(name="ROLE_MST")
@NamedQuery(name="RoleMst.findAll", query="SELECT r FROM RoleMst r")
public class RoleMst extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="active_flag", nullable=false)
	private byte activeFlag;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to RoleMenuMapping
	@OneToMany(mappedBy="roleMst")
	private List<RoleMenuMapping> roleMenuMappings;

	//bi-directional many-to-one association to UserRoleMapping
	@OneToMany(mappedBy="roleMst")
	private List<UserRoleMapping> userRoleMappings;

	public RoleMst() {
	}

	public byte getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(byte activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoleMenuMapping> getRoleMenuMappings() {
		return this.roleMenuMappings;
	}

	public void setRoleMenuMappings(List<RoleMenuMapping> roleMenuMappings) {
		this.roleMenuMappings = roleMenuMappings;
	}

	public RoleMenuMapping addRoleMenuMapping(RoleMenuMapping roleMenuMapping) {
		getRoleMenuMappings().add(roleMenuMapping);
		roleMenuMapping.setRoleMst(this);

		return roleMenuMapping;
	}

	public RoleMenuMapping removeRoleMenuMapping(RoleMenuMapping roleMenuMapping) {
		getRoleMenuMappings().remove(roleMenuMapping);
		roleMenuMapping.setRoleMst(null);

		return roleMenuMapping;
	}

	public List<UserRoleMapping> getUserRoleMappings() {
		return this.userRoleMappings;
	}

	public void setUserRoleMappings(List<UserRoleMapping> userRoleMappings) {
		this.userRoleMappings = userRoleMappings;
	}

	public UserRoleMapping addUserRoleMapping(UserRoleMapping userRoleMapping) {
		getUserRoleMappings().add(userRoleMapping);
		userRoleMapping.setRoleMst(this);

		return userRoleMapping;
	}

	public UserRoleMapping removeUserRoleMapping(UserRoleMapping userRoleMapping) {
		getUserRoleMappings().remove(userRoleMapping);
		userRoleMapping.setRoleMst(null);

		return userRoleMapping;
	}

}