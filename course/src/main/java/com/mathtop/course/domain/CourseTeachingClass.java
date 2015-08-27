package com.mathtop.course.domain;

public class CourseTeachingClass extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1166050818025622588L;

	
	private String id;
	private String t_course_id;
	private String t_teaching_class_id;
	private int teaching_year_begin;
	private int teaching_year_end;
	private int teaching_term;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_course_id() {
		return t_course_id;
	}
	public void setT_course_id(String t_course_id) {
		this.t_course_id = t_course_id;
	}
	public String getT_teaching_class_id() {
		return t_teaching_class_id;
	}
	public void setT_teaching_class_id(String t_teaching_class_id) {
		this.t_teaching_class_id = t_teaching_class_id;
	}
	public int getTeaching_year_begin() {
		return teaching_year_begin;
	}
	public void setTeaching_year_begin(int teaching_year_begin) {
		this.teaching_year_begin = teaching_year_begin;
	}
	public int getTeaching_year_end() {
		return teaching_year_end;
	}
	public void setTeaching_year_end(int teaching_year_end) {
		this.teaching_year_end = teaching_year_end;
	}
	public int getTeaching_term() {
		return teaching_term;
	}
	public void setTeaching_term(int teaching_term) {
		this.teaching_term = teaching_term;
	}
	
}
