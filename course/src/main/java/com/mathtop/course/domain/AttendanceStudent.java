package com.mathtop.course.domain;

import java.util.Date;

public class AttendanceStudent extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7550500876608079344L;

	private String id;
	private String t_attendance_id;
	private String t_student_id;
	private String t_attendance_state_id;
	
	private String t_attendance_mode_id;
	private Date checking_in_datetime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_attendance_id() {
		return t_attendance_id;
	}
	public void setT_attendance_id(String t_attendance_id) {
		this.t_attendance_id = t_attendance_id;
	}
	public String getT_student_id() {
		return t_student_id;
	}
	public void setT_student_id(String t_student_id) {
		this.t_student_id = t_student_id;
	}
	public String getT_attendance_state_id() {
		return t_attendance_state_id;
	}
	public void setT_attendance_state_id(String t_attendance_state_id) {
		this.t_attendance_state_id = t_attendance_state_id;
	}
	
	public Date getChecking_in_datetime() {
		return checking_in_datetime;
	}
	public void setChecking_in_datetime(Date checking_in_datetime) {
		this.checking_in_datetime = checking_in_datetime;
	}
	public String getT_attendance_mode_id() {
		return t_attendance_mode_id;
	}
	public void setT_attendance_mode_id(String t_attendance_mode_id) {
		this.t_attendance_mode_id = t_attendance_mode_id;
	}
}
