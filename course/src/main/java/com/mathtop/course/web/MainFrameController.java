package com.mathtop.course.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainFrameController extends BaseController {

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/index.html")
	public ModelAndView mainframeview() {		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
}
