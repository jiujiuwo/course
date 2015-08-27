package com.mathtop.course.domain;

public class DepartmentNaturalClass extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9162111942743408639L;
	
	private String id;
	private String t_department_id;
	private String t_natural_class_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	public String getT_department_id() {
		return t_department_id;
	}
	public void setT_department_id(String t_department_id) {
		this.t_department_id = t_department_id;
	}
	public String getT_natural_class_id() {
		return t_natural_class_id;
	}
	public void setT_natural_class_id(String t_natural_class_id) {
		this.t_natural_class_id = t_natural_class_id;
	}

}
