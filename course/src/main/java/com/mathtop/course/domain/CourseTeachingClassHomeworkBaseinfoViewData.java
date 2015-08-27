package com.mathtop.course.domain;

import java.util.List;





public class CourseTeachingClassHomeworkBaseinfoViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8810004997222915656L;

	private CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo;
	private CourseTeachingClass courseteachingclass;
	private TeacherViewData teacher;
	private CourseTeachingClassHomeworkType homeworkType;
	private List<CourseTeachingClassHomeworkFile> homeworkFileList;
	
	public CourseTeachingClassHomeworkBaseinfo getHomeworkbaseinfo() {
		return homeworkbaseinfo;
	}
	public void setHomeworkbaseinfo(CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo) {
		this.homeworkbaseinfo = homeworkbaseinfo;
	}
	
	public TeacherViewData getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherViewData teacher) {
		this.teacher = teacher;
	}
	
	public CourseTeachingClass getCourseteachingclass() {
		return courseteachingclass;
	}
	public void setCourseteachingclass(CourseTeachingClass courseteachingclass) {
		this.courseteachingclass = courseteachingclass;
	}
	public CourseTeachingClassHomeworkType getHomeworkType() {
		return homeworkType;
	}
	public void setHomeworkType(CourseTeachingClassHomeworkType homeworkType) {
		this.homeworkType = homeworkType;
	}
	public List<CourseTeachingClassHomeworkFile> getHomeworkFileList() {
		return homeworkFileList;
	}
	public void setHomeworkFileList(List<CourseTeachingClassHomeworkFile> homeworkFileList) {
		this.homeworkFileList = homeworkFileList;
	}
	
	
}
