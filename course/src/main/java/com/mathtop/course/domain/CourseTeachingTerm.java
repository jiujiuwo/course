package com.mathtop.course.domain;

import java.util.Date;

public class CourseTeachingTerm extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8927951648641935003L;
	private String id;
	private int teachingYearBegin;
	private int teachingYearEnd;
	private int teachingTerm;
	private int weeks;
	private Date weekBegin;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTeachingYearBegin() {
		return teachingYearBegin;
	}
	public void setTeachingYearBegin(int teachingYearBegin) {
		this.teachingYearBegin = teachingYearBegin;
	}
	public int getTeachingYearEnd() {
		return teachingYearEnd;
	}
	public void setTeachingYearEnd(int teachingYearEnd) {
		this.teachingYearEnd = teachingYearEnd;
	}
	public int getTeachingTerm() {
		return teachingTerm;
	}
	public void setTeachingTerm(int teachingTerm) {
		this.teachingTerm = teachingTerm;
	}
	public int getWeeks() {
		return weeks;
	}
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	public Date getWeekBegin() {
		return weekBegin;
	}
	public void setWeekBegin(Date weekBegin) {
		this.weekBegin = weekBegin;
	}
	public String getTerm() {
		return teachingYearBegin+"-"+teachingYearEnd+"学年第"+teachingTerm+"学期";
	}
	

	
	
	
}
