package com.mathtop.course.domain;





public class CourseTeachingClassHomeworkReplyFileViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6920125742598194937L;

	private CourseTeachingClassHomeworkReplyFile replyfile;
	private CourseTeachingClassHomeworkReply reply;
	public CourseTeachingClassHomeworkReplyFile getReplyfile() {
		return replyfile;
	}
	public void setReplyfile(CourseTeachingClassHomeworkReplyFile replyfile) {
		this.replyfile = replyfile;
	}
	public CourseTeachingClassHomeworkReply getReply() {
		return reply;
	}
	public void setReply(CourseTeachingClassHomeworkReply reply) {
		this.reply = reply;
	}
}
