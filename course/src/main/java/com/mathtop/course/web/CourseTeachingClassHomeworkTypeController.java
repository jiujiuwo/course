package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;
import com.mathtop.course.service.CourseTeachingClassHomeworkTypeService;
import com.mathtop.course.service.CourseTeachingClassService;

@Controller
@RequestMapping("/coursehomeworktype")
public class CourseTeachingClassHomeworkTypeController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingClassHomeworkTypeService homeworktypeService;

	@Autowired
	CourseTeachingClassService teachingclassService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_course_teaching_class_id}")
	public ModelAndView add(HttpServletRequest request, @PathVariable String t_course_teaching_class_id) {

		String name = request.getParameter("name");

		String note = request.getParameter("note");

		homeworktypeService.add(t_course_teaching_class_id, name, note);

		ModelAndView mav = new ModelAndView();
		// mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID,
		// t_course_teaching_class_id);
		mav.setViewName("redirect:/coursehomeworktype/list-" + t_course_teaching_class_id + ".html");

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id) {

		homeworktypeService.deleteById(request,t_course_teaching_class_homeworktype_id);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/coursehomeworktype/list-" + t_course_teaching_class_id + ".html");

		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}")
	public ModelAndView update(HttpServletRequest request, @PathVariable String t_course_teaching_class_id) {
		ModelAndView mav = new ModelAndView();

		String t_course_teaching_class_homeworktype_id = request.getParameter("id");
		String name = request.getParameter("name");

		String note = request.getParameter("note");
		
		

		homeworktypeService.update(t_course_teaching_class_homeworktype_id, name, note);

		mav.setViewName("redirect:/coursehomeworktype/list-" + t_course_teaching_class_id + ".html");

		return mav;

	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list-{t_course_teaching_class_id}")
	public ModelAndView ListAll(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);
		
		Page<CourseTeachingClassHomeworkType> pagedHomeworkTypeData = homeworktypeService.getPage(t_course_teaching_class_id);

		view.addObject(PagedObjectConst.Paged_CourseHomeworkTypeData, pagedHomeworkTypeData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		view.setViewName("coursehomeworktype/list");
		return view;
	}

}
