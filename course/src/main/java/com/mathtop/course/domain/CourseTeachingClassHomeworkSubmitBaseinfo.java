package com.mathtop.course.domain;

import java.util.Date;



public class CourseTeachingClassHomeworkSubmitBaseinfo extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6387517426181912258L;

	private String id;
	private String courseTeachingClassHomeworkBaseinfoId;
	private String studentId;
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
	public String getCourseTeachingClassHomeworkBaseinfoId() {
		return courseTeachingClassHomeworkBaseinfoId;
	}
	public void setCourseTeachingClassHomeworkBaseinfoId(String courseTeachingClassHomeworkBaseinfoId) {
		this.courseTeachingClassHomeworkBaseinfoId = courseTeachingClassHomeworkBaseinfoId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	
}
