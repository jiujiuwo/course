package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.School;

@Repository
public class SchoolDao extends SimpleDao<School> {
	SchoolDao(){		
		super(School.class,"t_school");
	}
}

