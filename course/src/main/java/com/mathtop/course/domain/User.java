package com.mathtop.course.domain;

import com.mathtop.course.utility.EncoderHandler;

public class User extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1788742042022533623L;

	private String id;
	private String user_name;
	private String user_password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {	
		this.user_password = user_password;//EncoderHandler.encodeByMD5(user_password);
	}
	
	public void EncoderPassword(){
		user_password =EncoderHandler.encodeByMD5(user_password);
	}
	
}
