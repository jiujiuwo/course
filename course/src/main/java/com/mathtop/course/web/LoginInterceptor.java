package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.domain.User;


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
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		

		final String[] IGNORE_URI = {"/doLogin.html","doLogin.json"};		
		
		 boolean flag = false;
	        String url = request.getRequestURL().toString();
	   //     System.out.println(">>>: " + url);
	        for (String s : IGNORE_URI) {
	            if (url.contains(s)) {
	                flag = true;
	                break;
	            }
	        }
	        if (!flag) {
	        	User user= (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	            if (user != null) 
	            	flag = true;
	            else
	            	response.sendRedirect(request.getContextPath()+"/doLogin.html");
	        }
	        return flag;
		
	}

}
