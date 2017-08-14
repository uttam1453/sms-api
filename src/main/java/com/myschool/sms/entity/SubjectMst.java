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
 * The persistent class for the SUBJECT_MST database table.
 * 
 */
@Entity
@Table(name="SUBJECT_MST")
@NamedQuery(name="SubjectMst.findAll", query="SELECT s FROM SubjectMst s")
public class SubjectMst extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="active_flag", nullable=false)
	private byte activeFlag;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to ClassSubjectMapping
	@OneToMany(mappedBy="subjectMst")
	private List<ClassSubjectMapping> classSubjectMappings;

	//bi-directional many-to-one association to StudentResultDetail
	@OneToMany(mappedBy="subjectMst")
	private List<StudentResultDetail> studentResultDetails;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private UserEntity userMst;

	//bi-directional many-to-one association to SubjectTeachersMapping
	@OneToMany(mappedBy="subjectMst")
	private List<SubjectTeachersMapping> subjectTeachersMappings;

	public SubjectMst() {
	}

	public byte getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(byte activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ClassSubjectMapping> getClassSubjectMappings() {
		return this.classSubjectMappings;
	}

	public void setClassSubjectMappings(List<ClassSubjectMapping> classSubjectMappings) {
		this.classSubjectMappings = classSubjectMappings;
	}

	public ClassSubjectMapping addClassSubjectMapping(ClassSubjectMapping classSubjectMapping) {
		getClassSubjectMappings().add(classSubjectMapping);
		classSubjectMapping.setSubjectMst(this);

		return classSubjectMapping;
	}

	public ClassSubjectMapping removeClassSubjectMapping(ClassSubjectMapping classSubjectMapping) {
		getClassSubjectMappings().remove(classSubjectMapping);
		classSubjectMapping.setSubjectMst(null);

		return classSubjectMapping;
	}

	public List<StudentResultDetail> getStudentResultDetails() {
		return this.studentResultDetails;
	}

	public void setStudentResultDetails(List<StudentResultDetail> studentResultDetails) {
		this.studentResultDetails = studentResultDetails;
	}

	public StudentResultDetail addStudentResultDetail(StudentResultDetail studentResultDetail) {
		getStudentResultDetails().add(studentResultDetail);
		studentResultDetail.setSubjectMst(this);

		return studentResultDetail;
	}

	public StudentResultDetail removeStudentResultDetail(StudentResultDetail studentResultDetail) {
		getStudentResultDetails().remove(studentResultDetail);
		studentResultDetail.setSubjectMst(null);

		return studentResultDetail;
	}

	public UserEntity getUserMst() {
		return this.userMst;
	}

	public void setUserMst(UserEntity userMst) {
		this.userMst = userMst;
	}

	public List<SubjectTeachersMapping> getSubjectTeachersMappings() {
		return this.subjectTeachersMappings;
	}

	public void setSubjectTeachersMappings(List<SubjectTeachersMapping> subjectTeachersMappings) {
		this.subjectTeachersMappings = subjectTeachersMappings;
	}

	public SubjectTeachersMapping addSubjectTeachersMapping(SubjectTeachersMapping subjectTeachersMapping) {
		getSubjectTeachersMappings().add(subjectTeachersMapping);
		subjectTeachersMapping.setSubjectMst(this);

		return subjectTeachersMapping;
	}

	public SubjectTeachersMapping removeSubjectTeachersMapping(SubjectTeachersMapping subjectTeachersMapping) {
		getSubjectTeachersMappings().remove(subjectTeachersMapping);
		subjectTeachersMapping.setSubjectMst(null);

		return subjectTeachersMapping;
	}

}