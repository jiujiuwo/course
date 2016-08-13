package com.mathtop.course.domain;

import java.util.Date;

public class CourseTeachingClassReference extends BaseDomain {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8309110997151684180L;
	private String id;
	private String courseTeachingClassId;
	private String teacherId;
	private String courseTeachingClassReferenceTypeId;
	private String title;
	private String content;
	private Date pubdate;
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

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}	

	
	public Date getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getCourseTeachingClassId() {
		return courseTeachingClassId;
	}

	public void setCourseTeachingClassId(String courseTeachingClassId) {
		this.courseTeachingClassId = courseTeachingClassId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getCourseTeachingClassReferenceTypeId() {
		return courseTeachingClassReferenceTypeId;
	}

	public void setCourseTeachingClassReferenceTypeId(String courseTeachingClassReferenceTypeId) {
		this.courseTeachingClassReferenceTypeId = courseTeachingClassReferenceTypeId;
	}
}
