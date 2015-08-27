package com.mathtop.course.domain;

public class SchoolDepartment extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571546947876231808L;
	
	private String id;
	private String t_school_id;
	private String t_department_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_school_id() {
		return t_school_id;
	}
	public void setT_school_id(String t_school_id) {
		this.t_school_id = t_school_id;
	}
	public String getT_department_id() {
		return t_department_id;
	}
	public void setT_department_id(String t_department_id) {
		this.t_department_id = t_department_id;
	}
	

	
}
