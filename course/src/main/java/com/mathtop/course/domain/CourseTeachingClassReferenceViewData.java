package com.mathtop.course.domain;

import java.util.List;





public class CourseTeachingClassReferenceViewData extends BaseDomain {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4397038431887260876L;
	
	private CourseTeachingClassReference reference;
	private CourseTeachingClass courseteachingclass;
	private TeacherViewData teacher;
	private CourseTeachingClassReferenceType type;
	private List<CourseTeachingClassReferenceFile> fileList;
	
	
	
	public TeacherViewData getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherViewData teacher) {
		this.teacher = teacher;
	}
	
	public CourseTeachingClass getCourseteachingclass() {
		return courseteachingclass;
	}
	public void setCourseteachingclass(CourseTeachingClass courseteachingclass) {
		this.courseteachingclass = courseteachingclass;
	}
	public CourseTeachingClassReferenceType getType() {
		return type;
	}
	public void setType(CourseTeachingClassReferenceType type) {
		this.type = type;
	}
	public List<CourseTeachingClassReferenceFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<CourseTeachingClassReferenceFile> fileList) {
		this.fileList = fileList;
	}
	public CourseTeachingClassReference getReference() {
		return reference;
	}
	public void setReference(CourseTeachingClassReference reference) {
		this.reference = reference;
	}
	
	
	
}
