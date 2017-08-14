package com.myschool.sms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.myschool.sms.entity.base.BaseEntity;


/**
 * The persistent class for the CLASS_MST database table.
 * 
 */
@Entity
@Table(name="CLASS_MST")
@NamedQuery(name="ClassMst.findAll", query="SELECT c FROM ClassMst c")
public class ClassMst extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum CLASS{
		LKG, UKG, CLASS_ONE, CLASS_TWO, CLASS_THREE, CLASS_FOURTH, CLASS_FIVE, CLASS_SIX, CLASS_SEVEN, CLASS_EIGHT, CLASS_NINE, CLASS_TEN, CLASS_ELEVEN, CLASS_TWELVE
	}
	
	@Column(name="class", length=1)
	@Enumerated(EnumType.STRING)
	private CLASS className;

	@Column(name="grade_system", nullable=false)
	private boolean gradeSystem;

	@Column(name="school_id", nullable=false)
	private int schoolId;
	
	public enum ATTENDANCE_SYSTEM{
		DAYWISE, DATEWISE
	}
	
	@Column(name="attendance_system", nullable=false,length=1)
	@Enumerated(EnumType.STRING)
	private ATTENDANCE_SYSTEM attendanceType=ATTENDANCE_SYSTEM.DAYWISE;
	
	//bi-directional many-to-one association to AttendanceDatewise
	@OneToMany(mappedBy="classMst")
	private List<AttendanceDatewise> attendanceDatewises;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private UserEntity userMst;

	//bi-directional many-to-one association to ClassSectionMst
	@OneToMany(mappedBy="classMst")
	private List<ClassSectionMst> classSectionMsts;

	//bi-directional many-to-one association to StudentClassMapping
	@OneToMany(mappedBy="classMst")
	private List<StudentClassMapping> studentClassMappings;

	//bi-directional many-to-one association to StudentResult
	@OneToMany(mappedBy="classMst")
	private List<StudentResult> studentResults;

	public ClassMst() {
	}
	
	public ATTENDANCE_SYSTEM getAttendanceType() {
		return attendanceType;
	}

	public void setAttendanceType(ATTENDANCE_SYSTEM attendanceType) {
		this.attendanceType = attendanceType;
	}

	public CLASS getClassName() {
		return className;
	}

	public void setClassName(CLASS className) {
		this.className = className;
	}

	public boolean getGradeSystem() {
		return this.gradeSystem;
	}

	public void setGradeSystem(boolean gradeSystem) {
		this.gradeSystem = gradeSystem;
	}

	public int getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public List<AttendanceDatewise> getAttendanceDatewises() {
		return this.attendanceDatewises;
	}

	public void setAttendanceDatewises(List<AttendanceDatewise> attendanceDatewises) {
		this.attendanceDatewises = attendanceDatewises;
	}

	public AttendanceDatewise addAttendanceDatewis(AttendanceDatewise attendanceDatewis) {
		getAttendanceDatewises().add(attendanceDatewis);
		attendanceDatewis.setClassMst(this);

		return attendanceDatewis;
	}

	public AttendanceDatewise removeAttendanceDatewis(AttendanceDatewise attendanceDatewis) {
		getAttendanceDatewises().remove(attendanceDatewis);
		attendanceDatewis.setClassMst(null);

		return attendanceDatewis;
	}

	public UserEntity getUserMst() {
		return this.userMst;
	}

	public void setUserMst(UserEntity userMst) {
		this.userMst = userMst;
	}

	public List<ClassSectionMst> getClassSectionMsts() {
		return this.classSectionMsts;
	}

	public void setClassSectionMsts(List<ClassSectionMst> classSectionMsts) {
		this.classSectionMsts = classSectionMsts;
	}

	public ClassSectionMst addClassSectionMst(ClassSectionMst classSectionMst) {
		getClassSectionMsts().add(classSectionMst);
		classSectionMst.setClassMst(this);

		return classSectionMst;
	}

	public ClassSectionMst removeClassSectionMst(ClassSectionMst classSectionMst) {
		getClassSectionMsts().remove(classSectionMst);
		classSectionMst.setClassMst(null);

		return classSectionMst;
	}

	public List<StudentClassMapping> getStudentClassMappings() {
		return this.studentClassMappings;
	}

	public void setStudentClassMappings(List<StudentClassMapping> studentClassMappings) {
		this.studentClassMappings = studentClassMappings;
	}

	public StudentClassMapping addStudentClassMapping(StudentClassMapping studentClassMapping) {
		getStudentClassMappings().add(studentClassMapping);
		studentClassMapping.setClassMst(this);

		return studentClassMapping;
	}

	public StudentClassMapping removeStudentClassMapping(StudentClassMapping studentClassMapping) {
		getStudentClassMappings().remove(studentClassMapping);
		studentClassMapping.setClassMst(null);

		return studentClassMapping;
	}

	public List<StudentResult> getStudentResults() {
		return this.studentResults;
	}

	public void setStudentResults(List<StudentResult> studentResults) {
		this.studentResults = studentResults;
	}

	public StudentResult addStudentResult(StudentResult studentResult) {
		getStudentResults().add(studentResult);
		studentResult.setClassMst(this);

		return studentResult;
	}

	public StudentResult removeStudentResult(StudentResult studentResult) {
		getStudentResults().remove(studentResult);
		studentResult.setClassMst(null);

		return studentResult;
	}

}