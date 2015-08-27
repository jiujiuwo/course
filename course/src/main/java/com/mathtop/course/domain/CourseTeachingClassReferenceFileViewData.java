package com.mathtop.course.domain;





public class CourseTeachingClassReferenceFileViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7859813265817075293L;

	private CourseTeachingClassReference reference;
	private CourseTeachingClassReferenceFile file;
	public CourseTeachingClassReference getReference() {
		return reference;
	}
	public void setReference(CourseTeachingClassReference reference) {
		this.reference = reference;
	}
	public CourseTeachingClassReferenceFile getFile() {
		return file;
	}
	public void setFile(CourseTeachingClassReferenceFile file) {
		this.file = file;
	}
	
	
	
}
