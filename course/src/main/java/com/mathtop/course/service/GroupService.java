package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.GroupDao;
import com.mathtop.course.domain.Group;

@Service
public class GroupService extends SimpleService<GroupDao,Group> {
	
	@Autowired
	protected GroupDao dao;

	@Override
	public GroupDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
