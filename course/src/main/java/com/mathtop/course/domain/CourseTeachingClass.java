package com.mathtop.course.domain;

public class CourseTeachingClass extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1166050818025622588L;

	private String id;
	private String courseId;
	private String name;
	private String courseTeachingTermId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourseTeachingTermId() {
		return courseTeachingTermId;
	}
	public void setCourseTeachingTermId(String courseTeachingTermId) {
		this.courseTeachingTermId = courseTeachingTermId;
	}
}
