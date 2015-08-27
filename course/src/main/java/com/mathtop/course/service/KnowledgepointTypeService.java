package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.KnowledgepointTypeDao;
import com.mathtop.course.domain.KnowledgepointType;

@Service
public class KnowledgepointTypeService extends SimpleService<KnowledgepointTypeDao,KnowledgepointType> {
	
	@Autowired
	protected KnowledgepointTypeDao dao;

	@Override
	public KnowledgepointTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
