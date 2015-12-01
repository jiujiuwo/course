package com.mathtop.course.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.TeachingTypeDao;
import com.mathtop.course.domain.TeachingType;

@Service
public class TeachingTypeService extends SimpleService<TeachingTypeDao,TeachingType> {
	
	@Autowired
	protected TeachingTypeDao dao;

	@Override
	public TeachingTypeDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	public void deleteById(HttpServletRequest request, String t_teaching_type_id){
		
		//删除自己
		super.deleteById(t_teaching_type_id);
	}
	
}
