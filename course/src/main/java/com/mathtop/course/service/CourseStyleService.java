package com.mathtop.course.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseStyleDao;
import com.mathtop.course.domain.CourseStyle;

@Service
public class CourseStyleService extends SimpleService<CourseStyleDao,CourseStyle> {
	
	@Autowired
	protected CourseStyleDao dao;

	@Autowired
	CourseService courseService;
	
	@Override
	public CourseStyleDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	public void deleteById(HttpServletRequest request,String t_course_style_id){
		
	}
}
