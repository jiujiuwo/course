package com.mathtop.course.web;

import java.util.List;

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
import com.mathtop.course.dao.UserInfoViewDataDao;
import com.mathtop.course.domain.MailBoxReceivedViewData;
import com.mathtop.course.domain.MailBoxSendViewData;
import com.mathtop.course.domain.StudentViewData;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.UserBasicInfoViewData;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.MailBoxFileName;
import com.mathtop.course.service.MailBoxReceivedService;
import com.mathtop.course.service.MailBoxSendService;
import com.mathtop.course.service.TeachingClassService;

@Controller
@RequestMapping("/mailbox")
public class MailBoxController extends CourseTeachingClassBaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private MailBoxSendService mailboxSendService;

	@Autowired
	private MailBoxReceivedService mailboxReceivedService;

	@Autowired
	TeachingClassService teachingclassService;

	@Autowired
	UserInfoViewDataDao userinfoDao;

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/newmailwithcourse-{t_course_teaching_class_id}")
	public ModelAndView NewMail(HttpServletRequest request, @PathVariable String t_course_teaching_class_id) {
		
		ModelAndView view = new ModelAndView();
		
		setTeacherAndStudentReceiver(request,view);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		view.setViewName("mailbox/add");
		return view;

	}

	/**
	 * 向特定人发送邮件
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/newmailtoother-{t_course_teaching_class_id}-{t_user_id_to}")
	public ModelAndView NewMailToOther(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_user_id_to) {

		
		ModelAndView view = new ModelAndView();
		
		setTeacherAndStudentReceiver(request,view);

		UserBasicInfoViewData selectedMailBoxNewReceiver = userinfoDao.getUserBasicInfoViewDataByt_user_id(t_user_id_to);
		view.addObject(SelectedObjectConst.Selected_MailBoxNewReceiver, selectedMailBoxNewReceiver);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		view.setViewName("mailbox/add");
		return view;

	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/newmail")
	public ModelAndView NewMailWithoutCourse(HttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		setTeacherAndStudentReceiver(request,view);

		view.setViewName("mailbox/add");
		return view;

	}
	
	private void setTeacherAndStudentReceiver(HttpServletRequest request,ModelAndView view){
		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {

			String t_user_id = userinfo.getUser().getId();
			if (t_user_id != null) {

				Page<TeacherViewData> pagedTeacherViewData = teachingclassService.getAddTeacherPageByUserInfo(t_user_id);
				Page<StudentViewData> pagedStudentViewData = teachingclassService.getAddStudentPageByUserInfo(t_user_id);

				view.addObject(PagedObjectConst.Paged_TeacherViewData, pagedTeacherViewData);
				view.addObject(PagedObjectConst.Paged_StudentViewData, pagedStudentViewData);

			}
		}
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addwithcourse-{t_course_teaching_class_id}")
	public ModelAndView add(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@PathVariable String t_course_teaching_class_id, @RequestParam("file") MultipartFile[] files) {

		ModelAndView mav = new ModelAndView();

		AddMail(request, files);

		mav.setViewName("redirect:/mailbox/receivedlistwithcourse-" + t_course_teaching_class_id + ".html");

		return mav;
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView addWithoutCourse(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile[] files) {

		ModelAndView mav = new ModelAndView();

		AddMail(request, files);

		mav.setViewName("redirect:/mailbox/receivedlist" + ".html");

		return mav;
	}

	private void AddMail(HttpServletRequest request, MultipartFile[] files) {
		String receivers = request.getParameter("receivers");
		String subject = request.getParameter("title");
		String content = request.getParameter("content");

		UserSessionInfo userinfo = getSessionUser(request);

		if (userinfo != null) {

			String t_user_id_from = userinfo.getUser().getId();
			if (t_user_id_from != null) {
				String[] receiver = receivers.split(";");
				for (String t_user_id_to : receiver) {

					/**
					 * 注意，文件files仅在Send中处理，其在ReceivedFile中添加纪录
					 */
					List<MailBoxFileName> list = mailboxSendService.Add(request, t_user_id_from, t_user_id_to, subject, content, files);
					mailboxReceivedService.Add(request, t_user_id_from, t_user_id_to, subject, content, list);
				}

			}
		}
	}

	

	/**
	 * 删除收件箱
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deletereceivedwithcourse-{t_course_teaching_class_id}-{t_mail_box_received_id}", method = RequestMethod.GET)
	public ModelAndView deletereceived(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_mail_box_received_id) {

		ModelAndView mav = new ModelAndView();
		mailboxReceivedService.DeleteByID(request, t_mail_box_received_id);
		mav.setViewName("redirect:/mailbox/receivedlistwithcourse-" + t_course_teaching_class_id + ".html");

		return mav;
	}

	/**
	 * 删除收件箱
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deletereceived-{t_mail_box_received_id}", method = RequestMethod.GET)
	public ModelAndView deletereceivedWithoutCourse(HttpServletRequest request, @PathVariable String t_mail_box_received_id) {

		ModelAndView mav = new ModelAndView();
		mailboxReceivedService.DeleteByID(request, t_mail_box_received_id);

		mav.setViewName("redirect:/mailbox/receivedlist" + ".html");

		return mav;
	}

	/**
	 * 删除发件箱
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deletesendwithcourse-{t_course_teaching_class_id}-{t_mail_box_send_id}", method = RequestMethod.GET)
	public ModelAndView deletesend(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_mail_box_send_id) {

		ModelAndView mav = new ModelAndView();
		mailboxSendService.DeleteByID(request, t_mail_box_send_id);
		mav.setViewName("redirect:/mailbox/sendlistwithcourse-" + t_course_teaching_class_id + ".html");

		return mav;
	}

	/**
	 * 删除发件箱
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/deletesend-{t_mail_box_send_id}", method = RequestMethod.GET)
	public ModelAndView deletesendWithoutCourse(HttpServletRequest request, @PathVariable String t_mail_box_send_id) {

		ModelAndView mav = new ModelAndView();
		mailboxSendService.DeleteByID(request, t_mail_box_send_id);

		mav.setViewName("redirect:/mailbox/sendlist" + ".html");

		return mav;
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/receivedcontentwithcourse-{t_course_teaching_class_id}-{t_mail_box_received_id}")
	public ModelAndView receivedcontent(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_mail_box_received_id) {

		ModelAndView view = new ModelAndView();

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		ReceivedContent(view, t_mail_box_received_id);

		view.setViewName("mailbox/receivedcontent");
		return view;

	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/receivedcontent-{t_mail_box_received_id}")
	public ModelAndView receivedcontent(HttpServletRequest request, @PathVariable String t_mail_box_received_id) {

		ModelAndView view = new ModelAndView();

		ReceivedContent(view, t_mail_box_received_id);

		view.setViewName("mailbox/receivedcontent");
		return view;

	}

	private void ReceivedContent(ModelAndView view, String t_mail_box_received_id) {
		MailBoxReceivedViewData selectedMailBoxReceivedViewData = mailboxReceivedService.getMailBoxSendViewDataByID(t_mail_box_received_id);
		view.addObject(SelectedObjectConst.Selected_MailBoxReceivedViewData, selectedMailBoxReceivedViewData);
		
		mailboxReceivedService.SetRead(t_mail_box_received_id);
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sendcontentwithcourse-{t_course_teaching_class_id}-{t_mail_box_send_id}")
	public ModelAndView sendcontent(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@PathVariable String t_mail_box_send_id) {

		ModelAndView view = new ModelAndView();

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		SendContent(view, t_mail_box_send_id);

		view.setViewName("mailbox/sendcontent");
		return view;

	}

	/**
	 * 
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sendcontent-{t_mail_box_send_id}")
	public ModelAndView sendcontent(HttpServletRequest request, @PathVariable String t_mail_box_send_id) {

		ModelAndView view = new ModelAndView();

		SendContent(view, t_mail_box_send_id);

		view.setViewName("mailbox/sendcontent");
		return view;

	}

	private void SendContent(ModelAndView view, String t_mail_box_send_id) {
		MailBoxSendViewData selectedMailBoxSendViewData = mailboxSendService.getMailBoxSendViewDataByID(t_mail_box_send_id);
		view.addObject(SelectedObjectConst.Selected_MailBoxSendViewData, selectedMailBoxSendViewData);
	}

	/**
	 * 列出收件箱列表
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/receivedlistwithcourse-{t_course_teaching_class_id}")
	public ModelAndView receivedlist(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		SetMailBosReceived(request, pageNo, view);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		view.setViewName("mailbox/receivedlist");
		return view;
	}

	/**
	 * 列出收件箱列表
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/receivedlist")
	public ModelAndView receivedlistWithoutCourse(HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		SetMailBosReceived(request, pageNo, view);

		view.setViewName("mailbox/receivedlist");
		return view;
	}

	private void SetMailBosReceived(HttpServletRequest request, Integer pageNo, ModelAndView view) {
		UserSessionInfo userinfo = getSessionUser(request);
		if (userinfo != null) {
			String t_user_id = userinfo.getUser().getId();
			if (t_user_id != null) {

				pageNo = pageNo == null ? 1 : pageNo;
				Page<MailBoxReceivedViewData> pagedMailBoxReceivedViewData = mailboxReceivedService.getPage(t_user_id, pageNo,
						CommonConstant.PAGE_SIZE);

				view.addObject(PagedObjectConst.Paged_MailBoxReceivedViewData, pagedMailBoxReceivedViewData);

			}
		}

	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sendlistwithcourse-{t_course_teaching_class_id}")
	public ModelAndView sendlist(HttpServletRequest request, @PathVariable String t_course_teaching_class_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		SetMailBoxSend(request, pageNo, view);

		// 设置课程基本信息，包括授课、作业类型等
		SetCourseTeachingClassBaseInfo(view, t_course_teaching_class_id);

		view.setViewName("mailbox/sendlist");
		return view;
	}

	/**
	 * 列出全部
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sendlist")
	public ModelAndView sendlistWithoutCourse(HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		SetMailBoxSend(request, pageNo, view);

		view.setViewName("mailbox/sendlist");
		return view;
	}

	private void SetMailBoxSend(HttpServletRequest request, Integer pageNo, ModelAndView view) {
		UserSessionInfo userinfo = getSessionUser(request);
		if (userinfo != null) {
			String t_user_id = userinfo.getUser().getId();
			if (t_user_id != null) {

				Page<MailBoxSendViewData> pagedMailBoxSendViewData = mailboxSendService.getPage(t_user_id, pageNo,
						CommonConstant.PAGE_SIZE);

				view.addObject(PagedObjectConst.Paged_MailBoxSendViewData, pagedMailBoxSendViewData);

			}
		}
	}

}
