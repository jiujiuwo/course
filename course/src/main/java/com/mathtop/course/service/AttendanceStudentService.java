package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.AttendanceStateDao;
import com.mathtop.course.dao.AttendanceStudentDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.AttendanceSpecificStatistics;
import com.mathtop.course.domain.AttendanceState;
import com.mathtop.course.domain.AttendanceStateModeViewData;
import com.mathtop.course.domain.AttendanceStudentViewData;
import com.mathtop.course.domain.HTMLColors;

@Service
public class AttendanceStudentService {

	@Autowired
	AttendanceStudentDao attendancestudentdao;
	
	@Autowired
	AttendanceStateDao attendanceStateDao;
	
	public void deleteById(String id) {
		attendancestudentdao.deleteById(id);
	}
	public void deleteByAttendanceIdStudentId(String t_attendance_id,String t_student_id){
		attendancestudentdao.deleteByAttendanceIdStudentId(t_attendance_id,t_student_id);
	}
	public void deleteByAttendanceId(String t_attendance_id){
		attendancestudentdao.deleteByAttendanceId(t_attendance_id);
	}
	public String add(String t_attendance_id, String t_student_id, String a_ttendance_state_id, String a_ttendance_mode_id,Date checking_in_datetime) {
		return attendancestudentdao.add(t_attendance_id, t_student_id, a_ttendance_state_id, a_ttendance_mode_id,checking_in_datetime);
	}
	
	
	public Page<AttendanceStudentViewData> getPage(String t_attendance_id, int pageNo, int pageSize) {
		return attendancestudentdao.getPage(t_attendance_id, pageNo, pageSize);
	}
	public Page<AttendanceStudentViewData> getPage(String t_attendance_id) {
		return attendancestudentdao.getPage(t_attendance_id);
	}
	public void update(String id, String t_student_id, String a_ttendance_state_id, String a_ttendance_mode_id,Date checking_in_datetime){
		attendancestudentdao.update(id, t_student_id, a_ttendance_state_id, a_ttendance_mode_id,checking_in_datetime);
	}
	
	public Page<AttendanceStateModeViewData> getPage(String t_course_teaching_class_id, String t_attendance_type_id,String t_student_id, int pageNo, int pageSize) {
		return attendancestudentdao.getPage(t_course_teaching_class_id, t_attendance_type_id,t_student_id, pageNo, pageSize);
	}
	
	
	/**
	 * 取得单次统计数据
	 * */
	public List<AttendanceSpecificStatistics> getAttendanceSpecificStatistics(String t_attendance_id){
		List<AttendanceSpecificStatistics> list=new ArrayList<AttendanceSpecificStatistics>();
		
		List<AttendanceState> listState=attendanceStateDao.getAll();
		for(AttendanceState s:listState){
			AttendanceSpecificStatistics spe=new AttendanceSpecificStatistics();
			spe.setnCount(attendancestudentdao.getCountbyAttendanceIDAndStateId(t_attendance_id,s.getId()));
			spe.setState(s);
			list.add(spe);
		}
		return list;
	}
	
	public List<chartDataValue> getChartDataValueofAttendanceSpecificStatistics(String t_attendance_id){
		List<chartDataValue> list =new ArrayList<chartDataValue>();
		
		List<AttendanceSpecificStatistics> datas=getAttendanceSpecificStatistics(t_attendance_id);
		HTMLColors color=new HTMLColors();
		for(AttendanceSpecificStatistics d:datas){
			chartDataValue c=new chartDataValue();
			c.setValue(d.getnCount());
			c.setColor(color.getColor());
			list.add(c);
		}
		
		return list;
	}
	
}
