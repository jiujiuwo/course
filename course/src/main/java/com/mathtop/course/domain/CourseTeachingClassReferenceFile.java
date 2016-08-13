package com.mathtop.course.domain;



public class CourseTeachingClassReferenceFile extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 237605326519036820L;

	 private String id;
	 private String courseTeachingClassReferenceId;
	 private String filename;
	 private String filepath;
	 private String filelength;
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
	
	public String getFilelength() {
		return filelength;
	}
	public void setFilelength(String filelength) {
		this.filelength = filelength;
	}
	public String getCourseTeachingClassReferenceId() {
		return courseTeachingClassReferenceId;
	}
	public void setCourseTeachingClassReferenceId(String courseTeachingClassReferenceId) {
		this.courseTeachingClassReferenceId = courseTeachingClassReferenceId;
	}   
	
}
