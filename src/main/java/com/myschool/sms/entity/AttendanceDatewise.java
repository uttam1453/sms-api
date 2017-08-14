package com.myschool.sms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * The persistent class for the ATTENDANCE_DATEWISE database table.
 * 
 */
@Entity
@Table(name="ATTENDANCE_DATEWISE")
@NamedQuery(name="AttendanceDatewise.findAll", query="SELECT a FROM AttendanceDatewise a")
public class AttendanceDatewise extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Timestamp date;
	private byte isArchived;
	private int totalAbsent;
	private int totalPresent;
	private ClassMst classMst;
	private UserEntity userMst1;
	private UserEntity userMst2;
	private List<AttendanceDetail> attendanceDetails;
	private List<AttendanceDetailsArchived> attendanceDetailsArchiveds;

	public AttendanceDatewise() {
	}


	@Column(nullable=false)
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}


	@Column(name="is_archived", nullable=false)
	public byte getIsArchived() {
		return this.isArchived;
	}

	public void setIsArchived(byte isArchived) {
		this.isArchived = isArchived;
	}

	@Column(name="total_absent", nullable=false)
	public int getTotalAbsent() {
		return this.totalAbsent;
	}

	public void setTotalAbsent(int totalAbsent) {
		this.totalAbsent = totalAbsent;
	}


	@Column(name="total_present", nullable=false)
	public int getTotalPresent() {
		return this.totalPresent;
	}

	public void setTotalPresent(int totalPresent) {
		this.totalPresent = totalPresent;
	}


	//bi-directional many-to-one association to ClassMst
	@ManyToOne
	@JoinColumn(name="class_id", nullable=false)
	public ClassMst getClassMst() {
		return this.classMst;
	}

	public void setClassMst(ClassMst classMst) {
		this.classMst = classMst;
	}


	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	public UserEntity getUserMst1() {
		return this.userMst1;
	}

	public void setUserMst1(UserEntity userMst1) {
		this.userMst1 = userMst1;
	}


	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="modified_by", nullable=false)
	public UserEntity getUserMst2() {
		return this.userMst2;
	}

	public void setUserMst2(UserEntity userMst2) {
		this.userMst2 = userMst2;
	}


	//bi-directional many-to-one association to AttendanceDetail
	@OneToMany(mappedBy="attendanceDatewise")
	public List<AttendanceDetail> getAttendanceDetails() {
		return this.attendanceDetails;
	}

	public void setAttendanceDetails(List<AttendanceDetail> attendanceDetails) {
		this.attendanceDetails = attendanceDetails;
	}

	public AttendanceDetail addAttendanceDetail(AttendanceDetail attendanceDetail) {
		getAttendanceDetails().add(attendanceDetail);
		attendanceDetail.setAttendanceDatewise(this);

		return attendanceDetail;
	}

	public AttendanceDetail removeAttendanceDetail(AttendanceDetail attendanceDetail) {
		getAttendanceDetails().remove(attendanceDetail);
		attendanceDetail.setAttendanceDatewise(null);

		return attendanceDetail;
	}


	//bi-directional many-to-one association to AttendanceDetailsArchived
	@OneToMany(mappedBy="attendanceDatewise")
	public List<AttendanceDetailsArchived> getAttendanceDetailsArchiveds() {
		return this.attendanceDetailsArchiveds;
	}

	public void setAttendanceDetailsArchiveds(List<AttendanceDetailsArchived> attendanceDetailsArchiveds) {
		this.attendanceDetailsArchiveds = attendanceDetailsArchiveds;
	}

	public AttendanceDetailsArchived addAttendanceDetailsArchived(AttendanceDetailsArchived attendanceDetailsArchived) {
		getAttendanceDetailsArchiveds().add(attendanceDetailsArchived);
		attendanceDetailsArchived.setAttendanceDatewise(this);

		return attendanceDetailsArchived;
	}

	public AttendanceDetailsArchived removeAttendanceDetailsArchived(AttendanceDetailsArchived attendanceDetailsArchived) {
		getAttendanceDetailsArchiveds().remove(attendanceDetailsArchived);
		attendanceDetailsArchived.setAttendanceDatewise(null);

		return attendanceDetailsArchived;
	}

}