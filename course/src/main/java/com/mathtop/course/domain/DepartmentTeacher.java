package com.mathtop.course.domain;

public class DepartmentTeacher extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6114989438776640913L;

	private String id;
	private String t_department_id;
	private String t_teacher_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_department_id() {
		return t_department_id;
	}
	public void setT_department_id(String t_department_id) {
		this.t_department_id = t_department_id;
	}
	public String getT_teacher_id() {
		return t_teacher_id;
	}
	public void setT_teacher_id(String t_teacher_id) {
		this.t_teacher_id = t_teacher_id;
	}
}
