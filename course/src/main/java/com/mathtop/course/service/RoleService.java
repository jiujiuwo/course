package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.RoleDao;
import com.mathtop.course.domain.Role;

@Service
public class RoleService extends SimpleService<RoleDao,Role> {
	
	@Autowired
	protected RoleDao dao;

	@Override
	public RoleDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
