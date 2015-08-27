package com.mathtop.course.domain;

public class RolePermission extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6029515573021726701L;
	private String id;
	private String t_role_id;
	private String t_permission_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_role_id() {
		return t_role_id;
	}
	public void setT_role_id(String t_role_id) {
		this.t_role_id = t_role_id;
	}
	public String getT_permission_id() {
		return t_permission_id;
	}
	public void setT_permission_id(String t_permission_id) {
		this.t_permission_id = t_permission_id;
	}

}
