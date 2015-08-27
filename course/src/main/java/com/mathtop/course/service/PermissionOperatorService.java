package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.PermissionOperatorDao;
import com.mathtop.course.domain.PermissionOperator;

@Service
public class PermissionOperatorService extends SimpleService<PermissionOperatorDao,PermissionOperator> {
	
	@Autowired
	protected PermissionOperatorDao dao;

	@Override
	public PermissionOperatorDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
