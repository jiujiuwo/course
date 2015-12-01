package com.mathtop.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.AttendanceDao;
import com.mathtop.course.dao.AttendanceStudentDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Attendance;
import com.mathtop.course.domain.AttendanceViewData;

@Service
public class AttendanceService {

	@Autowired
	AttendanceDao attendancedao;
	
	@Autowired
	AttendanceStudentDao attendanceStudentDao;
	
	
	public String add(String t_course_teaching_class_id, String t_attendance_type_id,String t_teacher_id, String begin_datetime,String end_datetime) {
		return attendancedao.add(t_course_teaching_class_id, t_attendance_type_id,t_teacher_id, begin_datetime, end_datetime);
	}
	
	
	/**
	 * 根据id删除考勤信息
	 * */
	public void deleteById(String id) {
		attendanceStudentDao.deleteByAttendanceId(id);
		attendancedao.deleteById(id);
	}	
	
	
	/**
	 * 根据教师id删除考勤信息
	 * */
	public void deleteByTeacherId(String t_teacher_id){
		List<Attendance> list=attendancedao.getByTeacherID(t_teacher_id);
		if(list==null)
			return;
		for(Attendance a:list){
			attendanceStudentDao.deleteByAttendanceId(a.getId());
		}
		attendancedao.deleteByTeacherId(t_teacher_id);
	}
	
	/**
	 * 根据教师id删除考勤信息
	 * */
	public void deleteByCourseTeachingClassId(String t_course_teaching_class_id){
		List<Attendance> list=attendancedao.getByCourseTeachingClassID(t_course_teaching_class_id);
		if(list==null)
			return;
		for(Attendance a:list){
			attendanceStudentDao.deleteByAttendanceId(a.getId());
		}
		attendancedao.deleteByCourseTeachingClassId(t_course_teaching_class_id);
	}
	
	/**
	 * 根据考勤类型删除考勤信息
	 * */
	public void deleteByAttendanceTypeId(String t_attendance_type_id){
		List<Attendance> list=attendancedao.getByAttendanceTypeId(t_attendance_type_id);
		if(list==null)
			return;
		for(Attendance a:list){
			attendanceStudentDao.deleteByAttendanceId(a.getId());
		}
		attendancedao.deleteByAttendanceTypeId(t_attendance_type_id);
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
