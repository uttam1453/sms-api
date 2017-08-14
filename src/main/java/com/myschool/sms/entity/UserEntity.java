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
 * The persistent class for the USER_MST database table.
 * 
 */
@Entity
@Table(name="USER_MST")
@NamedQuery(name="UserMst.findAll", query="SELECT u FROM UserMst u")
public class UserEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="email_id", nullable=false, length=60)
	private String emailId;
	
	@Column(name="mobile_no", nullable=false)
	private int mobileNo;
	
	@OneToMany(mappedBy="userMst")
	private List<ParentsMst> parentsMsts;

	@Column(name="active_flag", nullable=false)
	private boolean activeFlag;
	
	@Column(name="password_reset_req", nullable=false)
	private boolean passwordResetReq;

	@Column(name="device_id", length=200)
	private String deviceId;

	@Column(name="device_type", length=45)
	private String deviceType;

	@Column(name="image_url", length=100)
	private String imageUrl;

	@Column(nullable=false, length=100)
	private String password;

	@Column(name="user_name", nullable=false, length=50)
	private String userName;
	
	@OneToMany(mappedBy="userMst")
	private List<UserSessionDetail> userSessionDetails;

	//bi-directional many-to-one association to AttendanceDatewise
	@OneToMany(mappedBy="userMst1")
	private List<AttendanceDatewise> attendanceDatewises1;

	//bi-directional many-to-one association to AttendanceDatewise
	@OneToMany(mappedBy="userMst2")
	private List<AttendanceDatewise> attendanceDatewises2;

	//bi-directional many-to-one association to AttendanceDetail
	@OneToMany(mappedBy="userMst")
	private List<AttendanceDetail> attendanceDetails;

	//bi-directional many-to-one association to AttendanceDetailsArchived
	@OneToMany(mappedBy="userMst")
	private List<AttendanceDetailsArchived> attendanceDetailsArchiveds;

	//bi-directional many-to-one association to ClassMst
	@OneToMany(mappedBy="userMst")
	private List<ClassMst> classMsts;

	//bi-directional many-to-one association to ClassSubjectMapping
	@OneToMany(mappedBy="userMst")
	private List<ClassSubjectMapping> classSubjectMappings;

	//bi-directional many-to-one association to ClassTeachersMapping
	@OneToMany(mappedBy="userMst1")
	private List<ClassTeachersMapping> classTeachersMappings1;

	//bi-directional many-to-one association to ClassTeachersMapping
	@OneToMany(mappedBy="userMst2")
	private List<ClassTeachersMapping> classTeachersMappings2;

	//bi-directional many-to-one association to StaffMst
	@OneToMany(mappedBy="userMst1")
	private List<StaffMst> staffMsts1;

	//bi-directional many-to-one association to StaffMst
	@OneToMany(mappedBy="userMst2")
	private List<StaffMst> staffMsts2;

	//bi-directional many-to-one association to StudentMst
	@OneToMany(mappedBy="userMst1")
	private List<StudentMst> studentMsts1;

	//bi-directional many-to-one association to StudentMst
	@OneToMany(mappedBy="userMst2")
	private List<StudentMst> studentMsts2;

	//bi-directional many-to-one association to StudentResult
	@OneToMany(mappedBy="userMst")
	private List<StudentResult> studentResults;

	//bi-directional many-to-one association to SubjectMst
	@OneToMany(mappedBy="userMst")
	private List<SubjectMst> subjectMsts;

	//bi-directional many-to-one association to SubjectTeachersMapping
	@OneToMany(mappedBy="userMst1")
	private List<SubjectTeachersMapping> subjectTeachersMappings1;

	//bi-directional many-to-one association to SubjectTeachersMapping
	@OneToMany(mappedBy="userMst2")
	private List<SubjectTeachersMapping> subjectTeachersMappings2;

	//bi-directional many-to-one association to SchoolMst
	@ManyToOne
	@JoinColumn(name="school_id", nullable=false)
	private SchoolMst schoolMst;

	//bi-directional many-to-one association to UserRoleMapping
	@OneToMany(mappedBy="userMst")
	private List<UserRoleMapping> userRoleMappings;

	public UserEntity() {
	}
	
	public boolean isPasswordResetReq() {
		return passwordResetReq;
	}

	public void setPasswordResetReq(boolean passwordResetReq) {
		this.passwordResetReq = passwordResetReq;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public int getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}

	public boolean getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}


	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<AttendanceDatewise> getAttendanceDatewises1() {
		return this.attendanceDatewises1;
	}

	public void setAttendanceDatewises1(List<AttendanceDatewise> attendanceDatewises1) {
		this.attendanceDatewises1 = attendanceDatewises1;
	}

	public AttendanceDatewise addAttendanceDatewises1(AttendanceDatewise attendanceDatewises1) {
		getAttendanceDatewises1().add(attendanceDatewises1);
		attendanceDatewises1.setUserMst1(this);

		return attendanceDatewises1;
	}

	public AttendanceDatewise removeAttendanceDatewises1(AttendanceDatewise attendanceDatewises1) {
		getAttendanceDatewises1().remove(attendanceDatewises1);
		attendanceDatewises1.setUserMst1(null);

		return attendanceDatewises1;
	}

	public List<AttendanceDatewise> getAttendanceDatewises2() {
		return this.attendanceDatewises2;
	}

	public void setAttendanceDatewises2(List<AttendanceDatewise> attendanceDatewises2) {
		this.attendanceDatewises2 = attendanceDatewises2;
	}

	public AttendanceDatewise addAttendanceDatewises2(AttendanceDatewise attendanceDatewises2) {
		getAttendanceDatewises2().add(attendanceDatewises2);
		attendanceDatewises2.setUserMst2(this);

		return attendanceDatewises2;
	}

	public AttendanceDatewise removeAttendanceDatewises2(AttendanceDatewise attendanceDatewises2) {
		getAttendanceDatewises2().remove(attendanceDatewises2);
		attendanceDatewises2.setUserMst2(null);

		return attendanceDatewises2;
	}

	public List<AttendanceDetail> getAttendanceDetails() {
		return this.attendanceDetails;
	}

	public void setAttendanceDetails(List<AttendanceDetail> attendanceDetails) {
		this.attendanceDetails = attendanceDetails;
	}

	public AttendanceDetail addAttendanceDetail(AttendanceDetail attendanceDetail) {
		getAttendanceDetails().add(attendanceDetail);
		attendanceDetail.setUserMst(this);

		return attendanceDetail;
	}

	public AttendanceDetail removeAttendanceDetail(AttendanceDetail attendanceDetail) {
		getAttendanceDetails().remove(attendanceDetail);
		attendanceDetail.setUserMst(null);

		return attendanceDetail;
	}

	public List<AttendanceDetailsArchived> getAttendanceDetailsArchiveds() {
		return this.attendanceDetailsArchiveds;
	}

	public void setAttendanceDetailsArchiveds(List<AttendanceDetailsArchived> attendanceDetailsArchiveds) {
		this.attendanceDetailsArchiveds = attendanceDetailsArchiveds;
	}

	public AttendanceDetailsArchived addAttendanceDetailsArchived(AttendanceDetailsArchived attendanceDetailsArchived) {
		getAttendanceDetailsArchiveds().add(attendanceDetailsArchived);
		attendanceDetailsArchived.setUserMst(this);

		return attendanceDetailsArchived;
	}

	public AttendanceDetailsArchived removeAttendanceDetailsArchived(AttendanceDetailsArchived attendanceDetailsArchived) {
		getAttendanceDetailsArchiveds().remove(attendanceDetailsArchived);
		attendanceDetailsArchived.setUserMst(null);

		return attendanceDetailsArchived;
	}

	public List<ClassMst> getClassMsts() {
		return this.classMsts;
	}

	public void setClassMsts(List<ClassMst> classMsts) {
		this.classMsts = classMsts;
	}

	public ClassMst addClassMst(ClassMst classMst) {
		getClassMsts().add(classMst);
		classMst.setUserMst(this);

		return classMst;
	}

	public ClassMst removeClassMst(ClassMst classMst) {
		getClassMsts().remove(classMst);
		classMst.setUserMst(null);

		return classMst;
	}

	public List<ClassSubjectMapping> getClassSubjectMappings() {
		return this.classSubjectMappings;
	}

	public void setClassSubjectMappings(List<ClassSubjectMapping> classSubjectMappings) {
		this.classSubjectMappings = classSubjectMappings;
	}

	public ClassSubjectMapping addClassSubjectMapping(ClassSubjectMapping classSubjectMapping) {
		getClassSubjectMappings().add(classSubjectMapping);
		classSubjectMapping.setUserMst(this);

		return classSubjectMapping;
	}

	public ClassSubjectMapping removeClassSubjectMapping(ClassSubjectMapping classSubjectMapping) {
		getClassSubjectMappings().remove(classSubjectMapping);
		classSubjectMapping.setUserMst(null);

		return classSubjectMapping;
	}

	public List<ClassTeachersMapping> getClassTeachersMappings1() {
		return this.classTeachersMappings1;
	}

	public void setClassTeachersMappings1(List<ClassTeachersMapping> classTeachersMappings1) {
		this.classTeachersMappings1 = classTeachersMappings1;
	}

	public ClassTeachersMapping addClassTeachersMappings1(ClassTeachersMapping classTeachersMappings1) {
		getClassTeachersMappings1().add(classTeachersMappings1);
		classTeachersMappings1.setUserMst1(this);

		return classTeachersMappings1;
	}

	public ClassTeachersMapping removeClassTeachersMappings1(ClassTeachersMapping classTeachersMappings1) {
		getClassTeachersMappings1().remove(classTeachersMappings1);
		classTeachersMappings1.setUserMst1(null);

		return classTeachersMappings1;
	}

	public List<ClassTeachersMapping> getClassTeachersMappings2() {
		return this.classTeachersMappings2;
	}

	public void setClassTeachersMappings2(List<ClassTeachersMapping> classTeachersMappings2) {
		this.classTeachersMappings2 = classTeachersMappings2;
	}

	public ClassTeachersMapping addClassTeachersMappings2(ClassTeachersMapping classTeachersMappings2) {
		getClassTeachersMappings2().add(classTeachersMappings2);
		classTeachersMappings2.setUserMst2(this);

		return classTeachersMappings2;
	}

	public ClassTeachersMapping removeClassTeachersMappings2(ClassTeachersMapping classTeachersMappings2) {
		getClassTeachersMappings2().remove(classTeachersMappings2);
		classTeachersMappings2.setUserMst2(null);

		return classTeachersMappings2;
	}

	public List<StaffMst> getStaffMsts1() {
		return this.staffMsts1;
	}

	public void setStaffMsts1(List<StaffMst> staffMsts1) {
		this.staffMsts1 = staffMsts1;
	}

	public StaffMst addStaffMsts1(StaffMst staffMsts1) {
		getStaffMsts1().add(staffMsts1);
		staffMsts1.setUserMst1(this);

		return staffMsts1;
	}

	public StaffMst removeStaffMsts1(StaffMst staffMsts1) {
		getStaffMsts1().remove(staffMsts1);
		staffMsts1.setUserMst1(null);

		return staffMsts1;
	}

	public List<StaffMst> getStaffMsts2() {
		return this.staffMsts2;
	}

	public void setStaffMsts2(List<StaffMst> staffMsts2) {
		this.staffMsts2 = staffMsts2;
	}

	public StaffMst addStaffMsts2(StaffMst staffMsts2) {
		getStaffMsts2().add(staffMsts2);
		staffMsts2.setUserMst2(this);

		return staffMsts2;
	}

	public StaffMst removeStaffMsts2(StaffMst staffMsts2) {
		getStaffMsts2().remove(staffMsts2);
		staffMsts2.setUserMst2(null);

		return staffMsts2;
	}

	public List<StudentMst> getStudentMsts1() {
		return this.studentMsts1;
	}

	public void setStudentMsts1(List<StudentMst> studentMsts1) {
		this.studentMsts1 = studentMsts1;
	}

	public StudentMst addStudentMsts1(StudentMst studentMsts1) {
		getStudentMsts1().add(studentMsts1);
		studentMsts1.setUserMst1(this);

		return studentMsts1;
	}

	public StudentMst removeStudentMsts1(StudentMst studentMsts1) {
		getStudentMsts1().remove(studentMsts1);
		studentMsts1.setUserMst1(null);

		return studentMsts1;
	}

	public List<StudentMst> getStudentMsts2() {
		return this.studentMsts2;
	}

	public void setStudentMsts2(List<StudentMst> studentMsts2) {
		this.studentMsts2 = studentMsts2;
	}

	public StudentMst addStudentMsts2(StudentMst studentMsts2) {
		getStudentMsts2().add(studentMsts2);
		studentMsts2.setUserMst2(this);

		return studentMsts2;
	}

	public StudentMst removeStudentMsts2(StudentMst studentMsts2) {
		getStudentMsts2().remove(studentMsts2);
		studentMsts2.setUserMst2(null);

		return studentMsts2;
	}

	public List<StudentResult> getStudentResults() {
		return this.studentResults;
	}

	public void setStudentResults(List<StudentResult> studentResults) {
		this.studentResults = studentResults;
	}

	public StudentResult addStudentResult(StudentResult studentResult) {
		getStudentResults().add(studentResult);
		studentResult.setUserMst(this);

		return studentResult;
	}

	public StudentResult removeStudentResult(StudentResult studentResult) {
		getStudentResults().remove(studentResult);
		studentResult.setUserMst(null);

		return studentResult;
	}

	public List<SubjectMst> getSubjectMsts() {
		return this.subjectMsts;
	}

	public void setSubjectMsts(List<SubjectMst> subjectMsts) {
		this.subjectMsts = subjectMsts;
	}

	public SubjectMst addSubjectMst(SubjectMst subjectMst) {
		getSubjectMsts().add(subjectMst);
		subjectMst.setUserMst(this);

		return subjectMst;
	}

	public SubjectMst removeSubjectMst(SubjectMst subjectMst) {
		getSubjectMsts().remove(subjectMst);
		subjectMst.setUserMst(null);

		return subjectMst;
	}

	public List<SubjectTeachersMapping> getSubjectTeachersMappings1() {
		return this.subjectTeachersMappings1;
	}

	public void setSubjectTeachersMappings1(List<SubjectTeachersMapping> subjectTeachersMappings1) {
		this.subjectTeachersMappings1 = subjectTeachersMappings1;
	}

	public SubjectTeachersMapping addSubjectTeachersMappings1(SubjectTeachersMapping subjectTeachersMappings1) {
		getSubjectTeachersMappings1().add(subjectTeachersMappings1);
		subjectTeachersMappings1.setUserMst1(this);

		return subjectTeachersMappings1;
	}

	public SubjectTeachersMapping removeSubjectTeachersMappings1(SubjectTeachersMapping subjectTeachersMappings1) {
		getSubjectTeachersMappings1().remove(subjectTeachersMappings1);
		subjectTeachersMappings1.setUserMst1(null);

		return subjectTeachersMappings1;
	}

	public List<SubjectTeachersMapping> getSubjectTeachersMappings2() {
		return this.subjectTeachersMappings2;
	}

	public void setSubjectTeachersMappings2(List<SubjectTeachersMapping> subjectTeachersMappings2) {
		this.subjectTeachersMappings2 = subjectTeachersMappings2;
	}

	public SubjectTeachersMapping addSubjectTeachersMappings2(SubjectTeachersMapping subjectTeachersMappings2) {
		getSubjectTeachersMappings2().add(subjectTeachersMappings2);
		subjectTeachersMappings2.setUserMst2(this);

		return subjectTeachersMappings2;
	}

	public SubjectTeachersMapping removeSubjectTeachersMappings2(SubjectTeachersMapping subjectTeachersMappings2) {
		getSubjectTeachersMappings2().remove(subjectTeachersMappings2);
		subjectTeachersMappings2.setUserMst2(null);

		return subjectTeachersMappings2;
	}

	public SchoolMst getSchoolMst() {
		return this.schoolMst;
	}

	public void setSchoolMst(SchoolMst schoolMst) {
		this.schoolMst = schoolMst;
	}

	public List<UserRoleMapping> getUserRoleMappings() {
		return this.userRoleMappings;
	}

	public void setUserRoleMappings(List<UserRoleMapping> userRoleMappings) {
		this.userRoleMappings = userRoleMappings;
	}

	public UserRoleMapping addUserRoleMapping(UserRoleMapping userRoleMapping) {
		getUserRoleMappings().add(userRoleMapping);
		userRoleMapping.setUserMst(this);

		return userRoleMapping;
	}

	public UserRoleMapping removeUserRoleMapping(UserRoleMapping userRoleMapping) {
		getUserRoleMappings().remove(userRoleMapping);
		userRoleMapping.setUserMst(null);

		return userRoleMapping;
	}
	
	//bi-directional many-to-one association to ParentsMst
	public List<ParentsMst> getParentsMsts() {
		return this.parentsMsts;
	}

	public void setParentsMsts(List<ParentsMst> parentsMsts) {
		this.parentsMsts = parentsMsts;
	}

	public ParentsMst addParentsMst(ParentsMst parentsMst) {
		getParentsMsts().add(parentsMst);
		parentsMst.setUserMst(this);

		return parentsMst;
	}

	public ParentsMst removeParentsMst(ParentsMst parentsMst) {
		getParentsMsts().remove(parentsMst);
		parentsMst.setUserMst(null);

		return parentsMst;
	}

	//bi-directional many-to-one association to UserSessionDetail
	public List<UserSessionDetail> getUserSessionDetails() {
		return this.userSessionDetails;
	}

	public void setUserSessionDetails(List<UserSessionDetail> userSessionDetails) {
		this.userSessionDetails = userSessionDetails;
	}

	public UserSessionDetail addUserSessionDetail(UserSessionDetail userSessionDetail) {
		getUserSessionDetails().add(userSessionDetail);
		userSessionDetail.setUserMst(this);

		return userSessionDetail;
	}

	public UserSessionDetail removeUserSessionDetail(UserSessionDetail userSessionDetail) {
		getUserSessionDetails().remove(userSessionDetail);
		userSessionDetail.setUserMst(null);

		return userSessionDetail;
	}

}