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
	private String courseTypeId;	
	private String courseStyleId;
	
	
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
	public String getCourseTypeId() {
		return courseTypeId;
	}
	public void setCourseTypeId(String courseTypeId) {
		this.courseTypeId = courseTypeId;
	}
	public String getCourseStyleId() {
		return courseStyleId;
	}
	public void setCourseStyleId(String courseStyleId) {
		this.courseStyleId = courseStyleId;
	}
	
	

}
