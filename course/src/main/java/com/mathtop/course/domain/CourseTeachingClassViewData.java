package com.mathtop.course.domain;

public class CourseTeachingClassViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -185523270419711193L;

	//t_teaching_class:教学班
	TeachingClass teachingclass;
	

	CourseTeachingClass courseteachingclass;


	//课程
	Course course;	
	
	//授课教师
	TeacherViewData[] teacher;
	
	
	//教师授课类型
	TeachingType[] teachingtype;
	
	
	
	
	public TeachingClass getTeachingclass() {
		return teachingclass;
	}


	public void setTeachingclass(TeachingClass teachingclass) {
		this.teachingclass = teachingclass;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public TeacherViewData[] getTeacher() {
		return teacher;
	}


	public void setTeacher(TeacherViewData[] teacher) {
		this.teacher = teacher;
	}


	public TeachingType[] getTeachingtype() {
		return teachingtype;
	}


	public void setTeachingtype(TeachingType[] teachingtype) {
		this.teachingtype = teachingtype;
	}


	public CourseTeachingClass getCourseteachingclass() {
		return courseteachingclass;
	}


	public void setCourseteachingclass(CourseTeachingClass courseteachingclass) {
		this.courseteachingclass = courseteachingclass;
	}
}
