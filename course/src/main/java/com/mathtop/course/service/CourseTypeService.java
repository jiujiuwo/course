package com.mathtop.course.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTypeDao;
import com.mathtop.course.domain.CourseType;

@Service
public class CourseTypeService extends SimpleService<CourseTypeDao,CourseType> {
	
	@Autowired
	protected CourseTypeDao dao;
	
	@Autowired
	CourseService courseService;

	@Override
	public CourseTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
public void deleteById(HttpServletRequest request,String t_course_type_id){
	
	dao.deleteById(t_course_type_id);
	}
	
}
