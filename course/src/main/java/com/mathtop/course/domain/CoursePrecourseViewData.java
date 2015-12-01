package com.mathtop.course.domain;

public class CoursePrecourseViewData extends BaseDomain{

	public CoursePrecourse getCourseprecourse() {
		return courseprecourse;
	}
	public void setCourseprecourse(CoursePrecourse courseprecourse) {
		this.courseprecourse = courseprecourse;
	}
	public Course getCoursethis() {
		return coursethis;
	}
	public void setCoursethis(Course coursethis) {
		this.coursethis = coursethis;
	}
	public Course getCoursepre() {
		return coursepre;
	}
	public void setCoursepre(Course coursepre) {
		this.coursepre = coursepre;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4631407174990331561L;

	private CoursePrecourse courseprecourse;
	private Course coursethis;
	private Course coursepre;
	
}
