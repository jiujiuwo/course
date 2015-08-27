package com.mathtop.course.domain;

import java.util.Date;

public class UserBasicInfo extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2445506986479187759L;
	
	private String id;
	private String t_user_id;
	private String user_basic_info_name;
	private Date user_basic_info_birthday;
	private int user_basic_info_sex;
	private String user_basic_info_address;
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
	public String getUser_basic_info_name() {
		return user_basic_info_name;
	}
	public void setUser_basic_info_name(String user_basic_info_name) {
		this.user_basic_info_name = user_basic_info_name;
	}
	public Date getUser_basic_info_birthday() {
		return user_basic_info_birthday;
	}
	public void setUser_basic_info_birthday(Date user_basic_info_birthday) {
		this.user_basic_info_birthday = user_basic_info_birthday;
	}
	public int getUser_basic_info_sex() {
		return user_basic_info_sex;
	}
	public void setUser_basic_info_sex(int user_basic_info_sex) {
		this.user_basic_info_sex = user_basic_info_sex;
	}
	public String getUser_basic_info_address() {
		return user_basic_info_address;
	}
	public void setUser_basic_info_address(String user_basic_info_address) {
		this.user_basic_info_address = user_basic_info_address;
	}
	
	

}
