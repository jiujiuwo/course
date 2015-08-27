package com.mathtop.course.domain;

import java.util.Date;



public class CourseTeachingClassForumTopic extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1335599420458493229L;

	private String id;
	private String t_course_teaching_class_id;
	private String t_user_id;
	private String title;
	private String content;
	private Date created_date;
	private Date last_modified_date;
	private int view_count;
	private int flag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_course_teaching_class_id() {
		return t_course_teaching_class_id;
	}
	public void setT_course_teaching_class_id(String t_course_teaching_class_id) {
		this.t_course_teaching_class_id = t_course_teaching_class_id;
	}
	public String getT_user_id() {
		return t_user_id;
	}
	public void setT_user_id(String t_user_id) {
		this.t_user_id = t_user_id;
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
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(Date last_modified_date) {
		this.last_modified_date = last_modified_date;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
