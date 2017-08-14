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
import com.myschool.sms.entity.embeddedId.ClassTeachersMappingPK;


/**
 * The persistent class for the CLASS_TEACHERS_MAPPING database table.
 * 
 */
@Entity
@Table(name="CLASS_TEACHERS_MAPPING")
@NamedQuery(name="ClassTeachersMapping.findAll", query="SELECT c FROM ClassTeachersMapping c")
public class ClassTeachersMapping extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClassTeachersMappingPK id;

	@Column(name="is_class_teacher", nullable=false)
	private byte isClassTeacher;

	//bi-directional many-to-one association to ClassSectionMst
	@ManyToOne
	@JoinColumn(name="class_sec_id", nullable=false, insertable=false, updatable=false)
	private ClassSectionMst classSectionMst;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private UserEntity userMst1;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="teachers_id", nullable=false, insertable=false, updatable=false)
	private UserEntity userMst2;

	public ClassTeachersMapping() {
	}

	public ClassTeachersMappingPK getId() {
		return this.id;
	}

	public void setId(ClassTeachersMappingPK id) {
		this.id = id;
	}

	public byte getIsClassTeacher() {
		return this.isClassTeacher;
	}

	public void setIsClassTeacher(byte isClassTeacher) {
		this.isClassTeacher = isClassTeacher;
	}

	public ClassSectionMst getClassSectionMst() {
		return this.classSectionMst;
	}

	public void setClassSectionMst(ClassSectionMst classSectionMst) {
		this.classSectionMst = classSectionMst;
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