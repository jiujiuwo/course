package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.ExaminationConstitutionTypeDao;
import com.mathtop.course.domain.ExaminationConstitutionType;

@Service
public class ExaminationConstitutionTypeService extends SimpleService<ExaminationConstitutionTypeDao,ExaminationConstitutionType> {
	
	@Autowired
	protected ExaminationConstitutionTypeDao dao;

	@Override
	public ExaminationConstitutionTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
