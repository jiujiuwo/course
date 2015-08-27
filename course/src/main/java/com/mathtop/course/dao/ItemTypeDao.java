package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.ItemType;

@Repository
public class ItemTypeDao extends SimpleDao<ItemType> {
	ItemTypeDao(){		
		super(ItemType.class,"t_item_type");
	}
}