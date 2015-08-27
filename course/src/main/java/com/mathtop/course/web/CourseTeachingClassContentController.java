package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.service.TeachingClassService;

@Controller
@RequestMapping("/coursecontent")
public class CourseTeachingClassContentController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	TeachingClassService teachingclassService;

	/**
	 * 
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/index-{t_course_teaching_class_id}", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, @PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/coursecontent/index");

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);

		return mav;
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/content-{t_course_teaching_class_id}", method = RequestMethod.GET)
	public ModelAndView content(HttpServletRequest request, @PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/coursecontent/content");

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);

		return mav;
	}

}
