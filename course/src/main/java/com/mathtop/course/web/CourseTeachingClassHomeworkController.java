package com.mathtop.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoStudentViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkReplyViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkStatisticsViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkSubmitBaseinfoViewData;
import com.mathtop.course.domain.CourseTeachingClassHomeworkType;
import com.mathtop.course.domain.StatisticsData;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.CourseTeachingClassHomeworkReplyService;
import com.mathtop.course.service.CourseTeachingClassHomeworkService;
import com.mathtop.course.service.CourseTeachingClassHomeworkSubmitService;
import com.mathtop.course.service.CourseTeachingClassHomeworkTypeService;
import com.mathtop.course.service.FileNameFormatParser;
import com.mathtop.course.service.TeachingClassService;
import com.mathtop.course.service.chartDataValue;

@Controller
@RequestMapping("/coursehomework")
public class CourseTeachingClassHomeworkController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingClassHomeworkService homeworkService;

	@Autowired
	private CourseTeachingClassHomeworkTypeService homeworktypeService;

	@Autowired
	TeachingClassService teachingclassService;

	@Autowired
	private CourseTeachingClassHomeworkSubmitService homeworkSubmitService;

	@Autowired
	FileNameFormatParser filenameformatparser;

	@Autowired
	private CourseTeachingClassHomeworkReplyService replyService;

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addhomework-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}")
	public ModelAndView addhomework(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_homeworktype_id) {

		ModelAndView view = new ModelAndView();

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomework/new");
		return view;
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}")
	public ModelAndView add(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_homeworktype_id,
			@RequestParam("file") MultipartFile[] files) {

		ModelAndView mav = new ModelAndView();

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String enddate = request.getParameter("enddate");
		String filetype = "*.*";
		String filenameformat = "{*}";
		String filecount = request.getParameter("filecount");
		Integer nfilecount = Integer.parseInt(filecount);

		if (nfilecount != 0) {

			String filetyperadio = request.getParameter("filetyperadio");

			if (filetyperadio.equals("0")) {

				/**
				 * 文件类型 正确格式为*.doc;*.docx;*.txt
				 */
				String[] filetypes = request.getParameterValues("filetype");
				filetype = "";
				for (String s : filetypes) {
					filetype += s;
				}

				// 用户自定义格式
				String filetypecustom = request.getParameter("filetypecustom");
				String[] typearray = filetypecustom.split(";");
				for (String s : typearray) {
					String[] t = s.split(".");
					if (t.length == 2) {
						if (filetype.length() > 1)
							filetype += (";" + s);
						else
							filetype = s;
					}
				}
			} else {
				// 文件类型不限制

			}

			/**
			 * 文件名称格式 文件格式:{}{}等格式
			 */
			String filenameformatradio = request.getParameter("filenameformatradio");

			if (filenameformatradio.equals("0")) {

				filenameformat = request.getParameter("filenameformat");

			} else {
				// 自定义格式
				String filenameformatcustom = request.getParameter("filenameformatcustom");
				FileNameFormatParser ffp = new FileNameFormatParser();
				if (ffp.IsFileNameFormatRight(filenameformatcustom))
					filenameformat = filenameformatcustom;
				else {

					redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "文件名称格式不对，请修改.");

					mav.setViewName("redirect:/coursehomework/list-" + t_course_teaching_class_id + "-"
							+ t_course_teaching_class_homeworktype_id + ".html");
					return mav;
				}

			}

		}

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null) {

				homeworkService.Add(request, t_course_teaching_class_id, t_course_teaching_class_homeworktype_id, teacher.getId(), filetype,
						filenameformat, nfilecount, title, content, enddate, files);

			}
		}

		// mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID,
		// t_course_teaching_class_id);
		mav.setViewName(
				"redirect:/coursehomework/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id + ".html");

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id) {

		homeworkService.DeleteByID(request, t_course_teaching_class_homework_baseinfo_id);

		ModelAndView mav = new ModelAndView();

		mav.setViewName(
				"redirect:/coursehomework/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id + ".html");

		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatehomework-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}")
	public ModelAndView updatehomework(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id) {

		ModelAndView view = new ModelAndView();
		
		// 作业基本信息
				CourseTeachingClassHomeworkBaseinfoViewData selectedCourseHomeworkBasicInfoViewData = homeworkService
						.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_homework_baseinfo_id);

				view.addObject(SelectedObjectConst.Selected_CourseHomeworkBasicInfoViewData, selectedCourseHomeworkBasicInfoViewData);
		

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomework/update");
		return view;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}")
	public ModelAndView update(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id, @RequestParam("file") MultipartFile[] files) {
		ModelAndView mav = new ModelAndView();

		String updatetitle = request.getParameter("updatetitle");
		String updatecontent = request.getParameter("updatecontent");
	
		String updateenddate = request.getParameter("enddate");

		String filetype = "*.*";
		String filenameformat = "{*}";
		String filecount = request.getParameter("filecount");
		Integer nfilecount = Integer.parseInt(filecount);

		if (nfilecount != 0) {

			String filetyperadio = request.getParameter("filetyperadio");

			if (filetyperadio.equals("0")) {

				/**
				 * 文件类型 正确格式为*.doc;*.docx;*.txt
				 */
				String[] filetypes = request.getParameterValues("filetype");
				filetype = "";
				for (String s : filetypes) {
					filetype += s;
				}

				// 用户自定义格式
				String filetypecustom = request.getParameter("filetypecustom");
				String[] typearray = filetypecustom.split(";");
				for (String s : typearray) {
					String[] t = s.split(".");
					if (t.length == 2) {
						if (filetype.length() > 1)
							filetype += (";" + s);
						else
							filetype = s;
					}
				}
			} else {
				// 文件类型不限制

			}

			/**
			 * 文件名称格式 文件格式:{}{}等格式
			 */
			String filenameformatradio = request.getParameter("filenameformatradio");

			if (filenameformatradio.equals("0")) {

				filenameformat = request.getParameter("filenameformat");

			} else {
				// 自定义格式
				String filenameformatcustom = request.getParameter("filenameformatcustom");
				FileNameFormatParser ffp = new FileNameFormatParser();
				if (ffp.IsFileNameFormatRight(filenameformatcustom))
					filenameformat = filenameformatcustom;
				else {

					redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "文件名称格式不对，请修改.");
					// mav.addObject(CourseMessage.Message_errorMsg,
					// "不存在自然班，请先添加自然班才能够添加学生.");
					mav.setViewName("redirect:/coursehomework/list-" + t_course_teaching_class_id + "-"
							+ t_course_teaching_class_homeworktype_id + ".html");
					return mav;
				}

			}

		}

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null) {

				homeworkService.Update(request, t_course_teaching_class_homework_baseinfo_id, teacher.getId(), filetype, filenameformat,
						nfilecount, updatetitle, updatecontent, updateenddate, files);

			}
		}

		mav.setViewName(
				"redirect:/coursehomework/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id + ".html");

		return mav;

	}
	
	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/simpleupdatehomework-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}")
	public ModelAndView simpleupdatehomework(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id) {

		ModelAndView view = new ModelAndView();
		
		// 作业基本信息
				CourseTeachingClassHomeworkBaseinfoViewData selectedCourseHomeworkBasicInfoViewData = homeworkService
						.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_homework_baseinfo_id);

				view.addObject(SelectedObjectConst.Selected_CourseHomeworkBasicInfoViewData, selectedCourseHomeworkBasicInfoViewData);
		

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomework/simpleupdate");
		return view;
	}


	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatesimple-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}")
	public ModelAndView updatesimple(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id) {
		ModelAndView mav = new ModelAndView();

		String updatetitle = request.getParameter("updatetitle");
		String updatecontent = request.getParameter("updatecontent");

		String updateenddate = request.getParameter("enddate");

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null) {

				homeworkService.Update(request, t_course_teaching_class_homework_baseinfo_id, teacher.getId(), updatetitle, updatecontent,
						updateenddate);

			}
		}

		mav.setViewName(
				"redirect:/coursehomework/list-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id + ".html");

		return mav;

	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/replyadd-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_submit_baseinfo_id}-{t_course_teaching_class_homework_baseinfo_id}-{t_student_id}")
	public ModelAndView replyadd(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_submit_baseinfo_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id, @PathVariable String t_student_id,
			@RequestParam("file") MultipartFile[] files) {

		ModelAndView mav = new ModelAndView();

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null) {

				replyService.Add(request, t_course_teaching_class_homework_submit_baseinfo_id, teacher.getId(), title, content, files);

			}
		}

		mav.setViewName("redirect:/coursehomework/reply-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id + "-"
				+ t_course_teaching_class_homework_baseinfo_id + "-" + t_student_id + ".html");

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/replydelete-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_reply_id}-{t_course_teaching_class_homework_baseinfo_id}-{t_student_id}", method = RequestMethod.GET)
	public ModelAndView replydelete(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id, @PathVariable String t_course_teaching_class_homework_reply_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id, @PathVariable String t_student_id) {

		replyService.DeleteByID(request, t_course_teaching_class_homework_reply_id);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/coursehomework/reply-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id + "-"
				+ t_course_teaching_class_homework_baseinfo_id + "-" + t_student_id + ".html");

		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/replyupdate-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}-{t_student_id}")
	public ModelAndView replyupdate(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id, @PathVariable String t_student_id,
			@RequestParam("file") MultipartFile[] files) {
		ModelAndView mav = new ModelAndView();

		String t_course_teaching_class_homework_reply_id = request.getParameter("id");
		String updatetitle = request.getParameter("updatetitle");
		String updatecontent = request.getParameter("updatecontent");

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null) {

				replyService.Update(request, t_course_teaching_class_homework_reply_id, teacher.getId(), updatetitle, updatecontent, files);

			}
		}

		mav.setViewName("redirect:/coursehomework/reply-" + t_course_teaching_class_id + "-" + t_course_teaching_class_homeworktype_id + "-"
				+ t_course_teaching_class_homework_baseinfo_id + "-" + t_student_id + ".html");

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
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		UserSessionInfo userinfo = getSessionUser(request);
		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null){
				//作业信息
				Page<CourseTeachingClassHomeworkBaseinfoViewData> pagedCourseTeachingClassHomeworkBaseinfoViewData = homeworkService
						.getPage(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id, pageNo, CommonConstant.PAGE_SIZE);

				view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkBaseinfoViewData,
						pagedCourseTeachingClassHomeworkBaseinfoViewData);
				
				view.setViewName("coursehomework/list");
			}
			else {
				Student student=userinfo.getStudent();
				if(student!=null){
					String t_student_id=student.getId();
				
				//作业信息
				Page<CourseTeachingClassHomeworkBaseinfoStudentViewData> pagedCourseTeachingClassHomeworkBaseinfoViewData = homeworkService
						.getPage(t_course_teaching_class_id, t_course_teaching_class_homeworktype_id,t_student_id, pageNo, CommonConstant.PAGE_SIZE);
				
				

				view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkBaseinfoViewData,
						pagedCourseTeachingClassHomeworkBaseinfoViewData);

				view.setViewName("coursehomework/studentshowlist");
				}
			}
		} else
			view.setViewName("coursehomework/list");
		return view;
	}

	/**
	 * 统计
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/statistics-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}", method = RequestMethod.GET)
	public ModelAndView statistics(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id) {

		ModelAndView view = new ModelAndView();

		// 作业基本信息
		CourseTeachingClassHomeworkBaseinfoViewData selectedCourseHomeworkBasicInfoViewData = homeworkService
				.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_homework_baseinfo_id);

		view.addObject(SelectedObjectConst.Selected_CourseHomeworkBasicInfoViewData, selectedCourseHomeworkBasicInfoViewData);

		// 作业提交统计数据
		StatisticsData selectedCourseHomeworkStatisticsData = homeworkSubmitService
				.getStatisticsData(t_course_teaching_class_homework_baseinfo_id);

		view.addObject(SelectedObjectConst.Selected_CourseHomeworkStatisticsData, selectedCourseHomeworkStatisticsData);

		Page<CourseTeachingClassHomeworkStatisticsViewData> pagedCourseTeachingClassHomeworkStatisticsViewData = homeworkSubmitService
				.getPageofStatisticsViewData(t_course_teaching_class_homework_baseinfo_id, 1, 1);
		view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkStatisticsViewData,
				pagedCourseTeachingClassHomeworkStatisticsViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomework/statistics");

		return view;
	}

	/**
	 * 批复列表
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/replylist-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}", method = RequestMethod.GET)
	public ModelAndView replylist(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id, @PathVariable String t_course_teaching_class_homework_baseinfo_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = pageNo == null ? 1 : pageNo;
		
		

		ModelAndView view = new ModelAndView();

		// 作业基本信息
		CourseTeachingClassHomeworkBaseinfoViewData selectedCourseHomeworkBasicInfoViewData = homeworkService
				.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_homework_baseinfo_id);

		view.addObject(SelectedObjectConst.Selected_CourseHomeworkBasicInfoViewData, selectedCourseHomeworkBasicInfoViewData);

		// 作业提交
		Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData> pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData = homeworkSubmitService
				.getPage(t_course_teaching_class_homework_baseinfo_id, pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkSubmitBaseinfoViewData,
				pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomework/replylist");

		return view;
	}

	/**
	 * 批复
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/reply-{t_course_teaching_class_id}-{t_course_teaching_class_homeworktype_id}-{t_course_teaching_class_homework_baseinfo_id}-{t_student_id}", method = RequestMethod.GET)
	public ModelAndView reply(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_course_teaching_class_homeworktype_id, @PathVariable String t_course_teaching_class_homework_baseinfo_id,
			@PathVariable String t_student_id) {

		ModelAndView view = new ModelAndView();

		// 作业基本信息
		CourseTeachingClassHomeworkBaseinfoViewData selectedCourseHomeworkBasicInfoViewData = homeworkService
				.getCourseTeachingClassHomeworkBaseinfoViewDataByID(t_course_teaching_class_homework_baseinfo_id);

		view.addObject(SelectedObjectConst.Selected_CourseHomeworkBasicInfoViewData, selectedCourseHomeworkBasicInfoViewData);

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			Teacher teacher = userinfo.getTeacher();
			if (teacher != null) {

				// 作业提交
				Page<CourseTeachingClassHomeworkSubmitBaseinfoViewData> pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData = homeworkSubmitService
						.getPage(t_course_teaching_class_homework_baseinfo_id, t_student_id, 1, CommonConstant.PAGE_SIZE);

				view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkSubmitBaseinfoViewData,
						pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData);

				if (pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData.getTotalCount() > 0) {
					String t_course_teaching_class_homework_submit_baseinfo_id = pagedCourseTeachingClassHomeworkSubmitBaseinfoViewData
							.getResult().get(0).getHomeworksubmitbaseinfo().getId();

					// 作业批复
					Page<CourseTeachingClassHomeworkReplyViewData> pagedCourseTeachingClassHomeworkReplyBaseinfoViewData = replyService
							.getPage(t_course_teaching_class_homework_submit_baseinfo_id, t_student_id, 1, CommonConstant.PAGE_SIZE);

					view.addObject(PagedObjectConst.Paged_CourseTeachingClassHomeworkReplyBaseinfoViewData,
							pagedCourseTeachingClassHomeworkReplyBaseinfoViewData);
				}

				view.addObject(SelectedObjectConst.Selected_Student_ID, t_student_id);

			}
		}

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		// 课程类型
		CourseTeachingClassHomeworkType selectedCourseHomeworkTypeData = homeworktypeService
				.getByID(t_course_teaching_class_homeworktype_id);
		view.addObject(SelectedObjectConst.Selected_CourseHomeworkTypeData, selectedCourseHomeworkTypeData);

		view.setViewName("coursehomework/reply");
		return view;
	}

	@RequestMapping(value = "/getRightExampleFileName-{t_course_teaching_class_homework_baseinfo_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getRightExampleFileName(HttpServletRequest request,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id) {

		UserSessionInfo userinfo = getSessionUser(request);
		if (userinfo != null) {
			Student student = userinfo.getStudent();
			if (student != null) {

				return filenameformatparser.getRightExampleFileName(t_course_teaching_class_homework_baseinfo_id, student.getId());
			}
		}

		return null;
	}

	@RequestMapping(value = "/getRightExampleFileNameEx-{t_course_teaching_class_homework_baseinfo_id}-{t_student_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getRightExampleFileName(HttpServletRequest request,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id, @PathVariable String t_student_id) {

		return filenameformatparser.getRightExampleFileName(t_course_teaching_class_homework_baseinfo_id, t_student_id);

	}

	@RequestMapping(value = "/getchartdatavalue-{t_course_teaching_class_homework_baseinfo_id}", method = RequestMethod.GET)
	@ResponseBody
	public List<chartDataValue> getSubmitChartDataValue(HttpServletRequest request,
			@PathVariable String t_course_teaching_class_homework_baseinfo_id) {

		return homeworkSubmitService.getSubmitChartDataValue(t_course_teaching_class_homework_baseinfo_id);

	}

}
