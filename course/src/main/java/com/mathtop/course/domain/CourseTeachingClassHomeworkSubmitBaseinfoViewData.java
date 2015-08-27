package com.mathtop.course.domain;

import java.util.List;





public class CourseTeachingClassHomeworkSubmitBaseinfoViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6811526273844004022L;

	private CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo;
	private StudentViewData student;
	private CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo;
	private List<CourseTeachingClassHomeworkSubmitFile> homeworksubmitFileList;
	private boolean hasReply;//是否已经回复
	
	public CourseTeachingClassHomeworkSubmitBaseinfo getHomeworksubmitbaseinfo() {
		return homeworksubmitbaseinfo;
	}
	public void setHomeworksubmitbaseinfo(CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo) {
		this.homeworksubmitbaseinfo = homeworksubmitbaseinfo;
	}
	public StudentViewData getStudent() {
		return student;
	}
	public void setStudent(StudentViewData student) {
		this.student = student;
	}
	public CourseTeachingClassHomeworkBaseinfo getHomeworkbaseinfo() {
		return homeworkbaseinfo;
	}
	public void setHomeworkbaseinfo(CourseTeachingClassHomeworkBaseinfo homeworkbaseinfo) {
		this.homeworkbaseinfo = homeworkbaseinfo;
	}
	public List<CourseTeachingClassHomeworkSubmitFile> getHomeworksubmitFileList() {
		return homeworksubmitFileList;
	}
	public void setHomeworksubmitFileList(List<CourseTeachingClassHomeworkSubmitFile> homeworksubmitFileList) {
		this.homeworksubmitFileList = homeworksubmitFileList;
	}
	public boolean isHasReply() {
		return hasReply;
	}
	public void setHasReply(boolean hasReply) {
		this.hasReply = hasReply;
	}
	
}
