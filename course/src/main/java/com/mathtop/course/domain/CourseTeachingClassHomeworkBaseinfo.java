package com.mathtop.course.domain;

import java.util.Date;

public class CourseTeachingClassHomeworkBaseinfo extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6564742238383230337L;

	private String id;
	private String t_course_teaching_class_id;
	private String t_teacher_id;
	private String t_course_teaching_class_homework_type_id;
	private String filetype;
	private String filenameformat;
	private Integer filecount;
	private String title;
	private String content;
	private Date pubdate;
	private Date enddate;
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

	public String getT_course_teaching_class_homework_type_id() {
		return t_course_teaching_class_homework_type_id;
	}

	public void setT_course_teaching_class_homework_type_id(String t_course_teaching_class_homework_type_id) {
		this.t_course_teaching_class_homework_type_id = t_course_teaching_class_homework_type_id;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFilenameformat() {
		return filenameformat;
	}

	public void setFilenameformat(String filenameformat) {
		this.filenameformat = filenameformat;
	}

	public Integer getFilecount() {
		return filecount;
	}

	public void setFilecount(Integer filecount) {
		this.filecount = filecount;
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

	/**
	 * 学生是否允许提交作业
	 * */
	public boolean isCanReply() {
		Date now = new Date();

		if (now.getTime() < pubdate.getTime() || now.getTime() > enddate.getTime())
			return true;

		return false;
	}
}
