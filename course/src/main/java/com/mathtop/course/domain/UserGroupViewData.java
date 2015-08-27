package com.mathtop.course.domain;

public class UserGroupViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3158427779328095164L;

	private String id;
	
	Teacher teacher;
	Student student;
	UserBasicInfo userbasicinfo;
	School school;
	Department department;
	
	public UserBasicInfo getUserbasicinfo() {
		return userbasicinfo;
	}
	public void setUserbasicinfo(UserBasicInfo userbasicinfo) {
		this.userbasicinfo = userbasicinfo;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
