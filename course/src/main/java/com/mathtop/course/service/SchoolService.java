package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.SchoolDao;
import com.mathtop.course.domain.School;

@Service
public class SchoolService extends SimpleService<SchoolDao,School> {
	@Autowired
	private SchoolDao dao;
	
	@Override
	public SchoolDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
    
    
}
