package com.mathtop.course.domain;



public class CourseTeachingClassHomeworkType extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2842734497709823461L;

	private String id;
	private String t_course_teaching_class_id;

	private String name;
	private String note;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_course_teaching_class_id() {
		return t_course_teaching_class_id;
	}
	public void setT_course_teaching_class_id(String t_course_teaching_class_id) {
		this.t_course_teaching_class_id = t_course_teaching_class_id;
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
}
