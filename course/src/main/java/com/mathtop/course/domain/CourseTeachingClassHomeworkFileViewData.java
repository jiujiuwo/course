package com.mathtop.course.domain;





public class CourseTeachingClassHomeworkFileViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7859813265817075293L;

	private CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo;
	private CourseTeachingClassHomeworkFile homeworkfile;
	
	public CourseTeachingClassHomeworkFile getHomeworkfile() {
		return homeworkfile;
	}
	public void setHomeworkfile(CourseTeachingClassHomeworkFile homeworkfile) {
		this.homeworkfile = homeworkfile;
	}
	public CourseTeachingClassHomeworkBaseinfo getHomeworkbaseinfo() {
		return homeworkbaseinfo;
	}
	public void setHomeworkbaseinfo(CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo) {
		this.homeworkbaseinfo = homeworkbaseinfo;
	}
	
}
