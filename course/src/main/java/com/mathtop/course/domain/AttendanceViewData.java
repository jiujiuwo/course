package com.mathtop.course.domain;



public class AttendanceViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7621102703636012436L;


	private Course course;
	private Attendance attendance;
	private TeacherViewData teacherviewdata;
	private CourseTeachingClass courseteachingclass;
	private boolean hasCheckin;//是否已经记录该次考勤
	public Attendance getAttendance() {
		return attendance;
	}
	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	public TeacherViewData getTeacherviewdata() {
		return teacherviewdata;
	}
	public void setTeacherviewdata(TeacherViewData teacherviewdata) {
		this.teacherviewdata = teacherviewdata;
	}
	public CourseTeachingClass getCourseteachingclass() {
		return courseteachingclass;
	}
	public void setCourseteachingclass(CourseTeachingClass courseteachingclass) {
		this.courseteachingclass = courseteachingclass;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public boolean isHasCheckin() {
		return hasCheckin;
	}
	public void setHasCheckin(boolean hasCheckin) {
		this.hasCheckin = hasCheckin;
	}
	
	
}
