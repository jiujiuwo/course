package com.mathtop.course.domain;

public class Student extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7473705999215809531L;

	private String id;
	private String t_user_id;
	private String student_num;
	private String natural_class_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_user_id() {
		return t_user_id;
	}
	public void setT_user_id(String t_user_id) {
		this.t_user_id = t_user_id;
	}
	public String getStudent_num() {
		return student_num;
	}
	public void setStudent_num(String student_num) {
		this.student_num = student_num;
	}
	public String getNatural_class_id() {
		return natural_class_id;
	}
	public void setNatural_class_id(String natural_class_id) {
		this.natural_class_id = natural_class_id;
	}
	
	
}
