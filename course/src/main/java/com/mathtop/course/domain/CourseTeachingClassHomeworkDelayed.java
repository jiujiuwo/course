package com.mathtop.course.domain;

import java.util.Date;



public class CourseTeachingClassHomeworkDelayed extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7526244480695304977L;

	private String id;
	private String t_course_teaching_class_homework_baseinfo_id;
	private String t_teacher_id;
	private Date pubdate;
	private Date enddate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_course_teaching_class_homework_baseinfo_id() {
		return t_course_teaching_class_homework_baseinfo_id;
	}
	public void setT_course_teaching_class_homework_baseinfo_id(String t_course_teaching_class_homework_baseinfo_id) {
		this.t_course_teaching_class_homework_baseinfo_id = t_course_teaching_class_homework_baseinfo_id;
	}
	public String getT_teacher_id() {
		return t_teacher_id;
	}
	public void setT_teacher_id(String t_teacher_id) {
		this.t_teacher_id = t_teacher_id;
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
	
}
