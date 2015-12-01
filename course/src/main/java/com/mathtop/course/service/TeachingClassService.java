package com.mathtop.course.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.TeachingClassDao;

@Service
public class TeachingClassService {
	@Autowired
	TeachingClassDao teachingClassDao;
	
	@Autowired
	TeachingClassStudentService teachingClassStudentService;
	
	@Autowired
	CourseTeachingClassService courseTeachingClassService;
	
	public void deleteById(HttpServletRequest request, String t_teaching_class_id) {

		courseTeachingClassService.deleteByTeachingClassId(request, t_teaching_class_id);
		teachingClassStudentService.deleteByTeachingClassId(request, t_teaching_class_id);
		
		//删除自己
		teachingClassDao.deleteById(t_teaching_class_id);
	}
}
