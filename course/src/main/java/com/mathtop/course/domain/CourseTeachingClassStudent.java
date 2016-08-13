package com.mathtop.course.domain;

public class CourseTeachingClassStudent extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2085499659000769005L;
	
	private String id;
	private String courseTeachingClassId;
	private String studentId;
	private int showIndex;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseTeachingClassId() {
		return courseTeachingClassId;
	}
	public void setCourseTeachingClassId(String courseTeachingClassId) {
		this.courseTeachingClassId = courseTeachingClassId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getShowIndex() {
		return showIndex;
	}
	public void setShowIndex(int showIndex) {
		this.showIndex = showIndex;
	}
	

}
