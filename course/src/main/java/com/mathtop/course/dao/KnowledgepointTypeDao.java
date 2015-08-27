package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.KnowledgepointType;

@Repository
public class KnowledgepointTypeDao extends SimpleDao<KnowledgepointType> {
	KnowledgepointTypeDao(){		
		super(KnowledgepointType.class,"t_knowledgepoint_type");
	}
}