package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.domain.UserSessionInfo;

public class BaseController {
	protected static final String ERROR_MSG_KEY = CourseMessage.Message_errorMsg;

	/**
	 * 获取保存在Session中的用户对象
	 * 
	 * @param request
	 * @return
	 */
	protected UserSessionInfo getSessionUser(HttpServletRequest request) {
		return (UserSessionInfo) request.getSession().getAttribute(
				CommonConstant.USER_CONTEXT);
	}
	
	

	/**
	 * 保存用户对象到Session中
	 * 
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(HttpServletRequest request, UserSessionInfo user) {
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
	}

	
	
	
	
	/**
	 * 获取基于应用程序的url绝对路径
	 * 
	 * @param request
	 * @param url
	 *            以"/"打头的URL地址
	 * @return 基于应用程序的url绝对路径
	 */
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
}
