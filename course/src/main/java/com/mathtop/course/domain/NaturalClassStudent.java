package com.mathtop.course.domain;

public class NaturalClassStudent extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 453330490334273319L;
	
	private String id;
	private String t_natural_clas_id;
	private String t_student_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_natural_clas_id() {
		return t_natural_clas_id;
	}
	public void setT_natural_clas_id(String t_natural_clas_id) {
		this.t_natural_clas_id = t_natural_clas_id;
	}
	public String getT_student_id() {
		return t_student_id;
	}
	public void setT_student_id(String t_student_id) {
		this.t_student_id = t_student_id;
	}

}
