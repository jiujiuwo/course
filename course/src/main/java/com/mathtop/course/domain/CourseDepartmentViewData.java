package com.mathtop.course.domain;

public class CourseDepartmentViewData extends BaseDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2256798001711936909L;
	private CourseDepartment courseDepartment;
	private Course course;
	private Department department;
	

	public CourseDepartment getCourseDepartment() {
		return courseDepartment;
	}
	public void setCourseDepartment(CourseDepartment courseDepartment) {
		this.courseDepartment = courseDepartment;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
