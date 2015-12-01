package com.mathtop.course.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingClassForumTopicViewData;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.CourseTeachingClassForumTopicService;
import com.mathtop.course.service.CourseTeachingClassService;

@Controller
@RequestMapping("/courseforum")
public class CourseTeachingClassForumController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingClassForumTopicService forumtopicService;

	@Autowired
	CourseTeachingClassService teachingclassService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addtopic-{t_course_teaching_class_id}")
	public ModelAndView addtopic(HttpServletRequest request, @PathVariable String t_course_teaching_class_id) {

		ModelAndView mav = new ModelAndView();
		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);

		mav.setViewName("courseforum/add");

		return mav;
	}

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_course_teaching_class_id}")
	public ModelAndView add(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@RequestParam("file") MultipartFile[] files) {

		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Date pubdate = new Date();
		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			String t_user_id = userinfo.getUser().getId();
			forumtopicService.add(request, t_course_teaching_class_id, t_user_id, title, content, pubdate, pubdate, files);

		}

		ModelAndView mav = new ModelAndView();
		// mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID,
		// t_course_teaching_class_id);
		mav.setViewName("redirect:/courseforum/list-" + t_course_teaching_class_id + ".html");

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_course_teaching_class_id}-{topicid}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, @PathVariable String t_course_teaching_class_id, @PathVariable String topicid) {
		if (topicid != null)
			forumtopicService.deleteById(request, topicid);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/courseforum/list-" + t_course_teaching_class_id + ".html");

		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}-{t_forum_topic_id}")
	public ModelAndView update(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_forum_topic_id, @RequestParam("file") MultipartFile[] files) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("courseforum/list");

		String updatetitle = request.getParameter("title");
		String updatecontent = request.getParameter("content");

		// System.out.println(updatetopicid);

		String t_user_id = null;

		UserSessionInfo userinfo = getSessionUser(request);
		if (userinfo != null) {
			t_user_id = userinfo.getUser().getId();
		}

		if (t_user_id != null)
			forumtopicService.update(request, t_forum_topic_id, t_user_id, updatetitle, updatecontent, new Date(), files);

		mav.setViewName("redirect:/courseforum/list-" + t_course_teaching_class_id + ".html");

		return mav;

	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatetopic-{t_course_teaching_class_id}-{t_forum_topic_id}")
	public ModelAndView updatetopic(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_forum_topic_id) {
		ModelAndView mav = new ModelAndView();

		CourseTeachingClassForumTopicViewData selectedCourseForumTopicViewData = forumtopicService.getForumTopicViewDataById(t_forum_topic_id);
		mav.addObject(SelectedObjectConst.Selected_CourseForumTopicViewData, selectedCourseForumTopicViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);

		mav.setViewName("courseforum/update");

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
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView mav = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		Page<CourseTeachingClassForumTopicViewData> pagedForumTopicViewData = forumtopicService.getPage(t_course_teaching_class_id, pageNo,
				CommonConstant.PAGE_SIZE);

		mav.addObject(PagedObjectConst.Paged_CourseForumTopicViewData, pagedForumTopicViewData);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(mav, t_course_teaching_class_id);

		mav.setViewName("courseforum/list");
		return mav;
	}

}
