package com.mathtop.course.domain;

import java.util.List;




public class CourseTeachingClassForumTopicViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8680522714010359877L;
	
	private CourseTeachingClassForumTopic forumtopic;
	private UserBasicInfoViewData userbasicinfoviewdata;
	private List<CourseTeachingClassForumTopicFile> topicFileList;
	
	public CourseTeachingClassForumTopic getForumtopic() {
		return forumtopic;
	}
	public void setForumtopic(CourseTeachingClassForumTopic forumtopic) {
		this.forumtopic = forumtopic;
	}
	public UserBasicInfoViewData getUserbasicinfoviewdata() {
		return userbasicinfoviewdata;
	}
	public void setUserbasicinfoviewdata(UserBasicInfoViewData userbasicinfoviewdata) {
		this.userbasicinfoviewdata = userbasicinfoviewdata;
	}
	public List<CourseTeachingClassForumTopicFile> getTopicFileList() {
		return topicFileList;
	}
	public void setTopicFileList(List<CourseTeachingClassForumTopicFile> topicFileList) {
		this.topicFileList = topicFileList;
	}
}
