package com.myschool.sms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.myschool.sms.entity.base.AuditEntity;
import com.myschool.sms.entity.embeddedId.RoleMenuMappingPK;


/**
 * The persistent class for the ROLE_MENU_MAPPING database table.
 * 
 */
@Entity
@Table(name="ROLE_MENU_MAPPING")
@NamedQuery(name="RoleMenuMapping.findAll", query="SELECT r FROM RoleMenuMapping r")
public class RoleMenuMapping extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleMenuMappingPK id;

	@Column(name="active_flag", nullable=false)
	private byte activeFlag;

	@Column(name="exempt_user", length=200)
	private String exemptUser;

	//bi-directional many-to-one association to MenuMst
	@ManyToOne
	@JoinColumn(name="menu_id", nullable=false, insertable=false, updatable=false)
	private MenuMst menuMst;

	//bi-directional many-to-one association to RoleMst
	@ManyToOne
	@JoinColumn(name="role_id", nullable=false, insertable=false, updatable=false)
	private RoleMst roleMst;

	public RoleMenuMapping() {
	}

	public RoleMenuMappingPK getId() {
		return this.id;
	}

	public void setId(RoleMenuMappingPK id) {
		this.id = id;
	}

	public byte getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(byte activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getExemptUser() {
		return this.exemptUser;
	}

	public void setExemptUser(String exemptUser) {
		this.exemptUser = exemptUser;
	}

	public MenuMst getMenuMst() {
		return this.menuMst;
	}

	public void setMenuMst(MenuMst menuMst) {
		this.menuMst = menuMst;
	}

	public RoleMst getRoleMst() {
		return this.roleMst;
	}

	public void setRoleMst(RoleMst roleMst) {
		this.roleMst = roleMst;
	}

}