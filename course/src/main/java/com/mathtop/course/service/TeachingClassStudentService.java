package com.mathtop.course.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.TeachingClassStudentDao;

@Service
public class TeachingClassStudentService {
	@Autowired
	TeachingClassStudentDao teachingClassStudentDao;
	
	public void deleteById(HttpServletRequest request, String t_teaching_class_student_id){
		teachingClassStudentDao.deleteById(t_teaching_class_student_id);
		
	}
	public void deleteByTeachingClassId(HttpServletRequest request, String t_teaching_class_id ){
		teachingClassStudentDao.deleteByTeachingClassId(t_teaching_class_id);	
	}
	public void deleteByStudentId(HttpServletRequest request ,String t_student_id){
		teachingClassStudentDao.deleteByStudentId(t_student_id);
	}
	public void deleteByTeachingClassIdAndStudentId(HttpServletRequest request, String t_teaching_class_id,String t_student_id){
		teachingClassStudentDao.deleteByTeachingClassIdAndStudentId(t_teaching_class_id, t_student_id);
	}
}
