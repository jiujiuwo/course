package com.mathtop.course.domain;

import java.util.List;





public class CourseTeachingClassForumReplyViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8133233970921643278L;
	
	
	private CourseTeachingClassForumReply forumreply;
	private UserBasicInfoViewData userbasicinfoviewdata;
	private List<CourseTeachingClassForumReplyFile> replyFileList;
	
	public CourseTeachingClassForumReply getForumreply() {
		return forumreply;
	}
	public void setForumreply(CourseTeachingClassForumReply forumreply) {
		this.forumreply = forumreply;
	}
	public UserBasicInfoViewData getUserbasicinfoviewdata() {
		return userbasicinfoviewdata;
	}
	public void setUserbasicinfoviewdata(UserBasicInfoViewData userbasicinfoviewdata) {
		this.userbasicinfoviewdata = userbasicinfoviewdata;
	}
	public List<CourseTeachingClassForumReplyFile> getReplyFileList() {
		return replyFileList;
	}
	public void setReplyFileList(List<CourseTeachingClassForumReplyFile> replyFileList) {
		this.replyFileList = replyFileList;
	}
}
