package com.myschool.sms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the PARENTS_MST database table.
 * 
 */
@Entity
@Table(name="PARENTS_MST")
@NamedQuery(name="ParentsMst.findAll", query="SELECT p FROM ParentsMst p")
public class ParentsMst implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String profession;
	private String spouse;
	private String spouseProfession;
	private UserEntity userMst;
	private List<StudentMst> studentMsts;

	public ParentsMst() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(nullable=false, length=45)
	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}


	@Column(nullable=false, length=45)
	public String getSpouse() {
		return this.spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}


	@Column(name="spouse_profession", length=45)
	public String getSpouseProfession() {
		return this.spouseProfession;
	}

	public void setSpouseProfession(String spouseProfession) {
		this.spouseProfession = spouseProfession;
	}


	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	public UserEntity getUserMst() {
		return this.userMst;
	}

	public void setUserMst(UserEntity userMst) {
		this.userMst = userMst;
	}


	//bi-directional many-to-one association to StudentMst
	@OneToMany(mappedBy="parentsMst")
	public List<StudentMst> getStudentMsts() {
		return this.studentMsts;
	}

	public void setStudentMsts(List<StudentMst> studentMsts) {
		this.studentMsts = studentMsts;
	}

	public StudentMst addStudentMst(StudentMst studentMst) {
		getStudentMsts().add(studentMst);
		studentMst.setParentsMst(this);

		return studentMst;
	}

	public StudentMst removeStudentMst(StudentMst studentMst) {
		getStudentMsts().remove(studentMst);
		studentMst.setParentsMst(null);

		return studentMst;
	}

}