package com.myschool.sms.entity.embeddedId;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the STUDENT_CLASS_MAPPING database table.
 * 
 */
@Embeddable
public class StudentClassMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="student_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int studentId;

	@Column(name="class_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int classId;

	public StudentClassMappingPK() {
	}
	public int getStudentId() {
		return this.studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getClassId() {
		return this.classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof StudentClassMappingPK)) {
			return false;
		}
		StudentClassMappingPK castOther = (StudentClassMappingPK)other;
		return 
			(this.studentId == castOther.studentId)
			&& (this.classId == castOther.classId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.studentId;
		hash = hash * prime + this.classId;
		
		return hash;
	}
}