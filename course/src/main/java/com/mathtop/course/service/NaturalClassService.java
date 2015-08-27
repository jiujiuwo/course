package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.AttendanceStateDao;
import com.mathtop.course.domain.AttendanceState;

@Service
public class NaturalClassService extends SimpleService<AttendanceStateDao,AttendanceState> {
	
	@Autowired
	protected AttendanceStateDao dao;

	@Override
	public AttendanceStateDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
