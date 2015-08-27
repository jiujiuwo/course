package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.UserContactType;

@Repository
public class UserContactTypeDao extends SimpleDao<UserContactType> {
	UserContactTypeDao(){		
		super(UserContactType.class,"t_user_contact_type");
	}
}
