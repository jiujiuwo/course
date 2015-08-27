package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassHomeworkTypeDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;

@Service
public class CourseTeachingClassHomeworkTypeService {

	
	
	@Autowired
	CourseTeachingClassHomeworkTypeDao homeworktypeDao;
	
	public CourseTeachingClassHomeworkType getByID(String id){
		return homeworktypeDao.getByID(id);
	}
	public void deleteById(String id) {
		homeworktypeDao.deleteById(id);
	}
	public String add(String t_course_teaching_class_id, String name, String note) {		
		return homeworktypeDao.add(t_course_teaching_class_id, name,note);		
	}
	
	public Page<CourseTeachingClassHomeworkType> getPage(String t_course_teaching_class_id,int pageNo,int pageSize) {
		return homeworktypeDao.getPage(t_course_teaching_class_id, pageNo, pageSize);
	}
	
	public Page<CourseTeachingClassHomeworkType> getPage(String t_course_teaching_class_id) {
		return homeworktypeDao.getPage(t_course_teaching_class_id );
	}
	
	public void update(String id,   String name, String note){
		homeworktypeDao.update( id,      name,  note);	
	}
	
}
