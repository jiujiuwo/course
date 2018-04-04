package com.mathtop.course.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.domain.UserSessionInfo;

/*
 * 登录拦截，防止用户没有登录直接跳转到相应页面。
 * */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	

	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在多个Interceptor，
	 * 然后SpringMVC会根据声明的前后顺序一个接一个的执行，
	 * 而且所有的Interceptor中的preHandle方法都会在Controller方法调用之前调用。
	 * SpringMVC的这种Interceptor链式结构也是可以进行中断的，
	 * 这种中断方式是令preHandle的返回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		final String[] IGNORE_URI = { "/doLogin.html", "doLogin.json", "androidLogin.json","frequentaccess.html" };

		boolean flag = false;
		String url = request.getRequestURL().toString();
	//	 System.out.println(">>>: " + url);
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			UserSessionInfo user = (UserSessionInfo) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
			if (user != null) {
				flag = true;

				// 防止频繁访问
				HttpSession httpSession = request.getSession();
				Integer nAccessCount = (Integer) httpSession.getAttribute(CommonConstant.ACCESS_COUNT);

				if (nAccessCount == null || nAccessCount == 0) {
					nAccessCount = 0;
					httpSession.setAttribute(CommonConstant.ACCESS_COUNT, nAccessCount + 1);
					httpSession.setAttribute(CommonConstant.LAST_ACCESS_TIME, new Date());
				

				} else if (nAccessCount != null && nAccessCount == -1) {
					// 上次被频繁访问
					Date lastAccessTime = (Date) httpSession.getAttribute(CommonConstant.LAST_ACCESS_TIME);
					Date currentTime = new Date();
					if (lastAccessTime != null && (currentTime.getTime() - lastAccessTime.getTime()) / 1000 > CommonConstant.FREQUENT_ACCESS_WAITING_TIME) {

						httpSession.setAttribute(CommonConstant.ACCESS_COUNT, 0);
						httpSession.setAttribute(CommonConstant.LAST_ACCESS_TIME, new Date());
					}else{
						response.sendRedirect(request.getContextPath() + "/frequentaccess.html");
						return true;
					}

				} else if (nAccessCount < 100) {
					httpSession.setAttribute(CommonConstant.ACCESS_COUNT, nAccessCount + 1);
				} else {

					Date lastAccessTime = (Date) httpSession.getAttribute(CommonConstant.LAST_ACCESS_TIME);
					Date currentTime = new Date();

					httpSession.setAttribute(CommonConstant.ACCESS_COUNT, 0);
					httpSession.setAttribute(CommonConstant.LAST_ACCESS_TIME, new Date());

					if (lastAccessTime != null && (currentTime.getTime() - lastAccessTime.getTime()) / 1000 < 10) {

						httpSession.setAttribute(CommonConstant.ACCESS_COUNT, -1);
						httpSession.setAttribute(CommonConstant.LAST_ACCESS_TIME, new Date());
						response.sendRedirect(request.getContextPath() + "/frequentaccess.html");
						return true;

					}

				}

			} else
				response.sendRedirect(request.getContextPath() + "/account/doLogin.html");
		}
		return flag;

	}

}
