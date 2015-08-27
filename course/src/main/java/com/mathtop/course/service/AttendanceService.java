package com.mathtop.course.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.AttendanceDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.AttendanceViewData;

@Service
public class AttendanceService {

	@Autowired
	AttendanceDao attendancedao;
	
	public void deleteById(String id) {
		attendancedao.deleteById(id);
	}
	public String add(String t_course_teaching_class_id, String t_attendance_type_id,String t_teacher_id, String begin_datetime,String end_datetime) {
		return attendancedao.add(t_course_teaching_class_id, t_attendance_type_id,t_teacher_id, begin_datetime, end_datetime);
	}
	
	
	public AttendanceViewData getAttendanceViewDataByAttendanceId(String t_attendance_id){
		return attendancedao.getAttendanceViewDataByAttendanceId(t_attendance_id);
	}
	public Page<AttendanceViewData> getPage(String t_course_teaching_class_id, String t_attendance_type_id,int pageNo, int pageSize) {
		return attendancedao.getPage(t_course_teaching_class_id,t_attendance_type_id, pageNo, pageSize);
	}
	
	public void update(String id,String t_teacher_id,Date begin_datetime,Date end_datetime){
		attendancedao.update(id, t_teacher_id, begin_datetime, end_datetime);
	}
	
}
