package com.mathtop.course.domain;

import java.util.List;

public class UserSessionInfo extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82015374000057602L;

	private User user;
	private int pageSize;
	private Teacher teacher;
	private Student student;
	private UserBasicInfo userbasicinfo;
	private List<PermissionViewData> permissionviewdata;
	private List<CourseTeachingClassViewData> teachingclassviewdata;

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

	public List<PermissionViewData> getPermissionviewdata() {
		return permissionviewdata;
	}

	public void setPermissionviewdata(List<PermissionViewData> permissionviewdata) {
		this.permissionviewdata = permissionviewdata;
	}

	public List<CourseTeachingClassViewData> getTeachingclassviewdata() {
		return teachingclassviewdata;
	}

	public void setTeachingclassviewdata(List<CourseTeachingClassViewData> teachingclassviewdata) {
		this.teachingclassviewdata = teachingclassviewdata;
	}

	public Integer getTeachingclassviewdataSize() {
		if (teachingclassviewdata != null)
			return teachingclassviewdata.size();
		return 0;
	}

	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
