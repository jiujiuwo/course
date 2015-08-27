package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.UserContactTypeDao;
import com.mathtop.course.domain.UserContactType;

@Service
public class ContactTypeService extends SimpleService<UserContactTypeDao,UserContactType> {
	
	@Autowired
	protected UserContactTypeDao dao;

	@Override
	public UserContactTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
