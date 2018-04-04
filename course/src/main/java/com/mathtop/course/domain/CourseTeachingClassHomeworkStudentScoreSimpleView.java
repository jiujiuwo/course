package com.mathtop.course.domain;

public class CourseTeachingClassHomeworkStudentScoreSimpleView extends BaseDomain{

	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1193523302904023463L;
	private CourseTeachingClassHomeworkStudentScore score;
	private CourseTeachingClassHomeworkScoreInfoViewData scoreInfo;
	public CourseTeachingClassHomeworkStudentScore getScore() {
		return score;
	}
	public void setScore(CourseTeachingClassHomeworkStudentScore score) {
		this.score = score;
	}
	public CourseTeachingClassHomeworkScoreInfoViewData getScoreInfo() {
		return scoreInfo;
	}
	public void setScoreInfo(CourseTeachingClassHomeworkScoreInfoViewData scoreInfo) {
		this.scoreInfo = scoreInfo;
	}
	
	public String getScoreStatus(){
		if(score!=null && scoreInfo!=null){
			ScoreShowType showType=scoreInfo.getScoreShowType();
			if(showType!=null){
				String name=showType.getName();
				if(name.contains("完成")){
					if(score.getDescription().length()>0 || score.getScore().length()>0)
						return "完成";
					else
						return null;
				}
			}
			return null;
		}
		
		return null;
		
	}
}
