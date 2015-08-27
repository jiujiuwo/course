package com.mathtop.course.domain;

public class UserInfoViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9146625780255163336L;
	public UserInfoViewData() {
		// TODO Auto-generated constructor stub
	}
	private String t_user_contact_type;
	private String user_contact_value;
	public String getT_user_contact_type() {
		return t_user_contact_type;
	}
	public void setT_user_contact_type(String t_user_contact_type) {
		this.t_user_contact_type = t_user_contact_type;
	}
	public String getUser_contact_value() {
		return user_contact_value;
	}
	public void setUser_contact_value(String user_contact_value) {
		this.user_contact_value = user_contact_value;
	}

}
