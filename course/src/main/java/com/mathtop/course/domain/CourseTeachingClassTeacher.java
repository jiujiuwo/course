package com.mathtop.course.domain;

public class CourseTeachingClassTeacher extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 933880754638337678L;

	private String id;
	private String courseTeachingClassId;
	private String teacherId;
	private String teachingTypeId;
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
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeachingTypeId() {
		return teachingTypeId;
	}
	public void setTeachingTypeId(String teachingTypeId) {
		this.teachingTypeId = teachingTypeId;
	}
	
	
}
