package com.mathtop.course.domain;



public class CourseTeachingClassForumTopicFile extends BaseDomain {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3525442298067390215L;
	private String id;
	private String t_course_teaching_class_forum_topic_id;
	  private String filename;
	  private String filepath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getT_course_teaching_class_forum_topic_id() {
		return t_course_teaching_class_forum_topic_id;
	}
	public void setT_course_teaching_class_forum_topic_id(String t_course_teaching_class_forum_topic_id) {
		this.t_course_teaching_class_forum_topic_id = t_course_teaching_class_forum_topic_id;
	}
   
	
}
