package com.mathtop.course.domain;

import java.util.Date;



public class CourseTeachingClassHomeworkReply extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1920381666452223791L;

	private String id;
	private String courseTeachingClassHomeworkSubmitBaseinfoId;
	private String teacherId;	
	private String title;
	private String content;
	private Date submitdate;
	private Date modifieddate;
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
	public Date getSubmitdate() {
		return submitdate;
	}
	public void setSubmitdate(Date submitdate) {
		this.submitdate = submitdate;
	}
	public Date getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getCourseTeachingClassHomeworkSubmitBaseinfoId() {
		return courseTeachingClassHomeworkSubmitBaseinfoId;
	}
	public void setCourseTeachingClassHomeworkSubmitBaseinfoId(String courseTeachingClassHomeworkSubmitBaseinfoId) {
		this.courseTeachingClassHomeworkSubmitBaseinfoId = courseTeachingClassHomeworkSubmitBaseinfoId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	
	
}
