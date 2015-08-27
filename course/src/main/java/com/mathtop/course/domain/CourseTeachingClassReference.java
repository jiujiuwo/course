package com.mathtop.course.domain;

import java.util.Date;

public class CourseTeachingClassReference extends BaseDomain {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8309110997151684180L;
	private String id;
	private String t_course_teaching_class_id;
	private String t_teacher_id;
	private String t_course_teaching_class_reference_type_id;
	private String title;
	private String content;
	private Date pubdate;
	private Date modifieddate;
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

	public String getT_course_teaching_class_reference_type_id() {
		return t_course_teaching_class_reference_type_id;
	}

	public void setT_course_teaching_class_reference_type_id(String t_course_teaching_class_reference_type_id) {
		this.t_course_teaching_class_reference_type_id = t_course_teaching_class_reference_type_id;
	}

	public Date getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}
}
