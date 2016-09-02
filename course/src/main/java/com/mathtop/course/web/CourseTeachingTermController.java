package com.mathtop.course.web;

import java.util.Date;
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

import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.CourseTeachingTerm;
import com.mathtop.course.domain.UserContactType;
import com.mathtop.course.service.CourseTeachingTermService;
import com.mathtop.course.utility.DateTimeSql;

@Controller
@RequestMapping("/courseteachingterm")
public class CourseTeachingTermController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTeachingTermService courseTeachingTermService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/teachingterm/list");

		
		int teaching_year_begin=Integer.parseInt(request.getParameter("teaching_year_begin"));
		int teaching_year_end=Integer.parseInt(request.getParameter("teaching_year_end"));
		int teaching_term=Integer.parseInt(request.getParameter("teaching_term"));
		int weeks=Integer.parseInt(request.getParameter("weeks"));
		String strweek_begin=request.getParameter("week_begin");
		Date week_begin=DateTimeSql.GetDate(strweek_begin);

		if (courseTeachingTermService.isTermExist(teaching_year_begin, teaching_year_end, teaching_term)) {
			mav.addObject(CourseMessage.Message_errorMsg, "学期已经存在");
			
			String toUrl=("list.html");
			mav.setViewName("redirect:" + toUrl);

		} else {
			courseTeachingTermService.add(teaching_year_begin, teaching_year_end, teaching_term, weeks, week_begin);

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
	@RequestMapping(value = "/delete-{ctId}", method = RequestMethod.GET)
	public ModelAndView DELETE(@PathVariable String ctId) {
		if (ctId != null)
			courseTeachingTermService.deleteById(ctId);

	
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
	@RequestMapping(value = "/select-{teaching_year_begin}", method = RequestMethod.GET)
	public ModelAndView select(@PathVariable int teaching_year_begin) {
		ModelAndView view = new ModelAndView();
		view.setViewName("teachingterm/list");
		
		

			

		
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
	public ModelAndView update(HttpServletRequest request, UserContactType item) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/teachingterm/list");
		String id=request.getParameter("id");

		int teaching_year_begin=Integer.parseInt(request.getParameter("teaching_year_begin"));
		int teaching_year_end=Integer.parseInt(request.getParameter("teaching_year_end"));
		int teaching_term=Integer.parseInt(request.getParameter("teaching_term"));
		int weeks=Integer.parseInt(request.getParameter("weeks"));
		String strweek_begin=request.getParameter("week_begin");
		Date week_begin=DateTimeSql.GetDate(strweek_begin);

	

		courseTeachingTermService.update(id, teaching_year_begin, teaching_year_end, teaching_term, weeks, week_begin);

			String toUrl=("list");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		
	}

	/**
	 * 列出全部联系方式类型
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView ListAll(HttpServletRequest request, 
			@RequestParam(value = "pageNo", required = false) Integer pageNo,@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize=getPageSize(request,pageSize);
		
		Page<CourseTeachingTerm> pagedCourseTeachingTerm = courseTeachingTermService.getPage(pageNo,
				pageSize);

		view.addObject(PagedObjectConst.Paged_CourseTeachingTerm, pagedCourseTeachingTerm);
		
		view.setViewName("teachingterm/list");
		return view;
	}
	
	
	//得到所有学期
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<CourseTeachingTerm> getAllSchoolList() {		
		return courseTeachingTermService.getAll();		
	}

}
