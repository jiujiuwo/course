package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.TeachingClass;

@Repository
public class TeachingClassDao extends SimpleDao<TeachingClass> {
	TeachingClassDao(){		
		super(TeachingClass.class,"t_teaching_class");
	}
}