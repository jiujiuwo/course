package com.mathtop.course.domain;

public class CourseTeachingClassStudent extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2085499659000769005L;
	
	private String id;
	private String t_teaching_class_id;
	private String t_student_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getT_student_id() {
		return t_student_id;
	}
	public void setT_student_id(String t_student_id) {
		this.t_student_id = t_student_id;
	}
	public String getT_teaching_class_id() {
		return t_teaching_class_id;
	}
	public void setT_teaching_class_id(String t_teaching_class_id) {
		this.t_teaching_class_id = t_teaching_class_id;
	}
	

}
