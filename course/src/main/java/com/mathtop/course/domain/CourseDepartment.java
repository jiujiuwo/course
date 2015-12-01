package com.mathtop.course.domain;

public class CourseDepartment extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8185997798872849390L;
	
	private String id;
	private String t_course_id;	
	private String t_department_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_course_id() {
		return t_course_id;
	}
	public void setT_course_id(String t_course_id) {
		this.t_course_id = t_course_id;
	}
	public String getT_department_id() {
		return t_department_id;
	}
	public void setT_department_id(String t_department_id) {
		this.t_department_id = t_department_id;
	}

	
	
	
	
	
	
	
}
