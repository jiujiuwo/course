package com.mathtop.course.domain;

public class Permission extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4289671503720569512L;
	
	private String id;
	private String name;
	private String note;	
	private String t_permission_operator_id;
	private String t_resource_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getT_permission_operator_id() {
		return t_permission_operator_id;
	}
	public void setT_permission_operator_id(String t_permission_operator_id) {
		this.t_permission_operator_id = t_permission_operator_id;
	}
	public String getT_resource_id() {
		return t_resource_id;
	}
	public void setT_resource_id(String t_resource_id) {
		this.t_resource_id = t_resource_id;
	}

}
