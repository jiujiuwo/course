package com.mathtop.course.domain;

import java.util.Date;

public class Attendance extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9012304761222807798L;

	private String id;
	private String t_course_teacing_class_id;
	private String t_attendance_type_id;
	private String t_teacher_id;
	private Date begin_datetime;
	private Date end_datetime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getT_course_teacing_class_id() {
		return t_course_teacing_class_id;
	}
	public void setT_course_teacing_class_id(String t_course_teacing_class_id) {
		this.t_course_teacing_class_id = t_course_teacing_class_id;
	}
	public String getT_teacher_id() {
		return t_teacher_id;
	}
	public void setT_teacher_id(String t_teacher_id) {
		this.t_teacher_id = t_teacher_id;
	}
	public Date getBegin_datetime() {
		return begin_datetime;
	}
	public void setBegin_datetime(Date begin_datetime) {
		this.begin_datetime = begin_datetime;
	}
	public Date getEnd_datetime() {
		return end_datetime;
	}
	public void setEnd_datetime(Date end_datetime) {
		this.end_datetime = end_datetime;
	}
	public String getT_attendance_type_id() {
		return t_attendance_type_id;
	}
	public void setT_attendance_type_id(String t_attendance_type_id) {
		this.t_attendance_type_id = t_attendance_type_id;
	}
	
}
