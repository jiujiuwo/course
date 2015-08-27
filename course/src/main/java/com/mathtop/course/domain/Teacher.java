package com.mathtop.course.domain;

public class Teacher extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1115549477923166608L;
	
	private String id;
	private String t_user_id;
	private String teacher_num;
	
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
	public String getTeacher_num() {
		return teacher_num;
	}
	public void setTeacher_num(String teacher_num) {
		this.teacher_num = teacher_num;
	}
	

}
