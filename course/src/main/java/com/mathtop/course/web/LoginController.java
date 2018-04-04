package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.MailBoxReceivedViewData;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserSessionInfo;
import com.mathtop.course.service.LoginService;
import com.mathtop.course.service.MailBoxReceivedService;
import com.mathtop.course.service.UserService;
import com.mathtop.course.service.UserSessionInfoService;

@Controller
@RequestMapping("/account")
public class LoginController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@Autowired
	UserSessionInfoService usersessioninfoService;

	// 邮箱服务
	@Autowired
	private MailBoxReceivedService mailboxReceivedService;

	@RequestMapping(value = "androidLogin", method = RequestMethod.POST)
	@ResponseBody
	public User androidLogin(HttpServletRequest request, @RequestBody User user) {

	//	System.out.println("androidLogin");
		
		// 如果用户已经登录，则直接进入到主页面
		if (getSessionUser(request) != null) {

			return user;
		}
		
		

		// 对密码进行md5运算，比较两个密码其实是比较两个密码的md5值
		user.EncoderPassword();

		User dbUser = userService.getUserByUserName(user.getUserName());

		if (dbUser == null) {
			
			return null;
		} else if (dbUser.getUserName() == null || !dbUser.getUserName().equals(user.getUserName())) {
			// 用户名不存在
		
			dbUser.setUserName(null);
		} else if (dbUser.getUserPassword() == null || !dbUser.getUserPassword().equals(user.getUserPassword())) {
			// 用户密码不正确
			
			dbUser.setUserPassword(null);
		} else {
			
			// 写入到登录日志中
			loginService.Add(dbUser.getId(), request.getRemoteAddr());
		}
		
		return dbUser;
	}

	/**
	 * 取得当前用户的未读取邮件列表
	 */
	private void SetMailBosReceived(String t_user_id, Integer pageNo, ModelAndView view) {

		if (t_user_id != null) {

			pageNo = pageNo == null ? 1 : pageNo;
			Page<MailBoxReceivedViewData> pagedMailBoxReceivedViewData = mailboxReceivedService.getPageNotRead(t_user_id, pageNo,
					CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_MailBoxReceivedViewData, pagedMailBoxReceivedViewData);

		}

	}

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/doLogin")
	public ModelAndView login(HttpServletRequest request, User user) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/login");
		
		if(user.getUserName()==null || user.getUserPassword()==null)
			return mav;

		UserSessionInfo userinfo = getSessionUser(request);

		
		// 如果用户已经登录，则直接进入到主页面
		if (userinfo != null) {
			
			String t_user_id = userinfo.getUser().getId();
			SetMailBosReceived(t_user_id, 1, mav);
			mav.setViewName("redirect:/index.html");

			return mav;
		}

		
		// 对密码进行md5运算，比较两个密码其实是比较两个密码的md5值
		user.EncoderPassword();

		User dbUser = userService.getUserByUserName(user.getUserName());
		

		if (dbUser == null) {
			mav.addObject(CourseMessage.Message_errorMsg, "用户名不存在");
			return mav;
		} else if (dbUser.getUserName() == null || !dbUser.getUserName().equals(user.getUserName())) {			
			mav.addObject(CourseMessage.Message_errorMsg, "用户名不存在");
		} else if (dbUser.getUserPassword() == null || !dbUser.getUserPassword().equals(user.getUserPassword())) {
			mav.addObject(CourseMessage.Message_errorMsg, "用户密码不正确");
		} else {

			// 写入到登录日志中
			loginService.Add(dbUser.getId(), request.getRemoteAddr());

			setSessionUser(request, usersessioninfoService.getUserSessionInfoByUserId(dbUser.getId()));
			String toUrl = (String) request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
			request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);

			// 如果当前会话中没有保存登录之前的请求URL，则直接跳转到主页
			if (StringUtils.isEmpty(toUrl)) {

				SetMailBosReceived(dbUser.getId(), 1, mav);
				mav.addObject(dbUser);
				mav.setViewName("redirect:/index.html");
			} else
				mav.setViewName("redirect:" + toUrl);
		}
		return mav;
	}

	/**
	 * 登录注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/doLogout")
	public String logout(HttpSession session) {
		session.removeAttribute(CommonConstant.USER_CONTEXT);
		return "redirect:/account/doLogin.html";
	}

	@RequestMapping(value = "/pwd")
	public ModelAndView pwd(HttpServletRequest request, RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/pwd");
		return mav;
	}

	@RequestMapping(value = "/confirmpwd")
	public ModelAndView confirmpwd(HttpServletRequest request, RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/pwd");

		String old_user_password = request.getParameter("old_user_password");
		String new_user_password = request.getParameter("new_user_password");
		String confirm_user_password = request.getParameter("confirm_user_password");

		UserSessionInfo userinfo = getSessionUser(request);
		if (userinfo == null) {
			mav.setViewName("redirect:/account/login");
			return mav;
		}

		User user = new User();
		user.setUserPassword(old_user_password);
		user.EncoderPassword();

		if (!userinfo.getUser().getUserPassword().equals(user.getUserPassword())) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "旧密码错误，请重新输入");
			mav.setViewName("redirect:/account/pwd.html");
			return mav;
		}

		if (!new_user_password.equals(confirm_user_password)) {
			redirectAttributes.addFlashAttribute(CourseMessage.Message_errorMsg, "新密码和确认密码不同，请重新输入");
			mav.setViewName("redirect:/account/pwd.html");
			return mav;
		}

		user.setUserPassword(new_user_password);
		user.EncoderPassword();

		userService.updateUserPassword(userinfo.getUser().getId(), user.getUserPassword());
		userinfo.getUser().setUserPassword(user.getUserPassword());

		mav.setViewName("redirect:/index.html");
		return mav;

	}
}
