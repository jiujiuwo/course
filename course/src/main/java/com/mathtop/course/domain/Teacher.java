package com.mathtop.course.domain;

public class Teacher extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1115549477923166608L;
	
	private String id;
	private String userId;
	private String teacherNum;
	
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
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	

}
