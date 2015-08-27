package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.ItemDifficultyDao;
import com.mathtop.course.domain.ItemDifficulty;

@Service
public class ItemDifficultyService extends SimpleService<ItemDifficultyDao,ItemDifficulty> {
	
	@Autowired
	protected ItemDifficultyDao dao;

	@Override
	public ItemDifficultyDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
}
