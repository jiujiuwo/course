package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.ExaminationTypeDao;
import com.mathtop.course.domain.ExaminationType;

@Service
public class ExaminationTypeService extends SimpleService<ExaminationTypeDao,ExaminationType> {
	
	@Autowired
	protected ExaminationTypeDao dao;

	@Override
	public ExaminationTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
