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
import com.myschool.sms.entity.embeddedId.ClassSubjectMappingPK;


/**
 * The persistent class for the CLASS_SUBJECT_MAPPING database table.
 * 
 */
@Entity
@Table(name="CLASS_SUBJECT_MAPPING")
@NamedQuery(name="ClassSubjectMapping.findAll", query="SELECT c FROM ClassSubjectMapping c")
public class ClassSubjectMapping extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClassSubjectMappingPK id;

	@Column(name="active_flag", nullable=false)
	private byte activeFlag;

	//bi-directional many-to-one association to ClassSectionMst
	@ManyToOne
	@JoinColumn(name="class_sec_id", nullable=false, insertable=false, updatable=false)
	private ClassSectionMst classSectionMst;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private UserEntity userMst;

	//bi-directional many-to-one association to SubjectMst
	@ManyToOne
	@JoinColumn(name="subject_id", nullable=false, insertable=false, updatable=false)
	private SubjectMst subjectMst;

	public ClassSubjectMapping() {
	}

	public ClassSubjectMappingPK getId() {
		return this.id;
	}

	public void setId(ClassSubjectMappingPK id) {
		this.id = id;
	}

	public byte getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(byte activeFlag) {
		this.activeFlag = activeFlag;
	}

	public ClassSectionMst getClassSectionMst() {
		return this.classSectionMst;
	}

	public void setClassSectionMst(ClassSectionMst classSectionMst) {
		this.classSectionMst = classSectionMst;
	}

	public UserEntity getUserMst() {
		return this.userMst;
	}

	public void setUserMst(UserEntity userMst) {
		this.userMst = userMst;
	}

	public SubjectMst getSubjectMst() {
		return this.subjectMst;
	}

	public void setSubjectMst(SubjectMst subjectMst) {
		this.subjectMst = subjectMst;
	}

}