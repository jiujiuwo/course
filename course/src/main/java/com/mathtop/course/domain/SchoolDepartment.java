package com.mathtop.course.domain;

public class SchoolDepartment extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571546947876231808L;
	
	private String id;
	private String schoolId;
	private String departmentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	

	
}
