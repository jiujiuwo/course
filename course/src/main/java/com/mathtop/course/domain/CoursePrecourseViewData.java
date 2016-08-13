package com.mathtop.course.domain;

public class CoursePrecourseViewData extends BaseDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4631407174990331561L;

	private CoursePrecourse courseprecourse;
	private Course courseThis;
	private Course coursePre;

	public CoursePrecourse getCourseprecourse() {
		return courseprecourse;
	}
	public void setCourseprecourse(CoursePrecourse courseprecourse) {
		this.courseprecourse = courseprecourse;
	}
	public Course getCourseThis() {
		return courseThis;
	}
	public void setCourseThis(Course courseThis) {
		this.courseThis = courseThis;
	}
	public Course getCoursePre() {
		return coursePre;
	}
	public void setCoursePre(Course coursePre) {
		this.coursePre = coursePre;
	}
	
	
}
