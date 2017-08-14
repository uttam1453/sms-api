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
import com.myschool.sms.entity.embeddedId.StudentResultDetailPK;


/**
 * The persistent class for the STUDENT_RESULT_DETAIL database table.
 * 
 */
@Entity
@Table(name="STUDENT_RESULT_DETAIL")
@NamedQuery(name="StudentResultDetail.findAll", query="SELECT s FROM StudentResultDetail s")
public class StudentResultDetail extends AuditEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StudentResultDetailPK id;

	@Column(name="max_marks", nullable=false)
	private double maxMarks;

	@Column(name="obtained_marks", nullable=false)
	private double obtainedMarks;

	//bi-directional many-to-one association to StudentResult
	@ManyToOne
	@JoinColumn(name="student_res_id", nullable=false, insertable=false, updatable=false)
	private StudentResult studentResult;

	//bi-directional many-to-one association to SubjectMst
	@ManyToOne
	@JoinColumn(name="subject_id", nullable=false, insertable=false, updatable=false)
	private SubjectMst subjectMst;

	public StudentResultDetail() {
	}

	public StudentResultDetailPK getId() {
		return this.id;
	}

	public void setId(StudentResultDetailPK id) {
		this.id = id;
	}

	public double getMaxMarks() {
		return this.maxMarks;
	}

	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}


	public double getObtainedMarks() {
		return this.obtainedMarks;
	}

	public void setObtainedMarks(double obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	public StudentResult getStudentResult() {
		return this.studentResult;
	}

	public void setStudentResult(StudentResult studentResult) {
		this.studentResult = studentResult;
	}

	public SubjectMst getSubjectMst() {
		return this.subjectMst;
	}

	public void setSubjectMst(SubjectMst subjectMst) {
		this.subjectMst = subjectMst;
	}

}