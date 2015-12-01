package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.AttendanceTypeDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.AttendanceType;

@Service
public class AttendanceTypeService {
	
	@Autowired
	protected AttendanceTypeDao dao;
	
	@Autowired
	AttendanceService attendanceService;

	public AttendanceType getByID(String id){
		return dao.getByID(id);
	}
	public void deleteById(String id) {
		//1.删除t_attandance表中数据
		attendanceService.deleteByAttendanceTypeId(id);
		
		//2.删除自己数据
		dao.deleteById(id);
	}
	public String add(String t_course_teaching_class_id, String name, String note) {		
		return dao.add(t_course_teaching_class_id, name,note);		
	}
	
	public Page<AttendanceType> getPage(String t_course_teaching_class_id,int pageNo,int pageSize) {
		return dao.getPage(t_course_teaching_class_id, pageNo, pageSize);
	}
	
	public Page<AttendanceType> getPage(String t_course_teaching_class_id) {
		return dao.getPage(t_course_teaching_class_id );
	}
	
	public void update(String id,   String name, String note){
		dao.update( id,      name,  note);	
	}
	
	
}
