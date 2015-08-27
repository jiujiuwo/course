package com.mathtop.course.domain;

public class GroupRole extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1599145760397519476L;
	
	
	private String id;
	private String t_group_id;
	private String t_role_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_group_id() {
		return t_group_id;
	}
	public void setT_group_id(String t_group_id) {
		this.t_group_id = t_group_id;
	}
	public String getT_role_id() {
		return t_role_id;
	}
	public void setT_role_id(String t_role_id) {
		this.t_role_id = t_role_id;
	}
	


}
