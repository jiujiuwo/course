package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.ItemKindsDao;
import com.mathtop.course.domain.ItemKinds;

@Service
public class ItemKindsService extends SimpleService<ItemKindsDao,ItemKinds> {
	
	@Autowired
	protected ItemKindsDao dao;

	@Override
	public ItemKindsDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
