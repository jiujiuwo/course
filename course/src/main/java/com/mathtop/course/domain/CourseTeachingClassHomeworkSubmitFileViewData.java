package com.mathtop.course.domain;





public class CourseTeachingClassHomeworkSubmitFileViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3330336629812884511L;

	private CourseTeachingClassHomeworkSubmitFile homeworksubmitfile;
	private CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo;
	public CourseTeachingClassHomeworkSubmitFile getHomeworksubmitfile() {
		return homeworksubmitfile;
	}
	public void setHomeworksubmitfile(CourseTeachingClassHomeworkSubmitFile homeworksubmitfile) {
		this.homeworksubmitfile = homeworksubmitfile;
	}
	public CourseTeachingClassHomeworkSubmitBaseinfo getHomeworksubmitbaseinfo() {
		return homeworksubmitbaseinfo;
	}
	public void setHomeworksubmitbaseinfo(CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo) {
		this.homeworksubmitbaseinfo = homeworksubmitbaseinfo;
	}
	
}
