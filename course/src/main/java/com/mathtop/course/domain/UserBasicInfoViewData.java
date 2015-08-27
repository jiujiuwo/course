package com.mathtop.course.domain;



public class UserBasicInfoViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7808545381308284515L;

	private User user;
	private Teacher teacher;
	private Student student;
	private UserBasicInfo userbasicinfo;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public UserBasicInfo getUserbasicinfo() {
		return userbasicinfo;
	}
	public void setUserbasicinfo(UserBasicInfo userbasicinfo) {
		this.userbasicinfo = userbasicinfo;
	}

}
