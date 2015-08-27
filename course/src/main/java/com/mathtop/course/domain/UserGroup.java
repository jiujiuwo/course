package com.mathtop.course.domain;

public class UserGroup extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 280682936799155796L;
	
	private String id;
	private String t_user_id;
	private String t_group_id;
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
	public String getT_group_id() {
		return t_group_id;
	}
	public void setT_group_id(String t_group_id) {
		this.t_group_id = t_group_id;
	}
	

}
