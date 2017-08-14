package com.myschool.sms.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.myschool.sms.entity.base.AuditEntity;
import com.myschool.sms.entity.embeddedId.SubjectTeachersMappingPK;


/**
 * The persistent class for the SUBJECT_TEACHERS_MAPPING database table.
 * 
 */
@Entity
@Table(name="SUBJECT_TEACHERS_MAPPING")
@NamedQuery(name="SubjectTeachersMapping.findAll", query="SELECT s FROM SubjectTeachersMapping s")
public class SubjectTeachersMapping extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubjectTeachersMappingPK id;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private UserEntity userMst1;

	//bi-directional many-to-one association to SubjectMst
	@ManyToOne
	@JoinColumn(name="subject_id", nullable=false, insertable=false, updatable=false)
	private SubjectMst subjectMst;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="teachers_id", nullable=false, insertable=false, updatable=false)
	private UserEntity userMst2;

	public SubjectTeachersMapping() {
	}

	public SubjectTeachersMappingPK getId() {
		return this.id;
	}

	public void setId(SubjectTeachersMappingPK id) {
		this.id = id;
	}

	public UserEntity getUserMst1() {
		return this.userMst1;
	}

	public void setUserMst1(UserEntity userMst1) {
		this.userMst1 = userMst1;
	}

	public SubjectMst getSubjectMst() {
		return this.subjectMst;
	}

	public void setSubjectMst(SubjectMst subjectMst) {
		this.subjectMst = subjectMst;
	}

	public UserEntity getUserMst2() {
		return this.userMst2;
	}

	public void setUserMst2(UserEntity userMst2) {
		this.userMst2 = userMst2;
	}

}