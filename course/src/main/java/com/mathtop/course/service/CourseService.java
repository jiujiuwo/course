package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseDao;
import com.mathtop.course.dao.CourseDepartmentDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.PreCourseDao;
import com.mathtop.course.domain.Course;
import com.mathtop.course.exception.CourseExistException;

@Service
public class CourseService {
	
	
	
	@Autowired
	private CourseDao  courseDao;
	
	@Autowired
	PreCourseDao precourseDao;

	@Autowired
	CourseDepartmentDao coursedepartmentDao;
	
	/**
	 * 注册一个新课程
	 * 
	 * @param user
	 */
	public String add(String name, String englishname, String num,
			int class_hours, int experiment_hours, String t_course_type_id,
			String t_course_style_id,String[] t_department_ids,String[] precourseId) throws CourseExistException {
		
		long u = courseDao.getCountByCourseNum(num);
		if (u >0) {
			throw new CourseExistException("课程已经存在");
		} else {
			
			String id= courseDao.add( name,  englishname,  num,
					 class_hours,  experiment_hours,  t_course_type_id,
					 t_course_style_id);
			
			//课程适用专业
			if(t_department_ids!=null && t_department_ids.length>0){
				for(String t_department_id:t_department_ids){
					coursedepartmentDao.add(id, t_department_id);
				}
			}
			
			//先修课程
			if(precourseId!=null && precourseId.length>0){
				for(String t_course_id_pre:precourseId){
					precourseDao.add(id, t_course_id_pre);
				}
			}
			
			return id;
		}
	}
	
	
    
	
	/**
	 * 得到
	 * */	
	public Page<Course> getPage(int pageNo, int pageSize) {
		return courseDao.getPage(pageNo, pageSize);
	}
	
}
