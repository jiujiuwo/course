package com.mathtop.course.domain;

public class Course extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4636252963293812787L;
	
	private String id;
	private String name;
	private String englishname;
	private String num;
	private int class_hours;
	private int experiment_hours;
	private String t_course_type_name;
	private String t_course_type_id;
	private String t_course_style_name;
	private String t_course_style_id;
	
	
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
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public int getClass_hours() {
		return class_hours;
	}
	public void setClass_hours(int class_hours) {
		this.class_hours = class_hours;
	}
	public int getExperiment_hours() {
		return experiment_hours;
	}
	public void setExperiment_hours(int experiment_hours) {
		this.experiment_hours = experiment_hours;
	}
	public String getT_course_type_name() {
		return t_course_type_name;
	}
	public void setT_course_type_name(String t_course_type_name) {
		this.t_course_type_name = t_course_type_name;
	}
	public String getT_course_style_name() {
		return t_course_style_name;
	}
	public void setT_course_style_name(String t_course_style_name) {
		this.t_course_style_name = t_course_style_name;
	}
	public String getT_course_type_id() {
		return t_course_type_id;
	}
	public void setT_course_type_id(String t_course_type_id) {
		this.t_course_type_id = t_course_type_id;
	}
	public String getT_course_style_id() {
		return t_course_style_id;
	}
	public void setT_course_style_id(String t_course_style_id) {
		this.t_course_style_id = t_course_style_id;
	}

}
