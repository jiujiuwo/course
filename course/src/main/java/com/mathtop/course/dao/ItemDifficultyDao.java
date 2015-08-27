package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.ItemDifficulty;

@Repository
public class ItemDifficultyDao extends SimpleDao<ItemDifficulty> {
	ItemDifficultyDao(){		
		super(ItemDifficulty.class,"t_item_difficulty");
	}
}