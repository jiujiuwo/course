package com.mathtop.course.domain;

import java.sql.Timestamp;

public class Login extends BaseDomain{
	
	private String id;
	private String t_user_id;
	private Timestamp login_datetime;
	private String login_ip;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getT_user_id() {
		return t_user_id;
	}

	public void setT_user_id(String t_user_id) {
		this.t_user_id = t_user_id;
	}

	public Timestamp getLogin_datetime() {
		return login_datetime;
	}

	public void setLogin_datetime(Timestamp login_datetime) {
		this.login_datetime = login_datetime;
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 970070442605467720L;
	
	

}
