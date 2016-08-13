package com.mathtop.course.domain;

import java.util.Date;



public class CourseTeachingClassHomeworkDelayed extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7526244480695304977L;

	private String id;
	private String courseTeachingClassHomeworkBaseinfoId;
	private String teacherId;
	private Date pubdate;
	private Date enddate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}  
	
	/**
	 * 学生是否允许提交作业
	 * */
	public boolean isCanStudentSubmit() {
		Date now = new Date();

		if (now.getTime() > pubdate.getTime() && now.getTime() < enddate.getTime())
			return true;

		return false;
	}
	public String getCourseTeachingClassHomeworkBaseinfoId() {
		return courseTeachingClassHomeworkBaseinfoId;
	}
	public void setCourseTeachingClassHomeworkBaseinfoId(String courseTeachingClassHomeworkBaseinfoId) {
		this.courseTeachingClassHomeworkBaseinfoId = courseTeachingClassHomeworkBaseinfoId;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
}
