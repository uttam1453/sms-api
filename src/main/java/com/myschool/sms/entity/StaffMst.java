package com.myschool.sms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.myschool.sms.config.AppConst;
import com.myschool.sms.entity.base.BaseEntity;


/**
 * The persistent class for the STAFF_MST database table.
 * 
 */
@Entity
@Table(name="STAFF_MST")
@NamedQuery(name="StaffMst.findAll", query="SELECT s FROM StaffMst s")
public class StaffMst extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="active_flag", nullable=false)
	private byte activeFlag;

	@Column(nullable=false, length=150)
	private String address;

	@Column(name="blood_group", nullable=false, length=10)
	private String bloodGroup;

	private Timestamp dob;

	@Column(name="fathers_name", nullable=false, length=45)
	private String fathersName;

	@Column(name="job_desc", length=45)
	private String jobDesc;

	@Column(name="last_date_servcie")
	private Timestamp lastDateServcie;

	@Column(name="mothers_name", nullable=false, length=45)
	private String mothersName;

	@Column(nullable=false, length=45)
	private String name;

	@Column(nullable=false, length=45)
	private String pob;

	@Column(name="service_start_date")
	private Timestamp serviceStartDate;

	@Column(nullable=false, length=1)
	@Enumerated(EnumType.STRING)
	private AppConst.GENDER sex = AppConst.GENDER.MALE;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private UserEntity userMst1;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private UserEntity userMst2;

	public StaffMst() {
	}
	
	public byte getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(byte activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Timestamp getDob() {
		return this.dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public String getFathersName() {
		return this.fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getJobDesc() {
		return this.jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public Timestamp getLastDateServcie() {
		return this.lastDateServcie;
	}

	public void setLastDateServcie(Timestamp lastDateServcie) {
		this.lastDateServcie = lastDateServcie;
	}

	public String getMothersName() {
		return this.mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPob() {
		return this.pob;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public Timestamp getServiceStartDate() {
		return this.serviceStartDate;
	}

	public void setServiceStartDate(Timestamp serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public AppConst.GENDER getSex() {
		return sex;
	}

	public void setSex(AppConst.GENDER sex) {
		this.sex = sex;
	}
	
	
	public UserEntity getUserMst1() {
		return this.userMst1;
	}

	public void setUserMst1(UserEntity userMst1) {
		this.userMst1 = userMst1;
	}

	public UserEntity getUserMst2() {
		return this.userMst2;
	}

	public void setUserMst2(UserEntity userMst2) {
		this.userMst2 = userMst2;
	}

}