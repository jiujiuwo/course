package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.CourseStyle;

@Repository
public class CourseStyleDao extends SimpleDao<CourseStyle> {
	CourseStyleDao(){		
		super(CourseStyle.class,"t_course_style");
	}
}