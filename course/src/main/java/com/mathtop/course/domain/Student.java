package com.mathtop.course.domain;

public class Student extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7473705999215809531L;

	private String id;
	private String userId;
	private String studentNum;
	private String naturalClassId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	public String getNaturalClassId() {
		return naturalClassId;
	}
	public void setNaturalClassId(String naturalClassId) {
		this.naturalClassId = naturalClassId;
	}

	
	
}
