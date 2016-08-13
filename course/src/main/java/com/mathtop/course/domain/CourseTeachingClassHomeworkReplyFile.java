package com.mathtop.course.domain;



public class CourseTeachingClassHomeworkReplyFile extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3233967426720718668L;

	private String id;
	private String courseTeachingClassHomeworkReplyId;
	  private String filename;
	  private String filepath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getCourseTeachingClassHomeworkReplyId() {
		return courseTeachingClassHomeworkReplyId;
	}
	public void setCourseTeachingClassHomeworkReplyId(String courseTeachingClassHomeworkReplyId) {
		this.courseTeachingClassHomeworkReplyId = courseTeachingClassHomeworkReplyId;
	}   
	
}
