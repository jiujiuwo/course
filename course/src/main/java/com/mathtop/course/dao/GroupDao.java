package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Group;

@Repository
public class GroupDao extends SimpleDao<Group> {
	GroupDao(){		
		super(Group.class,"t_group");
	}
}