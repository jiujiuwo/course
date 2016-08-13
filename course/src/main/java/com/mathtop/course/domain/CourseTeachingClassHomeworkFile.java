package com.mathtop.course.domain;



public class CourseTeachingClassHomeworkFile extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 237605326519036820L;

	 private String id;
	 private String courseTeachingClassHomeworkBaseinfoId;
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
	public String getCourseTeachingClassHomeworkBaseinfoId() {
		return courseTeachingClassHomeworkBaseinfoId;
	}
	public void setCourseTeachingClassHomeworkBaseinfoId(String courseTeachingClassHomeworkBaseinfoId) {
		this.courseTeachingClassHomeworkBaseinfoId = courseTeachingClassHomeworkBaseinfoId;
	}   
	
}
