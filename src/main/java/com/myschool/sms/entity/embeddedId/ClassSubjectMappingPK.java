package com.myschool.sms.entity.embeddedId;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CLASS_SUBJECT_MAPPING database table.
 * 
 */
@Embeddable
public class ClassSubjectMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="subject_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int subjectId;

	@Column(name="class_sec_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int classSecId;

	public ClassSubjectMappingPK() {
	}
	public int getSubjectId() {
		return this.subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getClassSecId() {
		return this.classSecId;
	}
	public void setClassSecId(int classSecId) {
		this.classSecId = classSecId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ClassSubjectMappingPK)) {
			return false;
		}
		ClassSubjectMappingPK castOther = (ClassSubjectMappingPK)other;
		return 
			(this.subjectId == castOther.subjectId)
			&& (this.classSecId == castOther.classSecId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.subjectId;
		hash = hash * prime + this.classSecId;
		
		return hash;
	}
}