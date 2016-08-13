package com.mathtop.course.domain;

public class UserContactInfo extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6312683018926603207L;

	private String id;
	private String userId;
	private String userContactTypeId;
	private String userContactValue;
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
	public String getUserContactTypeId() {
		return userContactTypeId;
	}
	public void setUserContactTypeId(String userContactTypeId) {
		this.userContactTypeId = userContactTypeId;
	}
	public String getUserContactValue() {
		return userContactValue;
	}
	public void setUserContactValue(String userContactValue) {
		this.userContactValue = userContactValue;
	}

}
