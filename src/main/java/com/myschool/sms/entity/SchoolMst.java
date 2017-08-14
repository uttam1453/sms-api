package com.myschool.sms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.myschool.sms.entity.base.BaseEntity;


/**
 * The persistent class for the SCHOOL_MST database table.
 * 
 */
@Entity
@Table(name="SCHOOL_MST")
@NamedQuery(name="SchoolMst.findAll", query="SELECT s FROM SchoolMst s")
public class SchoolMst extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="active_flag", nullable=false)
	private byte activeFlag;

	@Column(name="grade_system", nullable=false)
	private byte gradeSystem;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to InstitutionType
	@ManyToOne
	@JoinColumn(name="type", nullable=false)
	private InstitutionType institutionType;

	//bi-directional many-to-one association to UserMst
	@OneToMany(mappedBy="schoolMst")
	private List<UserEntity> userMsts;

	public SchoolMst() {}

	public byte getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(byte activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	public byte getGradeSystem() {
		return this.gradeSystem;
	}

	public void setGradeSystem(byte gradeSystem) {
		this.gradeSystem = gradeSystem;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InstitutionType getInstitutionType() {
		return this.institutionType;
	}

	public void setInstitutionType(InstitutionType institutionType) {
		this.institutionType = institutionType;
	}

	public List<UserEntity> getUserMsts() {
		return this.userMsts;
	}

	public void setUserMsts(List<UserEntity> userMsts) {
		this.userMsts = userMsts;
	}

	public UserEntity addUserMst(UserEntity userMst) {
		getUserMsts().add(userMst);
		userMst.setSchoolMst(this);

		return userMst;
	}

	public UserEntity removeUserMst(UserEntity userMst) {
		getUserMsts().remove(userMst);
		userMst.setSchoolMst(null);

		return userMst;
	}

}