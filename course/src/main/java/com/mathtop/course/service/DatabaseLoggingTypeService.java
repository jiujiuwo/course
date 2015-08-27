package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.DatabaseLoggingTypeDao;
import com.mathtop.course.domain.DatabaseLoggingType;

@Service
public class DatabaseLoggingTypeService extends SimpleService<DatabaseLoggingTypeDao,DatabaseLoggingType> {
	
	@Autowired
	protected DatabaseLoggingTypeDao dao;

	@Override
	public DatabaseLoggingTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
