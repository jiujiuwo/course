package com.mathtop.course.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassTeacherDao;

@Service
public class CourseTeachingClassTeacherService {

	@Autowired
	CourseTeachingClassTeacherDao courseTeachingClassTeacherDao;

	public void deleteById(HttpServletRequest request, String t_course_teaching_class_teacher_id) {

		// 删除自己
		courseTeachingClassTeacherDao.deleteById(t_course_teaching_class_teacher_id);
	}
	/**
	 * 根据t_course_teaching_class_id
	 * */
	public void deleteByCourseTeachingClassId(HttpServletRequest request, String t_course_teaching_class_id) {
		courseTeachingClassTeacherDao.deleteByCourseTeachingClassId(t_course_teaching_class_id);
	}
	/**
	 * 根据t_teacher_id
	 * */
	public void deleteByTeacherId(HttpServletRequest request, String t_teacher_id) {
		courseTeachingClassTeacherDao.deleteByTeacherId(t_teacher_id);
	}
	/**
	 * 根据t_teaching_type_id
	 * */
	public void deleteByTeachingTypeId(HttpServletRequest request,  String t_teaching_type_id) {
		courseTeachingClassTeacherDao.deleteByTeachingTypeId(
				t_teaching_type_id);

	}
	
	/**
	 * 根据t_course_teaching_class_id、t_teacher_id
	 * */
	public void deleteByCourseTeachingClassIdAndTeacherId(HttpServletRequest request, String t_course_teaching_class_id,
			String t_teacher_id) {
		courseTeachingClassTeacherDao.deleteByCourseTeachingClassIdAndTeacherId(t_course_teaching_class_id, t_teacher_id);

	}
	/**
	 * 根据t_course_teaching_class_id、t_teacher_id、 t_teaching_type_id
	 * */
	public void deleteByCourseTeachingClassIdAndTeacherIdAndTeachingTypeId(HttpServletRequest request, String t_course_teaching_class_id,
			String t_teacher_id, String t_teaching_type_id) {
		courseTeachingClassTeacherDao.deleteByCourseTeachingClassIdAndTeacherIdAndTeachingTypeId(t_course_teaching_class_id, t_teacher_id,
				t_teaching_type_id);

	}
}
