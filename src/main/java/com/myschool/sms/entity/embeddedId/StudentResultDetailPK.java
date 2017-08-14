package com.myschool.sms.entity.embeddedId;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the STUDENT_RESULT_DETAIL database table.
 * 
 */
@Embeddable
public class StudentResultDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="student_res_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int studentResId;

	@Column(name="subject_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int subjectId;

	public StudentResultDetailPK() {
	}
	public int getStudentResId() {
		return this.studentResId;
	}
	public void setStudentResId(int studentResId) {
		this.studentResId = studentResId;
	}
	public int getSubjectId() {
		return this.subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StudentResultDetailPK)) {
			return false;
		}
		StudentResultDetailPK castOther = (StudentResultDetailPK)other;
		return 
			(this.studentResId == castOther.studentResId)
			&& (this.subjectId == castOther.subjectId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.studentResId;
		hash = hash * prime + this.subjectId;
		
		return hash;
	}
}