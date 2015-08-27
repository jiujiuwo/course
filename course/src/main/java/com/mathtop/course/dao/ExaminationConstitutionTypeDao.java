package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.ExaminationConstitutionType;

@Repository
public class ExaminationConstitutionTypeDao extends SimpleDao<ExaminationConstitutionType> {
	ExaminationConstitutionTypeDao(){		
		super(ExaminationConstitutionType.class,"t_examination_constitution_type");
	}
}
