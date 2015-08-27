package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.ExaminationType;

@Repository
public class ExaminationTypeDao extends SimpleDao<ExaminationType> {
	ExaminationTypeDao(){		
		super(ExaminationType.class,"t_examination_type");
	}
}