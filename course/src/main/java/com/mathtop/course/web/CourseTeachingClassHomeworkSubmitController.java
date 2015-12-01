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
import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReplyViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfo;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.CourseTeachingClassHomeworkReplyService;
import com.mathtop.course.service.CourseTeachingClassHomeworkService;
import com.mathtop.course.service.CourseTeachingClassHomeworkSubmitService;
import com.mathtop.course.service.CourseTeachingClassHomeworkTypeService;
import com.mathtop.course.service.FileNameFormatParser;

@Controller
@RequestMapping("/coursehomeworksubmit")
public class CourseTeachingClassHomeworkSubmitController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingClassHomeworkSubmitService homeworkSubmitService;

	@Autowired
	private CourseTeachingClassHomeworkService homeworkService;

	@Autowired
	private CourseTeachingClassHomeworkReplyService replyService;

	@Autowired
	private CourseTeachingClassHomeworkTypeService homeworktypeService;

	@Autowired
	FileNameFormatParser filenameformatparser;

	/**
	 * 学生提交作业
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}")
	public ModelAndView add(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id, @RequestParam("file") MultipartFile[] files) {

		ModelAndView mav = new ModelAndView();

		UserSessionInfo userinfo = getSessionUser(request);
		String t_student_id = null;

		if (userinfo != null) {
			Student student = userinfo.getStudent();
			if (student != null) {
				t_student_id = student.getId();
			}
		}

		if (t_student_id == null) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "学生不能为空，请修改.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + t_course_teaching_class_homework_baseinfo_id + ".html");
			return mav;

		}

		if (!filenameformatparser.IsSubmitFileContentIsNotNull(request, files)) {

			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "文件内容为空，请修改.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + t_course_teaching_class_homework_baseinfo_id + ".html");
			return mav;
		}

		if (!filenameformatparser.IsSubmitFileCountRight(request, files, t_course_teaching_class_homework_baseinfo_id)) {

			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "文件个数不符合要求，请修改.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + t_course_teaching_class_homework_baseinfo_id + ".html");
			return mav;
		}

		if (!filenameformatparser.IsSubmitFileNameFormatRight(request, files, t_course_teaching_class_homework_baseinfo_id, t_student_id)) {

			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "文件名称不符合要求，请修改.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + t_course_teaching_class_homework_baseinfo_id + ".html");
			return mav;
		}

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		if (userinfo != null) {
			Student student = userinfo.getStudent();
			if (student != null) {

				homeworkSubmitService.add(request, t_course_teaching_class_homework_baseinfo_id, t_student_id, title, content, files);

			}
		}

		// mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID,
		// t_course_teaching_class_id);
		mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id
				+ "-" + t_course_teaching_class_homework_baseinfo_id + ".html");

		return mav;

	}
	
	

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_submit_baseinfo_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_submit_baseinfo_id) {

		CourseTeachingClassHomeworkSubmitBaseinfo submitbaseinfo = homeworkSubmitService
				.getByID(t_course_teaching_class_homework_submit_baseinfo_id);

		homeworkSubmitService.deleteByID(request, t_course_teaching_class_homework_submit_baseinfo_id);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id
				+ "-" + submitbaseinfo.getT_course_teaching_class_homework_baseinfo_id() + ".html");

		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_submit_baseinfo_id}")
	public ModelAndView update(HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_submit_baseinfo_id, @RequestParam("file") MultipartFile[] files) {

		String updatetitle = request.getParameter("updatetitle");
		String updatecontent = request.getParameter("updatecontent");

		CourseTeachingClassHomeworkSubmitBaseinfo submitbaseinfo = homeworkSubmitService
				.getByID(t_course_teaching_class_homework_submit_baseinfo_id);

		UserSessionInfo userinfo = getSessionUser(request);

		
		
		String t_student_id = null;
		String t_course_teaching_class_homework_baseinfo_id=submitbaseinfo.getT_course_teaching_class_homework_baseinfo_id();

		if (userinfo != null) {
			Student student = userinfo.getStudent();
			if (student != null) {
				t_student_id = student.getId();
			}
		}
		
		ModelAndView mav = new ModelAndView();
		
		if (t_student_id == null) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "学生不能为空，请修改.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + t_course_teaching_class_homework_baseinfo_id + ".html");
			return mav;

		}

		if (!filenameformatparser.IsSubmitFileContentIsNotNull(request, files)) {

			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "文件内容为空，请修改.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + t_course_teaching_class_homework_baseinfo_id + ".html");
			return mav;
		}

		if (!filenameformatparser.IsSubmitFileCountRight(request, files, t_course_teaching_class_homework_baseinfo_id)) {

			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "文件个数不符合要求，请修改.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + t_course_teaching_class_homework_baseinfo_id + ".html");
			return mav;
		}

		if (!filenameformatparser.IsSubmitFileNameFormatRight(request, files, t_course_teaching_class_homework_baseinfo_id, t_student_id)) {

			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "文件名称不符合要求，请修改.");

			mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-"
					+ t_course_teaching_class_homeworktype_id + "-" + t_course_teaching_class_homework_baseinfo_id + ".html");
			return mav;
		}
		
		homeworkSubmitService.update(request, t_course_teaching_class_homework_submit_baseinfo_id, t_student_id, updatetitle,
				updatecontent, files);
		
		
		mav.setViewName("redirect:/coursehomeworksubmit/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id
				+ "-" + submitbaseinfo.getT_course_teaching_class_homework_baseinfo_id() + ".html");

		return mav;

	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}")
	public ModelAndView ListAll(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id, @PathVariable String t_course_teaching_class_homework_baseinfo_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();

		// 作业基本信息
		CourseTeachingClassHomeworkBaseinfoViewData selectedCourseHomeworkBasicInfoViewData = homeworkService
				.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_homework_baseinfo_id);

		view.addObject(SelectedObjectConst.Selected_CourseHomeworkBasicInfoViewData, selectedCourseHomeworkBasicInfoViewData);

		pageNo = pageNo == null ? 1 : pageNo;

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Student student = userinfo.getStudent();
			if (student != null) {
				String t_student_id = student.getId();

				// 作业提交
				Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData> pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData = homeworkSubmitService
						.getPage(t_course_teaching_class_homework_baseinfo_id, t_student_id, pageNo, CommonConstant.PAGE_SIZE);

				view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkSubmitBaseinfoViewData,
						pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData);

				if (pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.getTotalCount() > 0) {
					String t_course_teaching_class_homework_submit_baseinfo_id = pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData
							.getResult().get(0).getHomeworksubmitbaseinfo().getId();

					// 作业批复
					Page<CourseTeachingClassHomeworkReplyViewData> pagedCourseTeachingClassHomeworkReplyBaseinfoViewData = replyService
							.getPage(t_course_teaching_class_homework_submit_baseinfo_id, t_student_id, pageNo, CommonConstant.PAGE_SIZE);

					view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkReplyBaseinfoViewData,
							pagedCourseTeachingClassHomeworkReplyBaseinfoViewData);
				}

			}
		}

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomework/studentsubmitlist");
		return view;
	}

}
