package com.mathtop.course.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassHomeworkTypeDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;

@Service
public class CourseTeachingClassHomeworkTypeService {

	
	
	@Autowired
	CourseTeachingClassHomeworkTypeDao homeworktypeDao;
	
	@Autowired
	CourseTeachingClassReferenceService courseTeachingClassReferenceService;
	
	public CourseTeachingClassHomeworkType getByID(String id){
		return homeworktypeDao.getByID(id);
	}
	
	/**
	 * 根据id删除
	 * */
	public void deleteById(HttpServletRequest request, String id) {
		courseTeachingClassReferenceService.deleteByReferenceTypeId(request, id);
		homeworktypeDao.deleteById(id);		
	}
	
	/**
	 * 根据t_course_teaching_class_id删除
	 * */
	public void deleteByCourseTeachingClassId(HttpServletRequest request,String t_course_teaching_class_id){
		List<CourseTeachingClassHomeworkType> list=homeworktypeDao.getByCourseTeachingClassID(t_course_teaching_class_id);
		if(list==null)
			return ;
		for(CourseTeachingClassHomeworkType t:list){
			deleteById(request,t.getId());
		}
		
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
