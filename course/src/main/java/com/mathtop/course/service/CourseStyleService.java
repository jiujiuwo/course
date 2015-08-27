package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseStyleDao;
import com.mathtop.course.domain.CourseStyle;

@Service
public class CourseStyleService extends SimpleService<CourseStyleDao,CourseStyle> {
	
	@Autowired
	protected CourseStyleDao dao;

	@Override
	public CourseStyleDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
