package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.NaturalClass;

@Repository
public class NaturalClassDao extends SimpleDao<NaturalClass> {
	NaturalClassDao(){		
		super(NaturalClass.class,"t_natural_class");
	}
}