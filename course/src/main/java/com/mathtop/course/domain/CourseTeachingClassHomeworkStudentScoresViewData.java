package com.mathtop.course.domain;

import java.util.List;

public class CourseTeachingClassHomeworkStudentScoresViewData extends BaseDomain{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5783185337815311294L;
	
	private List<CourseTeachingClassHomeworkStudentScoreSimpleView> lstScore;
	
	private StudentViewData studentView;
	
	
	
	public StudentViewData getStudentView() {
		return studentView;
	}
	public void setStudentView(StudentViewData studentView) {
		this.studentView = studentView;
	}
	public List<CourseTeachingClassHomeworkStudentScoreSimpleView> getLstScore() {
		return lstScore;
	}
	public void setLstScore(List<CourseTeachingClassHomeworkStudentScoreSimpleView> lstScore) {
		this.lstScore = lstScore;
	}
	
}
