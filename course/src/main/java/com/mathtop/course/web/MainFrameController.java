package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.MailBoxReceivedService;

@Controller
@RequestMapping("/")
public class MainFrameController extends BaseController {
	@Autowired
	private MailBoxReceivedService mailboxReceivedService;
	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/index.html")
	public ModelAndView mainframeview(HttpServletRequest request) {		
		
		ModelAndView mav = new ModelAndView();
		
		UserSessionInfo userinfo = getSessionUser(request);
		
		String t_user_id="";

		if (userinfo != null) {
			t_user_id=userinfo.getUser().getId();
		}
		
		boolean hasMailNotReaded=mailboxReceivedService.hasMailNotReaded(t_user_id);
		mav.addObject(SelectedObjectConst.Selected_MailBoxNewMail, hasMailNotReaded);
		
		
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping(value = "/frequentaccess.html")
	public ModelAndView frequentaccess(HttpServletRequest request) {	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/frequentaccess");
		return mav;
	}
}
