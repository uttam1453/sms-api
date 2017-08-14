package com.myschool.sms.entity.embeddedId;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ATTENDANCE_DETAILS_ARCHIVED database table.
 * 
 */
@Embeddable
public class AttendanceDetailsArchivedPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="attendance_dt_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int attendanceDtId;

	@Column(name="student_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int studentId;

	public AttendanceDetailsArchivedPK() {
	}
	public int getAttendanceDtId() {
		return this.attendanceDtId;
	}
	public void setAttendanceDtId(int attendanceDtId) {
		this.attendanceDtId = attendanceDtId;
	}
	public int getStudentId() {
		return this.studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AttendanceDetailsArchivedPK)) {
			return false;
		}
		AttendanceDetailsArchivedPK castOther = (AttendanceDetailsArchivedPK)other;
		return 
			(this.attendanceDtId == castOther.attendanceDtId)
			&& (this.studentId == castOther.studentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.attendanceDtId;
		hash = hash * prime + this.studentId;
		
		return hash;
	}
}