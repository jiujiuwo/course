package com.mathtop.course.domain;

public class UserContactInfo extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6312683018926603207L;

	private String id;
	private String t_user_id;
	private String t_user_contact_type_id;
	private String user_contact_value;
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
	public String getT_user_contact_type_id() {
		return t_user_contact_type_id;
	}
	public void setT_user_contact_type_id(String t_user_contact_type_id) {
		this.t_user_contact_type_id = t_user_contact_type_id;
	}
	public String getUser_contact_value() {
		return user_contact_value;
	}
	public void setUser_contact_value(String user_contact_value) {
		this.user_contact_value = user_contact_value;
	}
}
