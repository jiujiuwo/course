package com.mathtop.course.domain;

public class CourseTeachingClassTeacher extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 933880754638337678L;

	private String id;
	private String t_course_teaching_class_id;
	private String t_teacher_id;
	private String t_teaching_type_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getT_teacher_id() {
		return t_teacher_id;
	}
	public void setT_teacher_id(String t_teacher_id) {
		this.t_teacher_id = t_teacher_id;
	}
	public String getT_teaching_type_id() {
		return t_teaching_type_id;
	}
	public void setT_teaching_type_id(String t_teaching_type_id) {
		this.t_teaching_type_id = t_teaching_type_id;
	}
	public String getT_course_teaching_class_id() {
		return t_course_teaching_class_id;
	}
	public void setT_course_teaching_class_id(String t_course_teaching_class_id) {
		this.t_course_teaching_class_id = t_course_teaching_class_id;
	}

}
