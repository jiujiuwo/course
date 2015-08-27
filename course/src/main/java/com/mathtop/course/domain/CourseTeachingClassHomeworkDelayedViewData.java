package com.mathtop.course.domain;





public class CourseTeachingClassHomeworkDelayedViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7866175825819084821L;

	private CourseTeachingClassHomeworkDelayed homeworkdelayed;
	private TeacherViewData teacher;
	private CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo;
	public CourseTeachingClassHomeworkDelayed getHomeworkdelayed() {
		return homeworkdelayed;
	}
	public void setHomeworkdelayed(CourseTeachingClassHomeworkDelayed homeworkdelayed) {
		this.homeworkdelayed = homeworkdelayed;
	}
	public TeacherViewData getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherViewData teacher) {
		this.teacher = teacher;
	}
	public CourseTeachingClassHomeworkBaseinfo getHomeworkbaseinfo() {
		return homeworkbaseinfo;
	}
	public void setHomeworkbaseinfo(CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo) {
		this.homeworkbaseinfo = homeworkbaseinfo;
	}
	
}
