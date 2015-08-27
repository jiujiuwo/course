package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.ScoreSystemType;

@Repository
public class ScoreSystemTypeDao extends SimpleDao<ScoreSystemType> {
	ScoreSystemTypeDao(){		
		super(ScoreSystemType.class,"t_score_system_type");
	}
}
