package com.mathtop.course.domain;

import java.util.Date;

public class UserBasicInfo extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2445506986479187759L;
	
	private String id;
	private String userId;
	private String userBasicInfoName;
	private Date userBasicInfoBirthday;
	private int userBasicInfoSex;
	private String userBasicInfoAddress;
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
	public String getUserBasicInfoName() {
		return userBasicInfoName;
	}
	public void setUserBasicInfoName(String userBasicInfoName) {
		this.userBasicInfoName = userBasicInfoName;
	}
	public Date getUserBasicInfoBirthday() {
		return userBasicInfoBirthday;
	}
	public void setUserBasicInfoBirthday(Date userBasicInfoBirthday) {
		this.userBasicInfoBirthday = userBasicInfoBirthday;
	}
	public int getUserBasicInfoSex() {
		return userBasicInfoSex;
	}
	public void setUserBasicInfoSex(int userBasicInfoSex) {
		this.userBasicInfoSex = userBasicInfoSex;
	}
	public String getUserBasicInfoAddress() {
		return userBasicInfoAddress;
	}
	public void setUserBasicInfoAddress(String userBasicInfoAddress) {
		this.userBasicInfoAddress = userBasicInfoAddress;
	}

	
	

}
