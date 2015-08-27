package com.mathtop.course.domain;





public class CourseTeachingClassForumTopicFileViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4684292075176267018L;

	private CourseTeachingClassForumTopic topic;
	private CourseTeachingClassForumTopicFile topicFile;
	public CourseTeachingClassForumTopic getTopic() {
		return topic;
	}
	public void setTopic(CourseTeachingClassForumTopic topic) {
		this.topic = topic;
	}
	public CourseTeachingClassForumTopicFile getTopicFile() {
		return topicFile;
	}
	public void setTopicFile(CourseTeachingClassForumTopicFile topicFile) {
		this.topicFile = topicFile;
	}
	
}
