package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.ItemKinds;

@Repository
public class ItemKindsDao extends SimpleDao<ItemKinds> {
	ItemKindsDao(){		
		super(ItemKinds.class,"t_item_kinds");
	}
}