package com.mathtop.course.domain;





public class CourseTeachingClassForumReplyFileViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3750881266333661857L;

	private CourseTeachingClassForumReplyFile replyFile;
	private CourseTeachingClassForumReply reply;
	public CourseTeachingClassForumReplyFile getReplyFile() {
		return replyFile;
	}
	public void setReplyFile(CourseTeachingClassForumReplyFile replyFile) {
		this.replyFile = replyFile;
	}
	public CourseTeachingClassForumReply getReply() {
		return reply;
	}
	public void setReply(CourseTeachingClassForumReply reply) {
		this.reply = reply;
	}
	
}
