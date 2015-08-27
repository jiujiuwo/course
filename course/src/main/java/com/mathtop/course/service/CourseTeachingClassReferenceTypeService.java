package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassReferenceTypeDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassReferenceType;

@Service
public class CourseTeachingClassReferenceTypeService {

	
	
	@Autowired
	CourseTeachingClassReferenceTypeDao typeDao;
	
	public CourseTeachingClassReferenceType getByID(String id){
		return typeDao.getByID(id);
	}
	public void deleteById(String id) {
		typeDao.deleteById(id);
	}
	public String add(String t_course_teaching_class_id, String name, String note) {		
		return typeDao.add(t_course_teaching_class_id, name,note);		
	}
	
	public Page<CourseTeachingClassReferenceType> getPage(String t_course_teaching_class_id,int pageNo,int pageSize) {
		return typeDao.getPage(t_course_teaching_class_id, pageNo, pageSize);
	}
	
	public Page<CourseTeachingClassReferenceType> getPage(String t_course_teaching_class_id) {
		return typeDao.getPage(t_course_teaching_class_id );
	}
	
	public void update(String id,   String name, String note){
		typeDao.update( id,      name,  note);	
	}
	
}
