package com.myschool.sms.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.myschool.sms.entity.base.AuditEntity;
import com.myschool.sms.entity.embeddedId.UserRoleMappingPK;


/**
 * The persistent class for the USER_ROLE_MAPPING database table.
 * 
 */
@Entity
@Table(name="USER_ROLE_MAPPING")
@NamedQuery(name="UserRoleMapping.findAll", query="SELECT u FROM UserRoleMapping u")
public class UserRoleMapping extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRoleMappingPK userRolePK;


	//bi-directional many-to-one association to RoleMst
	@ManyToOne
	@JoinColumn(name="role_id", nullable=false, insertable=false, updatable=false)
	private RoleMst roleMst;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false, insertable=false, updatable=false)
	private UserEntity userMst;

	public UserRoleMapping() {
	}

	public UserRoleMappingPK getUserRolePK() {
		return userRolePK;
	}

	public void setUserRolePK(UserRoleMappingPK userRolePK) {
		this.userRolePK = userRolePK;
	}

	public RoleMst getRoleMst() {
		return this.roleMst;
	}

	public void setRoleMst(RoleMst roleMst) {
		this.roleMst = roleMst;
	}

	public UserEntity getUserMst() {
		return this.userMst;
	}

	public void setUserMst(UserEntity userMst) {
		this.userMst = userMst;
	}

}