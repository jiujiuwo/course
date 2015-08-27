package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseType;

@Repository
public class CourseTypeDao extends SimpleDao<CourseType> {
	CourseTypeDao(){		
		super(CourseType.class,"t_course_type");
	}
}