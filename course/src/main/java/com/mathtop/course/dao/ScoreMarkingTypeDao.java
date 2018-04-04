package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.ScoreMarkingType;

@Repository
public class ScoreMarkingTypeDao extends SimpleDao<ScoreMarkingType> {
	ScoreMarkingTypeDao(){		
		super(ScoreMarkingType.class,"t_score_marking_type");
	}
}
