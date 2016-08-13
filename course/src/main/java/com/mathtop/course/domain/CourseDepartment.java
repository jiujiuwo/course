package com.mathtop.course.domain;

public class CourseDepartment extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8185997798872849390L;
	
	private String id;
	private String courseId;	
	private String departmentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	
	
	
	
	
	
	
}
