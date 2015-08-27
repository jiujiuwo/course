package com.mathtop.course.domain;

import java.util.List;

public class TeacherViewData  extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1181145268186320382L;

	private User user;
	private Teacher teacher;
	private School school;
	private Department department;
	private UserBasicInfo userbasicinfo;
	private List<UserContactInfoViewData> usercontactinfoviewdata;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public UserBasicInfo getUserbasicinfo() {
		return userbasicinfo;
	}
	public void setUserbasicinfo(UserBasicInfo userbasicinfo) {
		this.userbasicinfo = userbasicinfo;
	}
	public List<UserContactInfoViewData> getUsercontactinfoviewdata() {
		return usercontactinfoviewdata;
	}
	public void setUsercontactinfoviewdata(List<UserContactInfoViewData> usercontactinfoviewdata) {
		this.usercontactinfoviewdata = usercontactinfoviewdata;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	

}
