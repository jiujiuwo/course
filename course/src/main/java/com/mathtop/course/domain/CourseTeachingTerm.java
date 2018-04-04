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
	private Date weekBegin;//务必是周一
	private static Date dateComputed;//已经计算过今天是第几周，每天只算一次即可
	private static int weekIndex;//教学来的第几周
	

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
	
	private int betweenDays(Date d){
		if(weekBegin==null)
			return 0;
		
		Date d1=new Date();
		return  (int)((d1.getTime() - d.getTime())/86400000);//1000*3600*24
	}
	
	//返回第几周
	public int getWeekIndex(){
		if(weekBegin==null)
			return 0;
		
		
		
		if(dateComputed==null){
			dateComputed=new Date();
			weekIndex= betweenDays(weekBegin)/7+1;
			return weekIndex;
		}else if(betweenDays(dateComputed)<1){
			return weekIndex;
		}else{
			dateComputed=new Date();
			weekIndex= betweenDays(weekBegin)/7+1;
			return weekIndex;
		}
	}
	public boolean isCurrentTerm(){
		Date d=new Date();
		int n= betweenDays(weekBegin)/7+1;
		
		
		if(n<0 || n>weeks)
			return false;
		return true;
	}
	

	
	
	
}
