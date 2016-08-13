package com.mathtop.course.domain;

import java.util.Date;

public class AttendanceStudent extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7550500876608079344L;

	private String id;
	private String attendanceId;
	private String studentId;
	private String attendanceStateId;
	
	private String attendanceModeId;
	private Date checkingInDatetime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getAttendanceStateId() {
		return attendanceStateId;
	}
	public void setAttendanceStateId(String attendanceStateId) {
		this.attendanceStateId = attendanceStateId;
	}
	public String getAttendanceModeId() {
		return attendanceModeId;
	}
	public void setAttendanceModeId(String attendanceModeId) {
		this.attendanceModeId = attendanceModeId;
	}
	public Date getCheckingInDatetime() {
		return checkingInDatetime;
	}
	public void setCheckingInDatetime(Date checkingInDatetime) {
		this.checkingInDatetime = checkingInDatetime;
	}

}
