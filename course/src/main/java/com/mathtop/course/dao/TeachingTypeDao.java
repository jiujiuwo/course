package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.TeachingType;

@Repository
public class TeachingTypeDao extends SimpleDao<TeachingType> {
	TeachingTypeDao(){		
		super(TeachingType.class,"t_teaching_type");
	}
}