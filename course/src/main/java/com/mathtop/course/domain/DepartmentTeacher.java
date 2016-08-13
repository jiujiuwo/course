package com.mathtop.course.domain;

public class DepartmentTeacher extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6114989438776640913L;

	private String id;
	private String departmentId;
	private String teacherId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

}
