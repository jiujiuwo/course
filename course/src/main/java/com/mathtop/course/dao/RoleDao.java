package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Role;

@Repository
public class RoleDao extends SimpleDao<Role> {
	RoleDao(){		
		super(Role.class,"t_role");
	}
}