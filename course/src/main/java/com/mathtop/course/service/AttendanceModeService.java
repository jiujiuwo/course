package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.AttendanceModeDao;
import com.mathtop.course.domain.AttendanceMode;

@Service
public class AttendanceModeService extends SimpleService<AttendanceModeDao,AttendanceMode> {
	
	@Autowired
	protected AttendanceModeDao dao;

	@Override
	public AttendanceModeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
