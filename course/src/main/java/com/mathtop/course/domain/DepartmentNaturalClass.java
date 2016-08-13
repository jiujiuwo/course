package com.mathtop.course.domain;

public class DepartmentNaturalClass extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9162111942743408639L;
	
	private String id;
	private String departmentId;
	private String naturalClassId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getNaturalClassId() {
		return naturalClassId;
	}
	public void setNaturalClassId(String naturalClassId) {
		this.naturalClassId = naturalClassId;
	}	


}
