package com.mathtop.course.domain;

import java.util.List;

public class CourseTeachingClassHomeworkStatisticsViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3089394638570581L;

	private StudentViewData student;
	private CourseTeachingClassHomeworkBaseinfoViewData homeworkbaseinfo;
	private List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> homeworksubmit;
	private List<CourseTeachingClassHomeworkReply> homeworkreply;

	public StudentViewData getStudent() {
		return student;
	}

	public void setStudent(StudentViewData student) {
		this.student = student;
	}

	public CourseTeachingClassHomeworkBaseinfoViewData getHomeworkbaseinfo() {
		return homeworkbaseinfo;
	}

	public void setHomeworkbaseinfo(CourseTeachingClassHomeworkBaseinfoViewData homeworkbaseinfo) {
		this.homeworkbaseinfo = homeworkbaseinfo;
	}

	public List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> getHomeworksubmit() {
		return homeworksubmit;
	}

	public void setHomeworksubmit(List<CourseTeachingClassHomeworkSubmitBaseinfoViewData> homeworksubmit) {
		this.homeworksubmit = homeworksubmit;
	}

	public List<CourseTeachingClassHomeworkReply> getHomeworkreply() {
		return homeworkreply;
	}

	public void setHomeworkreply(List<CourseTeachingClassHomeworkReply> homeworkreply) {
		this.homeworkreply = homeworkreply;
	}

	// 如果作业提交，是否是迟交作业
	public boolean isDelayedSubmit() {
		//还没有提交作业
		if(homeworksubmit.size()==0)
			return false;
		
		
		
		
		for(CourseTeachingClassHomeworkSubmitBaseinfoViewData d:homeworksubmit){
			if(d.isDelayedSubmit())
				return true;
		}
		
		return false;
	}
}
