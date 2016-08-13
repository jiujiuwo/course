package com.mathtop.course.domain;

import java.util.Date;



public class CourseTeachingClassForumReply extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8765711094745814372L;

	private String id;
	private String courseTeachingClassForumTopicId;
	private String userId;
	private String title;
	private String content;
	private Date createdDate;
	private Date lastModifiedDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCourseTeachingClassForumTopicId() {
		return courseTeachingClassForumTopicId;
	}
	public void setCourseTeachingClassForumTopicId(String courseTeachingClassForumTopicId) {
		this.courseTeachingClassForumTopicId = courseTeachingClassForumTopicId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
