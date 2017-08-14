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
 * The persistent class for the MENU_MST database table.
 * 
 */
@Entity
@Table(name="MENU_MST")
@NamedQuery(name="MenuMst.findAll", query="SELECT m FROM MenuMst m")
public class MenuMst extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="active_flag", nullable=false)
	private byte activeFlag;

	@Column(name="menu_name", nullable=false, length=45)
	private String menuName;

	@Column(name="menu_url", nullable=false, length=45)
	private String menuUrl;

	//bi-directional many-to-one association to RoleMenuMapping
	@OneToMany(mappedBy="menuMst")
	private List<RoleMenuMapping> roleMenuMappings;

	public MenuMst() {
	}

	public byte getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(byte activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public List<RoleMenuMapping> getRoleMenuMappings() {
		return this.roleMenuMappings;
	}

	public void setRoleMenuMappings(List<RoleMenuMapping> roleMenuMappings) {
		this.roleMenuMappings = roleMenuMappings;
	}

	public RoleMenuMapping addRoleMenuMapping(RoleMenuMapping roleMenuMapping) {
		getRoleMenuMappings().add(roleMenuMapping);
		roleMenuMapping.setMenuMst(this);

		return roleMenuMapping;
	}

	public RoleMenuMapping removeRoleMenuMapping(RoleMenuMapping roleMenuMapping) {
		getRoleMenuMappings().remove(roleMenuMapping);
		roleMenuMapping.setMenuMst(null);

		return roleMenuMapping;
	}

}