package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseStyle;
import com.mathtop.course.domain.CourseType;
import com.mathtop.course.domain.CourseViewData;
import com.mathtop.course.exception.CourseExistException;
import com.mathtop.course.service.CourseService;
import com.mathtop.course.service.CourseStyleService;
import com.mathtop.course.service.CourseTypeService;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

	/**
	 * 自动注入
	 */

	@Autowired
	private CourseStyleService coursestyleService;

	@Autowired
	private CourseTypeService coursetypeService;

	@Autowired
	private CourseService courseService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		SetPage(mav, 1);

		mav.setViewName("/course/add");

		return mav;
	}

	/**
	 * 添加课程
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addcourse")
	public ModelAndView addCourse(HttpServletRequest request) {
		// String t_school_id = request.getParameter("t_school_id");
		String course_num = request.getParameter("course_num");
		String course_name = request.getParameter("course_name");
		String course_english_name = request.getParameter("course_english_name");
		String t_course_style_id = request.getParameter("coursestyleID");
		String t_course_type_id = request.getParameter("coursetypeID");
		String class_hours = request.getParameter("class_hours");
		String experiment_hours = request.getParameter("experiment_hours");
		String[] t_department_id = request.getParameterValues("t_department_id");
		String[] precourseId = request.getParameterValues("precourseId");

		try {
			int n_class_hours = Integer.parseInt(class_hours);
			int n_experiment_hours = Integer.parseInt(experiment_hours);
			courseService.add(course_name, course_english_name, course_num, n_class_hours, n_experiment_hours, t_course_type_id,
					t_course_style_id, t_department_id, precourseId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CourseExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/course/list.html");
		return mav;

	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_school_id}", method = RequestMethod.GET)
	public ModelAndView DELETE() {

		// if (t_school_id != null && t_department_id != null)
		// departmentService.DELETE(t_school_id, t_department_id);

		Integer pageNo = 1;
		return ListAll(pageNo);
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/select-{departmentname}", method = RequestMethod.GET)
	public ModelAndView select(@PathVariable String departmentname) {

		ModelAndView view = new ModelAndView();
		view.setViewName("course/list");

		return view;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_id}")
	public ModelAndView update(HttpServletRequest request, @PathVariable String t_course_id) {

		ModelAndView mav = new ModelAndView();

		CourseViewData selectedCourseViewData = courseService.getCourseViewDataById(t_course_id);
		mav.addObject(SelectedObjectConst.Selected_CourseViewData, selectedCourseViewData);

		// 课程类别
		Page<CourseStyle> pagedCourseStyle = coursestyleService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_CourseStyle, pagedCourseStyle);

		// 课程种类
		Page<CourseType> pagedCourseType = coursetypeService.getPage(1, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_CourseType, pagedCourseType);

		mav.setViewName("/course/update");

		return mav;

	}

	
	/**
	 * 添加课程
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatecourse-{t_course_id}")
	public ModelAndView updateCourse(HttpServletRequest request, @PathVariable String t_course_id) {
		// String t_school_id = request.getParameter("t_school_id");
		String course_num = request.getParameter("course_num");
		String course_name = request.getParameter("course_name");
		String course_english_name = request.getParameter("course_english_name");
		String t_course_style_id = request.getParameter("coursestyleID");
		String t_course_type_id = request.getParameter("coursetypeID");
		String class_hours = request.getParameter("class_hours");
		String experiment_hours = request.getParameter("experiment_hours");
		String[] t_department_id = request.getParameterValues("t_department_id");
		String[] precourseId = request.getParameterValues("precourseId");

		try {
			int n_class_hours = Integer.parseInt(class_hours);
			int n_experiment_hours = Integer.parseInt(experiment_hours);
			courseService.update(t_course_id,course_name, course_english_name, course_num, n_class_hours, n_experiment_hours, t_course_type_id,
					t_course_style_id, t_department_id, precourseId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CourseExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/course/list.html");
		return mav;

	}
	
	/**
	 * 列出全部学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView ListAll(@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		SetPage(mav, pageNo);

		mav.setViewName("course/list");

		return mav;
	}

	private void SetPage(ModelAndView mav, Integer pageNo) {

		// 课程类别
		Page<CourseStyle> pagedCourseStyle = coursestyleService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_CourseStyle, pagedCourseStyle);

		// 课程种类
		Page<CourseType> pagedCourseType = coursetypeService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_CourseType, pagedCourseType);

		Page<CourseViewData> pagedCourseViewData = courseService.getPage(pageNo, CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_CourseViewData, pagedCourseViewData);

	}

}
