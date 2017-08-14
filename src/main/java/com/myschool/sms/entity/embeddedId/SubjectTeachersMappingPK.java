package com.myschool.sms.entity.embeddedId;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SUBJECT_TEACHERS_MAPPING database table.
 * 
 */
@Embeddable
public class SubjectTeachersMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="subject_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int subjectId;

	@Column(name="teachers_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int teachersId;

	public SubjectTeachersMappingPK() {
	}
	public int getSubjectId() {
		return this.subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getTeachersId() {
		return this.teachersId;
	}
	public void setTeachersId(int teachersId) {
		this.teachersId = teachersId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubjectTeachersMappingPK)) {
			return false;
		}
		SubjectTeachersMappingPK castOther = (SubjectTeachersMappingPK)other;
		return 
			(this.subjectId == castOther.subjectId)
			&& (this.teachersId == castOther.teachersId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.subjectId;
		hash = hash * prime + this.teachersId;
		
		return hash;
	}
}