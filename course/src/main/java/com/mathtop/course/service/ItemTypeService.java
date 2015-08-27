package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.ItemTypeDao;
import com.mathtop.course.domain.ItemType;

@Service
public class ItemTypeService extends SimpleService<ItemTypeDao,ItemType> {
	
	@Autowired
	protected ItemTypeDao dao;

	@Override
	public ItemTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
