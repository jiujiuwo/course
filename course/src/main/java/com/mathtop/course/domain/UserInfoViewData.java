package com.mathtop.course.domain;

public class UserInfoViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9146625780255163336L;
	public UserInfoViewData() {
		// TODO Auto-generated constructor stub
	}
	private String userContactType;
	private String userContactValue;
	public String getUserContactType() {
		return userContactType;
	}
	public void setUserContactType(String userContactType) {
		this.userContactType = userContactType;
	}
	public String getUserContactValue() {
		return userContactValue;
	}
	public void setUserContactValue(String userContactValue) {
		this.userContactValue = userContactValue;
	}


}
