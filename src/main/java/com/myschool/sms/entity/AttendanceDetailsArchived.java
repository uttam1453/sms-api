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
import com.myschool.sms.entity.embeddedId.AttendanceDetailsArchivedPK;


/**
 * The persistent class for the ATTENDANCE_DETAILS_ARCHIVED database table.
 * 
 */
@Entity
@Table(name="ATTENDANCE_DETAILS_ARCHIVED")
@NamedQuery(name="AttendanceDetailsArchived.findAll", query="SELECT a FROM AttendanceDetailsArchived a")
public class AttendanceDetailsArchived  extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AttendanceDetailsArchivedPK id;

	@Column(name="present_flag", nullable=false)
	private byte presentFlag;

	//bi-directional many-to-one association to AttendanceDatewise
	@ManyToOne
	@JoinColumn(name="attendance_dt_id", nullable=false, insertable=false, updatable=false)
	private AttendanceDatewise attendanceDatewise;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false, insertable=false, updatable=false)
	private UserEntity userMst;

	public AttendanceDetailsArchived() {
	}

	public AttendanceDetailsArchivedPK getId() {
		return this.id;
	}

	public void setId(AttendanceDetailsArchivedPK id) {
		this.id = id;
	}

	public byte getPresentFlag() {
		return this.presentFlag;
	}

	public void setPresentFlag(byte presentFlag) {
		this.presentFlag = presentFlag;
	}

	public AttendanceDatewise getAttendanceDatewise() {
		return this.attendanceDatewise;
	}

	public void setAttendanceDatewise(AttendanceDatewise attendanceDatewise) {
		this.attendanceDatewise = attendanceDatewise;
	}

	public UserEntity getUserMst() {
		return this.userMst;
	}

	public void setUserMst(UserEntity userMst) {
		this.userMst = userMst;
	}

}