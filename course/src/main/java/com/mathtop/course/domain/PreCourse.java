package com.mathtop.course.domain;

public class PreCourse extends BaseDomain{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7814875898330442488L;
	
	private String t_course_id_this;
	private String t_course_this_name;
	private String[] t_course_id_pre;
	private String[] t_course_pre_name;
	
	public String getT_course_id_this() {
		return t_course_id_this;
	}
	public void setT_course_id_this(String t_course_id_this) {
		this.t_course_id_this = t_course_id_this;
	}
	public String getT_course_this_name() {
		return t_course_this_name;
	}
	public void setT_course_this_name(String t_course_this_name) {
		this.t_course_this_name = t_course_this_name;
	}
	public String[] getT_course_id_pre() {
		return t_course_id_pre;
	}
	public void setT_course_id_pre(String[] t_course_id_pre) {
		this.t_course_id_pre = t_course_id_pre;
	}
	public String[] getT_course_pre_name() {
		return t_course_pre_name;
	}
	public void setT_course_pre_name(String[] t_course_pre_name) {
		this.t_course_pre_name = t_course_pre_name;
	}
	
	
	
}
