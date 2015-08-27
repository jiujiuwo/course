package com.mathtop.course.domain;



public class CourseTeachingClassHomeworkScoreViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5614524203482683949L;

	
	private CourseTeachingClassHomeworkScore score;
	private ScoreSystemType systemType;
	public CourseTeachingClassHomeworkScore getScore() {
		return score;
	}
	public void setScore(CourseTeachingClassHomeworkScore score) {
		this.score = score;
	}
	public ScoreSystemType getSystemType() {
		return systemType;
	}
	public void setSystemType(ScoreSystemType systemType) {
		this.systemType = systemType;
	}
	
}
