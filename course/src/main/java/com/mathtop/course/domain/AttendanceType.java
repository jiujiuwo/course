package com.mathtop.course.domain;

public class AttendanceType extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2391851673324749706L;

	
	private String id;
	private String courseTeachingClassId;

	private String name;
	private String note;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCourseTeachingClassId() {
		return courseTeachingClassId;
	}
	public void setCourseTeachingClassId(String courseTeachingClassId) {
		this.courseTeachingClassId = courseTeachingClassId;
	}  

	

}
