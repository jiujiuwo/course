package com.mathtop.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.School;
import com.mathtop.course.service.SchoolService;

@Controller
@RequestMapping("/school")
public class SchoolController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private SchoolService schoolService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, School school) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/school/list");

		School dbschool = schoolService.getByName(school.getName());

		if (dbschool == null) {
			return mav;
		} else if (dbschool.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "学院名已经存在");

			Integer pageNo = 1;
			Page<School> pagedSchool = schoolService.getPage(pageNo,
					CommonConstant.PAGE_SIZE);

			mav.addObject("pagedSchool", pagedSchool);
			String toUrl=("list.html");
			mav.setViewName("redirect:" + toUrl);

		} else {
			schoolService.add(school.getName(), school.getNote());

			String toUrl=("list.html");
			mav.setViewName("redirect:" + toUrl);

		}
		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_school_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(@PathVariable String t_school_id) {
		if (t_school_id != null)
			schoolService.deleteById(t_school_id);

		ModelAndView mav = new ModelAndView();
		String toUrl=("list.html");
		mav.setViewName("redirect:" + toUrl);
		return mav;
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/select-{schoolname}", method = RequestMethod.GET)
	public ModelAndView select(@PathVariable String schoolname) {
		ModelAndView view = new ModelAndView();
		view.setViewName("school/list");
		
		

		if (schoolname != null && schoolname.length() > 0) {

			int pageNo = 1;
			Page<School> pagedSchool = schoolService.select(schoolname, pageNo,
					CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_School, pagedSchool);
			view.setViewName("school/list");

		}
		return view;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, School school) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/school/list");

		School dbschool = schoolService.getByName(school.getName());

		if (dbschool == null) {
			return mav;
		} else if (dbschool.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "学院名已经存在");

			String toUrl=("list");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		} else {

			schoolService.update(school.getId(), school.getName(),
					school.getNote());

			String toUrl=("list");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		}
	}

	/**
	 * 列出全部学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView ListAll(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		Page<School> pagedSchool = schoolService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject("pagedSchool", pagedSchool);
		
		view.setViewName("school/list");
		return view;
	}
	
	
	//得到所有学院
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<School> getAllSchoolList(@RequestParam(value = "pageNo", required = false) Integer pageNo) {		
		
		pageNo = pageNo == null ? 1 : pageNo;
		Page<School> pagedSchool = schoolService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);
		return pagedSchool.getResult();
	}

}
