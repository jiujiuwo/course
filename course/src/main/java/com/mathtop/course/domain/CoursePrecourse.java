package com.mathtop.course.domain;

public class CoursePrecourse extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -936912408814159293L;

	private String id;
	private String courseIdThis;
	private String courseIdPre;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseIdThis() {
		return courseIdThis;
	}
	public void setCourseIdThis(String courseIdThis) {
		this.courseIdThis = courseIdThis;
	}
	public String getCourseIdPre() {
		return courseIdPre;
	}
	public void setCourseIdPre(String courseIdPre) {
		this.courseIdPre = courseIdPre;
	}

}
