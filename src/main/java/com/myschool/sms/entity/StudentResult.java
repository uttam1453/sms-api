package com.myschool.sms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * The persistent class for the STUDENT_RESULT database table.
 * 
 */
@Entity
@Table(name="STUDENT_RESULT")
@NamedQuery(name="StudentResult.findAll", query="SELECT s FROM StudentResult s")
public class StudentResult extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false)
	private Timestamp date;

	@Column(name="exam_type", nullable=false, length=1)
	private String examType;

	@Column(name="grade_obtained", length=10)
	private String gradeObtained;

	@Column(name="maximum_marks", nullable=false)
	private double maximumMarks;

	@Column(name="obtained_marks", nullable=false)
	private double obtainedMarks;

	@Column(nullable=false, length=10)
	private String year;

	//bi-directional many-to-one association to ClassMst
	@ManyToOne
	@JoinColumn(name="class_id", nullable=false)
	private ClassMst classMst;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private UserEntity userMst;

	//bi-directional many-to-one association to StudentMst
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private StudentMst studentMst;

	//bi-directional many-to-one association to StudentResultDetail
	@OneToMany(mappedBy="studentResult")
	private List<StudentResultDetail> studentResultDetails;

	public StudentResult() {
	}


	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getExamType() {
		return this.examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getGradeObtained() {
		return this.gradeObtained;
	}

	public void setGradeObtained(String gradeObtained) {
		this.gradeObtained = gradeObtained;
	}

	public double getMaximumMarks() {
		return this.maximumMarks;
	}

	public void setMaximumMarks(double maximumMarks) {
		this.maximumMarks = maximumMarks;
	}

	public double getObtainedMarks() {
		return this.obtainedMarks;
	}

	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public ClassMst getClassMst() {
		return this.classMst;
	}

	public void setClassMst(ClassMst classMst) {
		this.classMst = classMst;
	}

	public UserEntity getUserMst() {
		return this.userMst;
	}

	public void setUserMst(UserEntity userMst) {
		this.userMst = userMst;
	}

	public StudentMst getStudentMst() {
		return this.studentMst;
	}

	public void setStudentMst(StudentMst studentMst) {
		this.studentMst = studentMst;
	}

	public List<StudentResultDetail> getStudentResultDetails() {
		return this.studentResultDetails;
	}

	public void setStudentResultDetails(List<StudentResultDetail> studentResultDetails) {
		this.studentResultDetails = studentResultDetails;
	}

	public StudentResultDetail addStudentResultDetail(StudentResultDetail studentResultDetail) {
		getStudentResultDetails().add(studentResultDetail);
		studentResultDetail.setStudentResult(this);

		return studentResultDetail;
	}

	public StudentResultDetail removeStudentResultDetail(StudentResultDetail studentResultDetail) {
		getStudentResultDetails().remove(studentResultDetail);
		studentResultDetail.setStudentResult(null);

		return studentResultDetail;
	}

}