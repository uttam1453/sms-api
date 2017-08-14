package com.myschool.sms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the USER_SESSION_DETAILS database table.
 * 
 */
@Entity
@Table(name="USER_SESSION_DETAILS")
@NamedQuery(name="UserSessionDetail.findAll", query="SELECT u FROM UserSessionDetail u")
public class UserSessionDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String deviceModel;
	private String deviceToken;
	private String deviceType;
	private byte isLoggedIn;
	private Timestamp loggedInTime;
	private Timestamp loggedOutTime;
	private String sessionId;
	private UserEntity userEntity;

	public UserSessionDetail() {
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


	@Column(name="device_model", length=20)
	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}


	@Column(name="device_token", length=45)
	public String getDeviceToken() {
		return this.deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}


	@Column(name="device_type", length=25)
	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}


	@Column(name="is_logged_in")
	public byte getIsLoggedIn() {
		return this.isLoggedIn;
	}

	public void setIsLoggedIn(byte isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}


	@Column(name="logged_in_time")
	public Timestamp getLoggedInTime() {
		return this.loggedInTime;
	}

	public void setLoggedInTime(Timestamp loggedInTime) {
		this.loggedInTime = loggedInTime;
	}


	@Column(name="logged_out_time")
	public Timestamp getLoggedOutTime() {
		return this.loggedOutTime;
	}

	public void setLoggedOutTime(Timestamp loggedOutTime) {
		this.loggedOutTime = loggedOutTime;
	}


	@Column(name="session_id", length=100)
	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	//bi-directional many-to-one association to UserMst
	@ManyToOne
	@JoinColumn(name="user_id")
	public UserEntity getUserMst() {
		return this.userEntity;
	}

	public void setUserMst(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}