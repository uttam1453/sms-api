package com.myschool.sms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.myschool.sms.entity.base.BaseEntity;


/**
 * The persistent class for the INSTITUTION_TYPE database table.
 * 
 */
@Entity
@Table(name="INSTITUTION_TYPE")
@NamedQuery(name="InstitutionType.findAll", query="SELECT i FROM InstitutionType i")
public class InstitutionType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=45)
	private String name;

	//bi-directional many-to-one association to SchoolMst
	@OneToMany(mappedBy="institutionType")
	private List<SchoolMst> schoolMsts;

	public InstitutionType() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SchoolMst> getSchoolMsts() {
		return this.schoolMsts;
	}

	public void setSchoolMsts(List<SchoolMst> schoolMsts) {
		this.schoolMsts = schoolMsts;
	}

	public SchoolMst addSchoolMst(SchoolMst schoolMst) {
		getSchoolMsts().add(schoolMst);
		schoolMst.setInstitutionType(this);

		return schoolMst;
	}

	public SchoolMst removeSchoolMst(SchoolMst schoolMst) {
		getSchoolMsts().remove(schoolMst);
		schoolMst.setInstitutionType(null);

		return schoolMst;
	}

}