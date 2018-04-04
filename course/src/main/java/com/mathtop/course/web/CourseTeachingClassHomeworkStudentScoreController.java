package com.mathtop.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.ListObjectConst;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassHomeworkScoreInfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStudentScore;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStudentScoresViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.CourseTeachingClassHomeworkScoreInfoService;
import com.mathtop.course.service.CourseTeachingClassHomeworkStudentScoreService;
import com.mathtop.course.service.CourseTeachingClassHomeworkTypeService;

@Controller
@RequestMapping("/coursehomeworkscore")
public class CourseTeachingClassHomeworkStudentScoreController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingClassHomeworkStudentScoreService scoreService;

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingClassHomeworkScoreInfoService scoreInfoService;

	@Autowired
	private CourseTeachingClassHomeworkTypeService homeworktypeService;

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_student_score_id}")
	public ModelAndView delete(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_student_score_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView mav = new ModelAndView();

		scoreService.deleteById(request, t_course_teaching_class_homework_student_score_id);

		pageNo = pageNo == null ? 1 : pageNo;

		mav.setViewName("redirect:list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id
				+ ".html?pageNo=" + pageNo);

		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_score_info_id}-{t_student_id}")
	public ModelAndView update(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_score_info_id, @PathVariable String t_student_id) {

		ModelAndView mav = new ModelAndView();

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo == null) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "用户信息为空.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + ".html");
			return mav;
		}

		Teacher teacher = userinfo.getTeacher();
		if (teacher == null) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "教师为空.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + ".html");
			return mav;
		}

		String t_teacher_id = teacher.getId();
		String score = request.getParameter("score");
		String description = request.getParameter("description");
		String note = request.getParameter("note");

		scoreService.add(t_course_teaching_class_homework_score_info_id, t_student_id, t_teacher_id, score, description,
				note);

		String pageNo = request.getParameter("pageNo");
		String homeworkShowOrder = request.getParameter("homeworkShowOrder");
		mav.setViewName("redirect:list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id
				+ ".html?pageNo=" + pageNo + "&homeworkShowOrder=" + homeworkShowOrder);

		return mav;

	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatebyjson", method = RequestMethod.POST)
	@ResponseBody
	public int updatebyjson(HttpServletRequest request,
			@RequestBody CourseTeachingClassHomeworkStudentScore courseTeachingClassHomeworkStudentScore) {

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo == null) {

			return 1;
		}

		Teacher teacher = userinfo.getTeacher();
		if (teacher == null) {

			return 2;
		}

		String t_teacher_id = teacher.getId();

		scoreService.add(courseTeachingClassHomeworkStudentScore.getCourseTeachingClassHomeworkScoreInfoId(),
				courseTeachingClassHomeworkStudentScore.getStudentId(), t_teacher_id,
				courseTeachingClassHomeworkStudentScore.getScore(),
				courseTeachingClassHomeworkStudentScore.getDescription(),
				courseTeachingClassHomeworkStudentScore.getNote());

		return 0;

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
			@RequestParam(value = "homeworkShowOrder", required = false) Integer homeworkShowOrder,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		view.addObject(SelectedObjectConst.Selected_PageNo, pageNo);

		homeworkShowOrder = homeworkShowOrder == null ? 0 : homeworkShowOrder;
		view.addObject(SelectedObjectConst.Selected_CourseTeachingClassHomeworkShowOrder, homeworkShowOrder);

		pageSize = getPageSize(request, pageSize);

		// 学生信息
		Page<CourseTeachingClassHomeworkStudentScoresViewData> pagedCourseTeachingClassHomeworkStudentScoresViewData = scoreService
				.getStudentScoresPage(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id,
						homeworkShowOrder, pageNo, pageSize);

		view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkStudentScoresViewData,
				pagedCourseTeachingClassHomeworkStudentScoresViewData);

		List<CourseTeachingClassHomeworkScoreInfoViewData> lstCourseTeachingClassHomeworkScoreInfoViewData = scoreInfoService
				.getAllByOrder(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id, homeworkShowOrder);
		view.addObject(ListObjectConst.List_CourseTeachingClassHomeworkScoreInfoViewData,
				lstCourseTeachingClassHomeworkScoreInfoViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomeworkscore/list");
		return view;
	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/studentlist-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}")
	public ModelAndView studentlist(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@RequestParam(value = "homeworkShowOrder", required = false) Integer homeworkShowOrder,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		view.addObject(SelectedObjectConst.Selected_PageNo, pageNo);

		homeworkShowOrder = homeworkShowOrder == null ? 0 : homeworkShowOrder;
		view.addObject(SelectedObjectConst.Selected_PageNo, pageNo);

		pageSize = getPageSize(request, pageSize);

		UserSessionInfo userinfo = getSessionUser(request);

		String t_student_id = null;

		if (userinfo != null) {
			Student student = userinfo.getStudent();
			if (student != null) {
				t_student_id = student.getId();
			}
		}

		ModelAndView mav = new ModelAndView();

		if (t_student_id == null) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "学生不能为空，请修改.");

			mav.setViewName("redirect:/index.html");
			return mav;

		}

		// 学生信息
		CourseTeachingClassHomeworkStudentScoresViewData selectedCourseTeachingClassHomeworkStudentScoresViewData = scoreService
				.getViewDataByCourseTeachingClassIdAndStudentId(t_course_teaching_class_id,
						t_course_teaching_class_homeworktype_id, t_student_id, homeworkShowOrder);

		view.addObject(SelectedObjectConst.Selected_CourseTeachingClassHomeworkStudentScoresViewData,
				selectedCourseTeachingClassHomeworkStudentScoresViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomeworkscore/studentlist");
		return view;
	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/studentscore-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_student_id}")
	public ModelAndView studentscore(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id, @PathVariable String t_student_id,
			@RequestParam(value = "homeworkShowOrder", required = false) Integer homeworkShowOrder,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;

		view.addObject(SelectedObjectConst.Selected_PageNo, pageNo);

		homeworkShowOrder = homeworkShowOrder == null ? 0 : homeworkShowOrder;
		view.addObject(SelectedObjectConst.Selected_CourseTeachingClassHomeworkShowOrder, homeworkShowOrder);

		pageSize = getPageSize(request, pageSize);

		// 学生信息
		CourseTeachingClassHomeworkStudentScoresViewData selectedCourseTeachingClassHomeworkStudentScoresViewData = scoreService
				.getViewDataByCourseTeachingClassIdAndStudentId(t_course_teaching_class_id,
						t_course_teaching_class_homeworktype_id, t_student_id, homeworkShowOrder);

		view.addObject(SelectedObjectConst.Selected_CourseTeachingClassHomeworkStudentScoresViewData,
				selectedCourseTeachingClassHomeworkStudentScoresViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomeworkscore/studentscore");
		return view;
	}

}
