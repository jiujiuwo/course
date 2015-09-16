package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassReferenceType;
import com.mathtop.course.domain.CourseTeachingClassReferenceViewData;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.CourseTeachingClassReferenceService;
import com.mathtop.course.service.CourseTeachingClassReferenceTypeService;
import com.mathtop.course.service.TeachingClassService;

@Controller
@RequestMapping("/coursereference")
public class CourseTeachingClassReferenceController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingClassReferenceService referenceService;

	@Autowired
	private CourseTeachingClassReferenceTypeService referencektypeService;

	@Autowired
	TeachingClassService teachingclassService;

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addreference-{t_course_teaching_class_id}-{t_course_teaching_class_reference_type_id}")
	public ModelAndView addreference(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_reference_type_id) {

		ModelAndView view = new ModelAndView();

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 资料类型
		CourseTeachingClassReferenceType selectedCourseReferenceTypeData = referencektypeService
				.getByID(t_course_teaching_class_reference_type_id);
		view.addObject(SelectedObjectConst.Selected_CourseReferenceTypeData, selectedCourseReferenceTypeData);

		view.setViewName("coursereference/add");
		return view;

	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_course_teaching_class_id}-{t_course_teaching_class_reference_type_id}")
	public ModelAndView add(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_reference_type_id,
			@RequestParam("file") MultipartFile[] files) {

		ModelAndView mav = new ModelAndView();

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null) {

				referenceService.Add(request, t_course_teaching_class_id, t_course_teaching_class_reference_type_id, teacher.getId(), title,
						content, files);

			}
		}

		mav.setViewName(
				"redirect:/coursereference/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_reference_type_id + ".html");

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_course_teaching_class_id}-{t_course_teaching_class_reference_type_id}-{t_course_teaching_class_reference_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_reference_type_id,
			@PathVariable String t_course_teaching_class_reference_id) {

		referenceService.DeleteByID(request, t_course_teaching_class_reference_id);

		ModelAndView mav = new ModelAndView();

		mav.setViewName(
				"redirect:/coursereference/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_reference_type_id + ".html");

		return mav;
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatereference-{t_course_teaching_class_id}-{t_course_teaching_class_reference_type_id}-{t_course_teaching_class_reference_id}")
	public ModelAndView updatereference(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_reference_type_id,
			@PathVariable String t_course_teaching_class_reference_id) {

		ModelAndView view = new ModelAndView();

		// 资料具体信息
		CourseTeachingClassReferenceViewData selectedCourseReferenceViewData = referenceService
				.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_reference_id);
		view.addObject(SelectedObjectConst.Selected_CourseReferenceViewData, selectedCourseReferenceViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 资料类型
		CourseTeachingClassReferenceType selectedCourseReferenceTypeData = referencektypeService
				.getByID(t_course_teaching_class_reference_type_id);
		view.addObject(SelectedObjectConst.Selected_CourseReferenceTypeData, selectedCourseReferenceTypeData);

		view.setViewName("coursereference/update");
		return view;

	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}-{t_course_teaching_class_reference_type_id}-{t_course_teaching_class_reference_id}")
	public ModelAndView update(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_reference_type_id,
			@PathVariable String t_course_teaching_class_reference_id, @RequestParam("file") MultipartFile[] files) {
		ModelAndView mav = new ModelAndView();

		String updatetitle = request.getParameter("title");
		String updatecontent = request.getParameter("content");

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null) {

				referenceService.Update(request, t_course_teaching_class_reference_id, teacher.getId(), updatetitle, updatecontent, files);

			}
		}

		mav.setViewName(
				"redirect:/coursereference/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_reference_type_id + ".html");

		return mav;

	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/content-{t_course_teaching_class_id}-{t_course_teaching_class_reference_type_id}-{t_course_teaching_class_reference_id}")
	public ModelAndView content(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_reference_type_id,
			@PathVariable String t_course_teaching_class_reference_id) {

		ModelAndView view = new ModelAndView();

		// 资料具体信息
		CourseTeachingClassReferenceViewData selectedCourseReferenceViewData = referenceService
				.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_reference_id);
		view.addObject(SelectedObjectConst.Selected_CourseReferenceViewData, selectedCourseReferenceViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 资料类型
		CourseTeachingClassReferenceType selectedCourseReferenceTypeData = referencektypeService
				.getByID(t_course_teaching_class_reference_type_id);
		view.addObject(SelectedObjectConst.Selected_CourseReferenceTypeData, selectedCourseReferenceTypeData);

		view.setViewName("coursereference/content");
		return view;

	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list-{t_course_teaching_class_id}-{t_course_teaching_class_reference_type_id}")
	public ModelAndView ListAll(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_reference_type_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		Page<CourseTeachingClassReferenceViewData> pagedCourseTeachingClassReferenceViewData = referenceService
				.getPage(request,t_course_teaching_class_id, t_course_teaching_class_reference_type_id, pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_CourseTeachingClassReferenceViewData, pagedCourseTeachingClassReferenceViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 资料类型
		CourseTeachingClassReferenceType selectedCourseReferenceTypeData = referencektypeService
				.getByID(t_course_teaching_class_reference_type_id);
		view.addObject(SelectedObjectConst.Selected_CourseReferenceTypeData, selectedCourseReferenceTypeData);

		view.setViewName("coursereference/list");
		return view;
	}

}
