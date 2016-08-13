package com.mathtop.course.domain;



public class CourseTeachingClassHomeworkScore extends BaseDomain{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4551211496557887280L;
	
	private String id;
	private String courseTeachingClassHomeworkBaseinfoId;
	private String scoreSystemTypeId;
	private String score;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCourseTeachingClassHomeworkBaseinfoId() {
		return courseTeachingClassHomeworkBaseinfoId;
	}
	public void setCourseTeachingClassHomeworkBaseinfoId(String courseTeachingClassHomeworkBaseinfoId) {
		this.courseTeachingClassHomeworkBaseinfoId = courseTeachingClassHomeworkBaseinfoId;
	}
	public String getScoreSystemTypeId() {
		return scoreSystemTypeId;
	}
	public void setScoreSystemTypeId(String scoreSystemTypeId) {
		this.scoreSystemTypeId = scoreSystemTypeId;
	}

}
