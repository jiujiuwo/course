package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.ScoreMarkingTypeDao;
import com.mathtop.course.domain.ScoreMarkingType;

@Service
public class ScoreMarkingTypeService extends SimpleService<ScoreMarkingTypeDao,ScoreMarkingType> {
	
	@Autowired
	protected ScoreMarkingTypeDao dao;
	
	@Autowired
	CourseTeachingClassHomeworkScoreInfoService courseTeachingClassHomeworkScoreInfoService;

	@Override
	public ScoreMarkingTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	public void deleteById(String id){
		courseTeachingClassHomeworkScoreInfoService.deleteByScoreMarkingTypeId(id);
		dao.deleteById(id);
	}
	
}
