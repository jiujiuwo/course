package com.mathtop.course.domain;

import java.util.Date;



public class CourseTeachingClassHomeworkReply extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1920381666452223791L;

	private String id;
	private String t_course_teaching_class_homework_submit_baseinfo_id;
	private String t_teacher_id;
	
	private String title;
	private String content;
	private Date submitdate;
	private Date modifieddate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getT_teacher_id() {
		return t_teacher_id;
	}
	public void setT_teacher_id(String t_teacher_id) {
		this.t_teacher_id = t_teacher_id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSubmitdate() {
		return submitdate;
	}
	public void setSubmitdate(Date submitdate) {
		this.submitdate = submitdate;
	}
	public Date getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getT_course_teaching_class_homework_submit_baseinfo_id() {
		return t_course_teaching_class_homework_submit_baseinfo_id;
	}
	public void setT_course_teaching_class_homework_submit_baseinfo_id(String t_course_teaching_class_homework_submit_baseinfo_id) {
		this.t_course_teaching_class_homework_submit_baseinfo_id = t_course_teaching_class_homework_submit_baseinfo_id;
	}
	
	
}
