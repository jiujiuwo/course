package com.mathtop.course.domain;

import java.util.List;





public class CourseTeachingClassHomeworkReplyViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2992738039274857269L;

	private CourseTeachingClassHomeworkReply reply;
	private CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo;
	private List<CourseTeachingClassHomeworkReplyFile> repplyFileList;
	public CourseTeachingClassHomeworkReply getReply() {
		return reply;
	}
	public void setReply(CourseTeachingClassHomeworkReply reply) {
		this.reply = reply;
	}
	
	public List<CourseTeachingClassHomeworkReplyFile> getRepplyFileList() {
		return repplyFileList;
	}
	public void setRepplyFileList(List<CourseTeachingClassHomeworkReplyFile> repplyFileList) {
		this.repplyFileList = repplyFileList;
	}
	public CourseTeachingClassHomeworkSubmitBaseinfo getHomeworksubmitbaseinfo() {
		return homeworksubmitbaseinfo;
	}
	public void setHomeworksubmitbaseinfo(CourseTeachingClassHomeworkSubmitBaseinfo homeworksubmitbaseinfo) {
		this.homeworksubmitbaseinfo = homeworksubmitbaseinfo;
	}
	
	
}
