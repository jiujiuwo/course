package com.mathtop.course.domain;

public class CourseTeachingClassHomeworkScoreInfo extends BaseDomain{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4551211496557887280L;
	
	private String id;
	private String courseTeachingClassHomeworkBaseinfoId;
	private String scoreMarkingTypeId;
	private String scoreShowTypeId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	public String getCourseTeachingClassHomeworkBaseinfoId() {
		return courseTeachingClassHomeworkBaseinfoId;
	}
	public void setCourseTeachingClassHomeworkBaseinfoId(String courseTeachingClassHomeworkBaseinfoId) {
		this.courseTeachingClassHomeworkBaseinfoId = courseTeachingClassHomeworkBaseinfoId;
	}
	
	public String getScoreMarkingTypeId() {
		return scoreMarkingTypeId;
	}
	public void setScoreMarkingTypeId(String scoreMarkingTypeId) {
		this.scoreMarkingTypeId = scoreMarkingTypeId;
	}
	public String getScoreShowTypeId() {
		return scoreShowTypeId;
	}
	public void setScoreShowTypeId(String scoreShowTypeId) {
		this.scoreShowTypeId = scoreShowTypeId;
	}
	

}
