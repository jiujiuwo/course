package com.mathtop.course.domain;

public class CourseTeachingClassViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -185523270419711193L;


	

	CourseTeachingClass courseTeachingClass;


	//课程
	Course course;	
	CourseTeachingTerm term;
	
	//授课教师
	TeacherViewData[] teacher;
	
	
	//教师授课类型
	TeachingType[] teachingtype;
	
	
	

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


	


	public CourseTeachingTerm getTerm() {
		return term;
	}


	public void setTerm(CourseTeachingTerm term) {
		this.term = term;
	}


	public CourseTeachingClass getCourseTeachingClass() {
		return courseTeachingClass;
	}


	public void setCourseTeachingClass(CourseTeachingClass courseTeachingClass) {
		this.courseTeachingClass = courseTeachingClass;
	}
}
