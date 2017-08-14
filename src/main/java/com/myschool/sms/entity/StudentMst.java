package com.myschool.sms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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

import com.myschool.sms.config.AppConst;
import com.myschool.sms.entity.base.BaseEntity;


/**
 * The persistent class for the STUDENT_MST database table.
 * 
 */
@Entity
@Table(name="STUDENT_MST")
@NamedQuery(name="StudentMst.findAll", query="SELECT s FROM StudentMst s")
public class StudentMst extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=150)
	private String address;

	@Column(name="blood_group", nullable=false, length=10)
	private String bloodGroup;

	private Timestamp dob;

	@Column(name="enrollment_date")
	private Timestamp enrollmentDate;

	@Column(name="enrollment_no", nullable=false)
	private int enrollmentNo;

	@Column(name="fathers_name", nullable=false, length=45)
	private String fathersName;

	@Column(name="fathers_profession", nullable=false, length=45)
	private String fathersProfession;

	@Column(name="mothers_name", nullable=false, length=45)
	private String mothersName;

	@Column(name="mothers_profession", nullable=false, length=45)
	private String mothersProfession;

	@Column(nullable=false, length=1)
	@Enumerated(EnumType.STRING)
	private AppConst.GENDER sex = AppConst.GENDER.MALE;

	@Column(name="student_name", nullable=false, length=45)
	private String studentName;
	
	@JoinColumn(name="parent_id", nullable=false)
	private ParentsMst parentsMst;

	//bi-directional many-to-one association to StudentClassMapping
	@OneToMany(mappedBy="studentMst")
	private List<StudentClassMapping> studentClassMappings;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private UserEntity userMst1;

	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private UserEntity userMst2;

	//bi-directional many-to-one association to StudentResult
	@OneToMany(mappedBy="studentMst")
	private List<StudentResult> studentResults;

	public StudentMst() {
	}
	
	//bi-directional many-to-one association to ParentsMst
	@ManyToOne
	public ParentsMst getParentsMst() {
		return this.parentsMst;
	}

	public void setParentsMst(ParentsMst parentsMst) {
		this.parentsMst = parentsMst;
	}

	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Timestamp getDob() {
		return this.dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}


	public Timestamp getEnrollmentDate() {
		return this.enrollmentDate;
	}

	public void setEnrollmentDate(Timestamp enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public int getEnrollmentNo() {
		return this.enrollmentNo;
	}

	public void setEnrollmentNo(int enrollmentNo) {
		this.enrollmentNo = enrollmentNo;
	}

	public String getFathersName() {
		return this.fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getFathersProfession() {
		return this.fathersProfession;
	}

	public void setFathersProfession(String fathersProfession) {
		this.fathersProfession = fathersProfession;
	}

	public String getMothersName() {
		return this.mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getMothersProfession() {
		return this.mothersProfession;
	}

	public void setMothersProfession(String mothersProfession) {
		this.mothersProfession = mothersProfession;
	}

	public AppConst.GENDER getSex() {
		return sex;
	}

	public void setSex(AppConst.GENDER sex) {
		this.sex = sex;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<StudentClassMapping> getStudentClassMappings() {
		return this.studentClassMappings;
	}

	public void setStudentClassMappings(List<StudentClassMapping> studentClassMappings) {
		this.studentClassMappings = studentClassMappings;
	}

	public StudentClassMapping addStudentClassMapping(StudentClassMapping studentClassMapping) {
		getStudentClassMappings().add(studentClassMapping);
		studentClassMapping.setStudentMst(this);

		return studentClassMapping;
	}

	public StudentClassMapping removeStudentClassMapping(StudentClassMapping studentClassMapping) {
		getStudentClassMappings().remove(studentClassMapping);
		studentClassMapping.setStudentMst(null);

		return studentClassMapping;
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

	public List<StudentResult> getStudentResults() {
		return this.studentResults;
	}

	public void setStudentResults(List<StudentResult> studentResults) {
		this.studentResults = studentResults;
	}

	public StudentResult addStudentResult(StudentResult studentResult) {
		getStudentResults().add(studentResult);
		studentResult.setStudentMst(this);

		return studentResult;
	}

	public StudentResult removeStudentResult(StudentResult studentResult) {
		getStudentResults().remove(studentResult);
		studentResult.setStudentMst(null);

		return studentResult;
	}

}