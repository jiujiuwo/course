package com.mathtop.course.domain;

import java.sql.Timestamp;

public class Login extends BaseDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4644854851593571111L;
	private String id;
	private String userId;
	private Timestamp loginDatetime;
	private String loginIp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getLoginDatetime() {
		return loginDatetime;
	}

	public void setLoginDatetime(Timestamp loginDatetime) {
		this.loginDatetime = loginDatetime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	

}
