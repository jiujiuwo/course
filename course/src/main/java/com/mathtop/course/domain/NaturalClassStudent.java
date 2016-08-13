package com.mathtop.course.domain;

public class NaturalClassStudent extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 453330490334273319L;
	
	private String id;
	private String naturalClassId;
	private String studentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNaturalClassId() {
		return naturalClassId;
	}
	public void setNaturalClassId(String naturalClassId) {
		this.naturalClassId = naturalClassId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}


}
