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
import com.myschool.sms.entity.embeddedId.StudentClassMappingPK;


/**
 * The persistent class for the STUDENT_CLASS_MAPPING database table.
 * 
 */
@Entity
@Table(name="STUDENT_CLASS_MAPPING")
@NamedQuery(name="StudentClassMapping.findAll", query="SELECT s FROM StudentClassMapping s")
public class StudentClassMapping  extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StudentClassMappingPK id;

	@Column(name="current_class", nullable=false)
	private byte currentClass;

	@Column(name="roll_no", nullable=false, length=45)
	private String rollNo;

	@Column(nullable=false, length=10)
	private String year;

	//bi-directional many-to-one association to ClassMst
	@ManyToOne
	@JoinColumn(name="class_id", nullable=false, insertable=false, updatable=false)
	private ClassMst classMst;

	//bi-directional many-to-one association to StudentMst
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false, insertable=false, updatable=false)
	private StudentMst studentMst;

	public StudentClassMapping() {
	}

	public StudentClassMappingPK getId() {
		return this.id;
	}

	public void setId(StudentClassMappingPK id) {
		this.id = id;
	}

	public byte getCurrentClass() {
		return this.currentClass;
	}

	public void setCurrentClass(byte currentClass) {
		this.currentClass = currentClass;
	}

	public String getRollNo() {
		return this.rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
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

	public StudentMst getStudentMst() {
		return this.studentMst;
	}

	public void setStudentMst(StudentMst studentMst) {
		this.studentMst = studentMst;
	}

}