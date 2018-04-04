package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.ScoreShowType;

@Repository
public class ScoreShowTypeDao extends SimpleDao<ScoreShowType> {
	ScoreShowTypeDao(){		
		super(ScoreShowType.class,"t_score_show_type");
	}
}
