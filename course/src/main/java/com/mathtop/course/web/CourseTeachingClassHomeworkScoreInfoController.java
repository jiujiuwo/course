package com.mathtop.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.ListObjectConst;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassHomeworkScoreInfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;
import com.mathtop.course.domain.ScoreMarkingType;
import com.mathtop.course.domain.ScoreShowType;
import com.mathtop.course.service.CourseTeachingClassHomeworkScoreInfoService;
import com.mathtop.course.service.CourseTeachingClassHomeworkTypeService;
import com.mathtop.course.service.ScoreMarkingTypeService;
import com.mathtop.course.service.ScoreShowTypeService;

@Controller
@RequestMapping("/coursehomeworkscoreinfo")
public class CourseTeachingClassHomeworkScoreInfoController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingClassHomeworkScoreInfoService scoreInfoService;

	@Autowired
	private CourseTeachingClassHomeworkTypeService homeworktypeService;
	
	@Autowired
	ScoreMarkingTypeService scoreMarkingTypeService;

	@Autowired
	ScoreShowTypeService scoreShowTypeService;
	/**
	 * 学生提交作业
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}-{t_score_marking_type_id}-{t_score_show_type_id}")
	public ModelAndView add(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id,
			@PathVariable String t_score_marking_type_id, @PathVariable String t_score_show_type_id

	) {

		ModelAndView mav = new ModelAndView();

		scoreInfoService.add(t_course_teaching_class_homework_baseinfo_id, t_score_marking_type_id,
				t_score_show_type_id);

		// mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID,
		// t_course_teaching_class_id);
		mav.setViewName("redirect:list-" + t_course_teaching_class_id + "-"
				+ t_course_teaching_class_homeworktype_id + "-" + t_course_teaching_class_homework_baseinfo_id
				+ ".html");

		return mav;

	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_score_info_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_score_info_id) {

		scoreInfoService.deleteById(request, t_course_teaching_class_homework_score_info_id);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:list-" + t_course_teaching_class_id + "-"
				+ t_course_teaching_class_homeworktype_id + ".html");

		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_score_info_id}")
	public ModelAndView update(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_score_info_id) {

		ModelAndView mav = new ModelAndView();
		// scoreInfoService.update(request,
		// t_course_teaching_class_homework_score_info_id);

		mav.setViewName("redirect:list-" + t_course_teaching_class_id + "-"
				+ t_course_teaching_class_homeworktype_id + ".html");

		return mav;

	}
	
	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateall-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}")
	public ModelAndView updateall(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id) {

		ModelAndView mav = new ModelAndView();
		String t_score_marking_type_id=request.getParameter("t_score_marking_type_id");
		String t_score_show_type_id=request.getParameter("t_score_show_type_id");

		scoreInfoService.updateByCourseTeachingClassIdAndHomeworkTypeId(t_course_teaching_class_id,t_course_teaching_class_homeworktype_id, t_score_marking_type_id, t_score_show_type_id);
		mav.setViewName("redirect:list-" + t_course_teaching_class_id + "-"
				+ t_course_teaching_class_homeworktype_id + ".html");

		return mav;

	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}")
	public ModelAndView ListAll(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);

		scoreInfoService.addHomeworkBaseInfo(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id);
		
		Page<CourseTeachingClassHomeworkScoreInfoViewData> pagedCourseTeachingClassHomeworkScoreinfoViewData = scoreInfoService
				.getPage(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id, pageNo, pageSize);

		view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkScoreinfoViewData,
				pagedCourseTeachingClassHomeworkScoreinfoViewData);
		
		List<ScoreMarkingType> lstCourseTeachingClassScoreMarkingType=scoreMarkingTypeService.getAll();
		view.addObject(ListObjectConst.List_CourseTeachingClassScoreMarkingType,
				lstCourseTeachingClassScoreMarkingType);
		
		List<ScoreShowType> lstCourseTeachingClassScoreShowType=scoreShowTypeService.getAll();
		view.addObject(ListObjectConst.List_CourseTeachingClassScoreShowType,
				lstCourseTeachingClassScoreShowType);
		

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomeworkscoreinfo/list");
		return view;
	}

}
