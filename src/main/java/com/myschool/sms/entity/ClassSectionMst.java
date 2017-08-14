package com.myschool.sms.entity;

import java.io.Serializable;
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
 * The persistent class for the CLASS_SECTION_MST database table.
 * 
 */
@Entity
@Table(name="CLASS_SECTION_MST")
@NamedQuery(name="ClassSectionMst.findAll", query="SELECT c FROM ClassSectionMst c")
public class ClassSectionMst extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=45)
	private String section;

	@Column(name="total_student", nullable=false)
	private int totalStudent;

	//bi-directional many-to-one association to ClassMst
	@ManyToOne
	@JoinColumn(name="class_id", nullable=false)
	private ClassMst classMst;

	//bi-directional many-to-one association to ClassSubjectMapping
	@OneToMany(mappedBy="classSectionMst")
	private List<ClassSubjectMapping> classSubjectMappings;

	//bi-directional many-to-one association to ClassTeachersMapping
	@OneToMany(mappedBy="classSectionMst")
	private List<ClassTeachersMapping> classTeachersMappings;

	public ClassSectionMst() {
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getTotalStudent() {
		return this.totalStudent;
	}

	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}

	public ClassMst getClassMst() {
		return this.classMst;
	}

	public void setClassMst(ClassMst classMst) {
		this.classMst = classMst;
	}

	public List<ClassSubjectMapping> getClassSubjectMappings() {
		return this.classSubjectMappings;
	}

	public void setClassSubjectMappings(List<ClassSubjectMapping> classSubjectMappings) {
		this.classSubjectMappings = classSubjectMappings;
	}

	public ClassSubjectMapping addClassSubjectMapping(ClassSubjectMapping classSubjectMapping) {
		getClassSubjectMappings().add(classSubjectMapping);
		classSubjectMapping.setClassSectionMst(this);

		return classSubjectMapping;
	}

	public ClassSubjectMapping removeClassSubjectMapping(ClassSubjectMapping classSubjectMapping) {
		getClassSubjectMappings().remove(classSubjectMapping);
		classSubjectMapping.setClassSectionMst(null);

		return classSubjectMapping;
	}

	public List<ClassTeachersMapping> getClassTeachersMappings() {
		return this.classTeachersMappings;
	}

	public void setClassTeachersMappings(List<ClassTeachersMapping> classTeachersMappings) {
		this.classTeachersMappings = classTeachersMappings;
	}

	public ClassTeachersMapping addClassTeachersMapping(ClassTeachersMapping classTeachersMapping) {
		getClassTeachersMappings().add(classTeachersMapping);
		classTeachersMapping.setClassSectionMst(this);

		return classTeachersMapping;
	}

	public ClassTeachersMapping removeClassTeachersMapping(ClassTeachersMapping classTeachersMapping) {
		getClassTeachersMappings().remove(classTeachersMapping);
		classTeachersMapping.setClassSectionMst(null);

		return classTeachersMapping;
	}

}