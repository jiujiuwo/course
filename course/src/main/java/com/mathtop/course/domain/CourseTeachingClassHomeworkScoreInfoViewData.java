package com.mathtop.course.domain;



public class CourseTeachingClassHomeworkScoreInfoViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5614524203482683949L;

	
	private CourseTeachingClassHomeworkScoreInfo scoreInfo;
	private CourseTeachingClassHomeworkBaseinfo homeworkBaseinfo;
	private ScoreMarkingType scoreMarkingType;
	private ScoreShowType scoreShowType;
	
	public ScoreMarkingType getScoreMarkingType() {
		return scoreMarkingType;
	}
	public void setScoreMarkingType(ScoreMarkingType scoreMarkingType) {
		this.scoreMarkingType = scoreMarkingType;
	}
	public ScoreShowType getScoreShowType() {
		return scoreShowType;
	}
	public void setScoreShowType(ScoreShowType scoreShowType) {
		this.scoreShowType = scoreShowType;
	}
	public CourseTeachingClassHomeworkScoreInfo getScoreInfo() {
		return scoreInfo;
	}
	public void setScoreInfo(CourseTeachingClassHomeworkScoreInfo scoreInfo) {
		this.scoreInfo = scoreInfo;
	}
	public CourseTeachingClassHomeworkBaseinfo getHomeworkBaseinfo() {
		return homeworkBaseinfo;
	}
	public void setHomeworkBaseinfo(CourseTeachingClassHomeworkBaseinfo homeworkBaseinfo) {
		this.homeworkBaseinfo = homeworkBaseinfo;
	}

	
}
