package com.mathtop.course.domain;

import java.util.Date;

import com.mathtop.course.cons.CommonConstant;

public class CourseTeachingClassHomeworkBaseinfo extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6564742238383230337L;

	private String id;
	private String courseTeachingClassId;
	private String teacherId;
	private String courseTeachingClassHomeworkTypeId;
	private int flag;
	private FileRequirementManager fileRequirement;	
	private String title;
	private String content;
	private Date pubdate;
	private Date enddate;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	

	public String getCourseTeachingClassId() {
		return courseTeachingClassId;
	}

	public void setCourseTeachingClassId(String courseTeachingClassId) {
		this.courseTeachingClassId = courseTeachingClassId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getCourseTeachingClassHomeworkTypeId() {
		return courseTeachingClassHomeworkTypeId;
	}

	public void setCourseTeachingClassHomeworkTypeId(String courseTeachingClassHomeworkTypeId) {
		this.courseTeachingClassHomeworkTypeId = courseTeachingClassHomeworkTypeId;
	}
	
	
	/**
	 * 学生是否允许提交作业
	 * */
	public boolean isCanStudentSubmit() {
		Date now = new Date();

		if (now.getTime() > pubdate.getTime() && now.getTime() < enddate.getTime())
			return true;

		return false;
	}

	/**
	 * 学生是否允许提交作业
	 * */
	public boolean isCanReply() {
		Date now = new Date();

		if (now.getTime() < pubdate.getTime() || now.getTime() > enddate.getTime())
			return true;

		return false;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public boolean isFlagGroup(){
		return (flag & CommonConstant.HOMEWORK_FLAG_GROUP) !=0;
	}

	public FileRequirementManager getFileRequirement() {
		return fileRequirement;
	}

	public void setFileRequirement(FileRequirementManager fileRequirement) {
		this.fileRequirement = fileRequirement;
	}
}
