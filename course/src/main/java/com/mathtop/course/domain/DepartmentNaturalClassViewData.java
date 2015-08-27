package com.mathtop.course.domain;


public class DepartmentNaturalClassViewData  extends BaseDomain{

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -1682374614815934739L;

	public DepartmentNaturalClassViewData() {
		// TODO Auto-generated constructor stub
	}
	
	private String id;
	private String t_school_id;
	private String t_school_name;
	private String t_department_id;
	private String t_department_name;
	private String t_natural_class_id;
	private String t_natural_class_name;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_school_id() {
		return t_school_id;
	}
	public void setT_school_id(String t_school_id) {
		this.t_school_id = t_school_id;
	}
	public String getT_school_name() {
		return t_school_name;
	}
	public void setT_school_name(String t_school_name) {
		this.t_school_name = t_school_name;
	}
	public String getT_department_id() {
		return t_department_id;
	}
	public void setT_department_id(String t_department_id) {
		this.t_department_id = t_department_id;
	}
	public String getT_department_name() {
		return t_department_name;
	}
	public void setT_department_name(String t_department_name) {
		this.t_department_name = t_department_name;
	}
	public String getT_natural_class_id() {
		return t_natural_class_id;
	}
	public void setT_natural_class_id(String t_natural_class_id) {
		this.t_natural_class_id = t_natural_class_id;
	}
	public String getT_natural_class_name() {
		return t_natural_class_name;
	}
	public void setT_natural_class_name(String t_natural_class_name) {
		this.t_natural_class_name = t_natural_class_name;
	}
}
