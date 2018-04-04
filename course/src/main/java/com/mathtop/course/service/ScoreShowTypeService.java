package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.ScoreShowTypeDao;
import com.mathtop.course.domain.ScoreShowType;

@Service
public class ScoreShowTypeService extends SimpleService<ScoreShowTypeDao,ScoreShowType> {
	
	@Autowired
	protected ScoreShowTypeDao dao;
	
	@Autowired
	CourseTeachingClassHomeworkScoreInfoService courseTeachingClassHomeworkScoreInfoService;

	@Override
	public ScoreShowTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	public void deleteById(String id){
		courseTeachingClassHomeworkScoreInfoService.deleteByScoreShowTypeId(id);
		dao.deleteById(id);
	}
	
}
