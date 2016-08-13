package com.mathtop.course.domain;



public class CourseTeachingClassForumTopicFile extends BaseDomain {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3525442298067390215L;
	private String id;
	private String courseTeachingClassForumTopicId;
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
	public String getCourseTeachingClassForumTopicId() {
		return courseTeachingClassForumTopicId;
	}
	public void setCourseTeachingClassForumTopicId(String courseTeachingClassForumTopicId) {
		this.courseTeachingClassForumTopicId = courseTeachingClassForumTopicId;
	}

	
}
