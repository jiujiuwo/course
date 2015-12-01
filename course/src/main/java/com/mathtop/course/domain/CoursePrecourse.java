package com.mathtop.course.domain;

public class CoursePrecourse extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -936912408814159293L;

	private String id;
	private String t_course_id_this;
	private String t_course_id_pre;
	public String getT_course_id_pre() {
		return t_course_id_pre;
	}
	public void setT_course_id_pre(String t_course_id_pre) {
		this.t_course_id_pre = t_course_id_pre;
	}
	public String getT_course_id_this() {
		return t_course_id_this;
	}
	public void setT_course_id_this(String t_course_id_this) {
		this.t_course_id_this = t_course_id_this;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
