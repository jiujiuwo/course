package com.mathtop.course.domain;



public class CourseTeachingClassHomeworkReplyFile extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3233967426720718668L;

	private String id;
	private String t_course_teaching_class_homework_reply_id;
	  private String filename;
	  private String filepath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_course_teaching_class_homework_reply_id() {
		return t_course_teaching_class_homework_reply_id;
	}
	public void setT_course_teaching_class_homework_reply_id(String t_course_teaching_class_homework_reply_id) {
		this.t_course_teaching_class_homework_reply_id = t_course_teaching_class_homework_reply_id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}   
	
}
