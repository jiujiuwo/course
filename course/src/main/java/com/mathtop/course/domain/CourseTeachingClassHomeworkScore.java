package com.mathtop.course.domain;



public class CourseTeachingClassHomeworkScore extends BaseDomain{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4551211496557887280L;
	
	private String id;
	private String t_course_teaching_class_homework_baseinfo_id;
	private String t_score_system_type_id;
	private String score;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getT_score_system_type_id() {
		return t_score_system_type_id;
	}
	public void setT_score_system_type_id(String t_score_system_type_id) {
		this.t_score_system_type_id = t_score_system_type_id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getT_course_teaching_class_homework_baseinfo_id() {
		return t_course_teaching_class_homework_baseinfo_id;
	}
	public void setT_course_teaching_class_homework_baseinfo_id(String t_course_teaching_class_homework_baseinfo_id) {
		this.t_course_teaching_class_homework_baseinfo_id = t_course_teaching_class_homework_baseinfo_id;
	}
}
