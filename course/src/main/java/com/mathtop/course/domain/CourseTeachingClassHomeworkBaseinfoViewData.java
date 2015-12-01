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
	private List<CourseTeachingClassHomeworkDelayed> homeworkDelayedList;
	
	
	
	/**
	 * 学生是否允许提交作业,分两种情况，一种是正常作业，另一种是布置的迟交作业
	 * */
	public boolean isCanStudentSubmit() {
		if(homeworkbaseinfo==null)
			return false;
		
		if(homeworkbaseinfo.isCanStudentSubmit())
			return true;
		
		if(homeworkDelayedList==null)
			return false;
		
		for(CourseTeachingClassHomeworkDelayed c:homeworkDelayedList){
			if(c.isCanStudentSubmit())
				return true;
		}
		return false;
	}
	
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
	public List<CourseTeachingClassHomeworkDelayed> getHomeworkDelayedList() {
		return homeworkDelayedList;
	}
	public void setHomeworkDelayedList(List<CourseTeachingClassHomeworkDelayed> homeworkDelayedList) {
		this.homeworkDelayedList = homeworkDelayedList;
	}

	
	
	
}
