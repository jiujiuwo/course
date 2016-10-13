package com.mathtop.course.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseDao;
import com.mathtop.course.dao.CourseDepartmentDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.PreCourseDao;
import com.mathtop.course.domain.Course;
import com.mathtop.course.domain.CourseViewData;
import com.mathtop.course.exception.CourseExistException;

@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	PreCourseDao precourseDao;

	@Autowired
	CourseDepartmentDao coursedepartmentDao;
	
	@Autowired
	CourseTeachingClassService CourseTeachingClassService;

	/**
	 * 注册一个新课程
	 * 
	 * @param user
	 */
	public String add(String name, String englishname, String num, int class_hours, int experiment_hours, String t_course_type_id,
			String t_course_style_id, String[] t_department_ids, String[] precourseId) throws CourseExistException {

		long u = courseDao.getCountByCourseNum(num);
		if (u > 0) {
			throw new CourseExistException("课程已经存在");
		} else {

			String id = courseDao.add(name, englishname, num, class_hours, experiment_hours, t_course_type_id, t_course_style_id);

			// 课程适用专业
			if (t_department_ids != null && t_department_ids.length > 0) {
				for (String t_department_id : t_department_ids) {
					coursedepartmentDao.add(id, t_department_id);
				}
			}

			// 先修课程
			if (precourseId != null && precourseId.length > 0) {
				for (String t_course_id_pre : precourseId) {
					precourseDao.add(id, t_course_id_pre);
				}
			}

			return id;
		}
	}

	/**
	 * 注册一个新课程
	 * 
	 * @param user
	 */
	public String update(String t_course_id, String name, String englishname, String num, int class_hours, int experiment_hours,
			String t_course_type_id, String t_course_style_id, String[] t_department_ids, String[] precourseId)
					throws CourseExistException {

		// 更新课程
		String id = courseDao.update(t_course_id, name, englishname, num, class_hours, experiment_hours, t_course_type_id,
				t_course_style_id);

		// 课程适用专业
		coursedepartmentDao.deleteByCourseId(t_course_id);
		if (t_department_ids != null && t_department_ids.length > 0) {
			for (String t_department_id : t_department_ids) {
				coursedepartmentDao.add(id, t_department_id);
			}
		}

		// 先修课程
		precourseDao.deleteByCourseId(t_course_id);
		if (precourseId != null && precourseId.length > 0) {
			for (String t_course_id_pre : precourseId) {
				precourseDao.add(id, t_course_id_pre);
			}
		}

		return id;

	}
	
	/**
	 * 删除课程
	 * */
	public void deleteById(HttpServletRequest request, String t_course_id){
		
		
		//1.删除使用专业
		coursedepartmentDao.deleteByCourseId(t_course_id);
		
		
		//2.删除先修课程
		precourseDao.deleteByCourseId(t_course_id);
		
		
		//3.删除课程-教学班
		CourseTeachingClassService.deleteByCourseId(request, t_course_id);
		
		//删除自己
		courseDao.deleteById(t_course_id);
	}
	
	/**
	 * 根据t_course_type_id删除课程
	 * */
	public void deleteByCourseTypeId(HttpServletRequest request, String t_course_type_id){
		List<Course> list=courseDao.getByCourseTypeId(t_course_type_id);
		if(list==null)
			return;
		for(Course c:list){
			deleteById(request,c.getId());
		}
		
		
	}
	
	/**
	 * 根据t_course_style_id删除课程
	 * */
	public void deleteByCourseStyleId(HttpServletRequest request, String t_course_style_id){
		List<Course> list=courseDao.getByCourseStyleId(t_course_style_id);
		if(list==null)
			return;
		for(Course c:list){
			deleteById(request,c.getId());
		}
		
		
	}
	
	/**
	 * 根据t_course_type_id、t_course_style_id删除课程
	 * */
	public void deleteByCourseTypeIdAndStyleId(HttpServletRequest request, String t_course_type_id, String t_course_style_id){
		List<Course> list=courseDao.getByCourseTypeIdAndStyleId(t_course_type_id, t_course_style_id);
		if(list==null)
			return;
		for(Course c:list){
			deleteById(request,c.getId());
		}
		
		
	}

	
	
	public CourseViewData getCourseViewDataById(String t_course_id) {
		return courseDao.getCourseViewDataByCourseId(t_course_id);
	}

	/**
	 * 得到
	 */
	public Page<CourseViewData> getPage(int pageNo, int pageSize) {
		return courseDao.getPage(pageNo, pageSize);
	}
	
	/**
	 * 得到
	 */
	public Page<CourseViewData> getAllPage() {
		return courseDao.getAllPage();
	}

}
