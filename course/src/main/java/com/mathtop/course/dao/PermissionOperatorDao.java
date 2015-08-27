package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.PermissionOperator;

@Repository
public class PermissionOperatorDao extends SimpleDao<PermissionOperator> {
	PermissionOperatorDao(){		
		super(PermissionOperator.class,"t_permission_operator");
	}
}