package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/permission")
public class PermissionrController extends BaseController {

	

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/")
	public ModelAndView add(HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		
		
		mav.setViewName("redirect:/group/list.html");
		return mav;

	}

	
}
