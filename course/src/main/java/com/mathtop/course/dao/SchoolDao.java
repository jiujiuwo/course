package com.mathtop.course.dao;

import org.springframework.stereotype.Repository;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.domain.School;

@Repository
public class SchoolDao extends SimpleDao<School> {
	SchoolDao(){		
		super(School.class,"t_school");
	}
	
	
	public String getDefaultSchoolId(){
		School s=getByName(CommonConstant.DEFAULT_SCHOOL_NAME);
		if(s==null){
			s=new School();
			s.setName(CommonConstant.DEFAULT_SCHOOL_NAME);
			return add(s);
		}
		return s.getId();
	}
}

