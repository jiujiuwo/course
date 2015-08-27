package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.TeachingTypeDao;
import com.mathtop.course.domain.TeachingType;

@Service
public class TeachingTypeService extends SimpleService<TeachingTypeDao,TeachingType> {
	
	@Autowired
	protected TeachingTypeDao dao;

	@Override
	public TeachingTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
