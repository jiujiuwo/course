package com.mathtop.course.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.mathtop.course.domain.CourseTeachingClassForumReplyViewData;
import com.mathtop.course.domain.CourseTeachingClassForumTopicViewData;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.CourseTeachingClassForumReplyService;
import com.mathtop.course.service.CourseTeachingClassForumTopicService;
import com.mathtop.course.service.CourseTeachingClassService;

@Controller
@RequestMapping("/courseforumreply")
public class CourseTeachingClassForumReplyController extends CourseTeachingClassBaseController {


	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingClassForumReplyService forumreplyService;
	
	@Autowired
	private CourseTeachingClassForumTopicService forumtopicService;

	@Autowired
	CourseTeachingClassService teachingclassService;

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addreply-{t_course_teaching_class_id}-{t_forum_topic_id}")
	public ModelAndView addreply(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_forum_topic_id) {
		ModelAndView view = new ModelAndView();

		CourseTeachingClassForumTopicViewData selectedCourseForumTopicViewData = forumtopicService.getForumTopicViewDataById(t_forum_topic_id);
		view.addObject(SelectedObjectConst.Selected_CourseForumTopicViewData, selectedCourseForumTopicViewData);

		view.addObject(SelectedObjectConst.Selected_CourseForumTopicID, t_forum_topic_id);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		view.setViewName("courseforum/reply");
		return view;
	}

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add-{t_course_teaching_class_id}-{t_forum_topic_id}")
	public ModelAndView add(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_forum_topic_id, @RequestParam("file") MultipartFile[] files) {

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Date pubdate = new Date();
		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {
			String t_user_id = userinfo.getUser().getId();
			forumreplyService.add(request,t_forum_topic_id, t_user_id, title, content, pubdate,files);

		}

		ModelAndView mav = new ModelAndView();
		// mav.addObject(SelectedObjectConst.Selected_CourseTeachingClassID,
		// t_course_teaching_class_id);
		mav.setViewName("redirect:/courseforumreply/list-" + t_course_teaching_class_id + "-" + t_forum_topic_id + ".html");

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_course_teaching_class_id}-{t_forum_topic_id}-{t_reply_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_forum_topic_id, @PathVariable String t_reply_id) {
		if (t_reply_id != null)
			forumreplyService.deleteById(request,t_reply_id);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("redirect:/courseforumreply/list-" + t_course_teaching_class_id + "-" + t_forum_topic_id + ".html");

		return mav;
	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updatereply-{t_course_teaching_class_id}-{t_forum_topic_id}-{t_forum_reply_id}")
	public ModelAndView updatereply(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_forum_topic_id,@PathVariable String t_forum_reply_id) {
		ModelAndView view = new ModelAndView();

		CourseTeachingClassForumTopicViewData selectedCourseForumTopicViewData = forumtopicService.getForumTopicViewDataById(t_forum_topic_id);
		view.addObject(SelectedObjectConst.Selected_CourseForumTopicViewData, selectedCourseForumTopicViewData);

		CourseTeachingClassForumReplyViewData selectedCourseReplyTopicViewData=forumreplyService.getForumReplyViewDataByID(t_forum_reply_id);
		view.addObject(SelectedObjectConst.Selected_CourseForumReplyViewData, selectedCourseReplyTopicViewData);
		
		view.addObject(SelectedObjectConst.Selected_CourseForumTopicID, t_forum_topic_id);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		view.setViewName("courseforum/replyupdate");
		return view;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update-{t_course_teaching_class_id}-{t_forum_topic_id}-{t_forum_reply_id}")
	public ModelAndView update(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_forum_topic_id,@PathVariable String t_forum_reply_id, @RequestParam("file") MultipartFile[] files) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("courseforumreply/list");

	
		String updatetitle = request.getParameter("title");
		String updatecontent = request.getParameter("content");

		// System.out.println(updatetopicid);

		String t_user_id = null;

		UserSessionInfo userinfo = getSessionUser(request);
		if (userinfo != null) {
			t_user_id = userinfo.getUser().getId();
		}

		if (t_user_id != null)
			forumreplyService.update(request,t_forum_reply_id, t_user_id, updatetitle, updatecontent, new Date(),files);

		mav.setViewName("redirect:/courseforumreply/list-" + t_course_teaching_class_id + "-" + t_forum_topic_id + ".html");

		return mav;

	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list-{t_course_teaching_class_id}-{t_forum_topic_id}")
	public ModelAndView ListAll(HttpServletRequest request,HttpSession httpSession, RedirectAttributes redirectAttributes,@PathVariable String t_course_teaching_class_id,
			@PathVariable String t_forum_topic_id, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		
		
		

		pageNo = pageNo == null ? 1 : pageNo;
		Page<CourseTeachingClassForumReplyViewData> pagedForumReplyViewData = forumreplyService.getPage(t_forum_topic_id, pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_CourseForumReplyViewData, pagedForumReplyViewData);

		CourseTeachingClassForumTopicViewData selectedCourseForumTopicViewData = forumtopicService.getForumTopicViewDataById(t_forum_topic_id);
		view.addObject(SelectedObjectConst.Selected_CourseForumTopicViewData, selectedCourseForumTopicViewData);

		view.addObject(SelectedObjectConst.Selected_CourseForumTopicID, t_forum_topic_id);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		view.setViewName("courseforum/replylist");
		return view;
	}

}
