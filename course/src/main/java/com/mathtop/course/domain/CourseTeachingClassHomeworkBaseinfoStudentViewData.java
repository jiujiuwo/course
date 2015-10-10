package com.mathtop.course.domain;

public class CourseTeachingClassHomeworkBaseinfoStudentViewData extends CourseTeachingClassHomeworkBaseinfoViewData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3488058091558969595L;
	
	private boolean studentSubmitted;//是否提交作业
	
	public CourseTeachingClassHomeworkBaseinfoStudentViewData(CourseTeachingClassHomeworkBaseinfoViewData data){
		this.setCourseteachingclass(data.getCourseteachingclass());
		this.setHomeworkbaseinfo(data.getHomeworkbaseinfo());
		this.setHomeworkFileList(data.getHomeworkFileList());
		this.setHomeworkType(data.getHomeworkType());
		this.setTeacher(data.getTeacher());
	}

	public boolean isStudentSubmitted() {
		return studentSubmitted;
	}

	public void setStudentSubmitted(boolean studentSubmitted) {
		this.studentSubmitted = studentSubmitted;
	}

	
	

}
