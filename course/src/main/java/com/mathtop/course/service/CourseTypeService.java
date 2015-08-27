package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTypeDao;
import com.mathtop.course.domain.CourseType;

@Service
public class CourseTypeService extends SimpleService<CourseTypeDao,CourseType> {
	
	@Autowired
	protected CourseTypeDao dao;

	@Override
	public CourseTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
