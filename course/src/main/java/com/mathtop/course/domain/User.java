package com.mathtop.course.domain;

import com.mathtop.course.utility.EncoderHandler;

public class User extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1788742042022533623L;

	private String id;
	private String userName;
	private String userPassword;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	
	public void EncoderPassword(){
		userPassword =EncoderHandler.encode(userPassword);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
