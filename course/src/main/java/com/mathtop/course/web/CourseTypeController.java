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
import com.mathtop.course.domain.CourseType;
import com.mathtop.course.domain.Simple;
import com.mathtop.course.service.CourseTypeService;

@Controller
@RequestMapping("/coursetype")
public class CourseTypeController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private CourseTypeService coursetypeService;
	
	private final String strPageURI="coursetype";

	private void SetPageURI(ModelAndView mav){
		mav.addObject("pagedURI", strPageURI);
	}
	
	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, CourseType ct) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/coursetype/list");

		Simple dbdata = coursetypeService.getByName(ct.getName());

		if (dbdata == null) {
			return mav;
		} else if (dbdata.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");
			
			String toUrl=("list.html");
			mav.setViewName("redirect:" + toUrl);

		} else {
			coursetypeService.add(ct.getName(), ct.getNote());

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
	@RequestMapping(value = "/DELETE-{ctId}", method = RequestMethod.GET)
	public ModelAndView DELETE(@PathVariable String ctId) {
		if (ctId != null)
			coursetypeService.deleteById(ctId);

		ModelAndView mav = new ModelAndView();
		String toUrl=("list.html");
		mav.setViewName("redirect:" + toUrl);
		SetPageURI(mav);
		return mav;
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/select-{ctIdname}", method = RequestMethod.GET)
	public ModelAndView select(@PathVariable String ctIdname) {
		ModelAndView view = new ModelAndView();
		view.setViewName("coursetype/list");
		
		

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<CourseType> pagedCourseType = coursetypeService.select(ctIdname, pageNo,
					CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_CourseType, pagedCourseType);
			view.setViewName("coursetype/list");
			SetPageURI(view);

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
	public ModelAndView update(HttpServletRequest request, CourseType item) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/coursetype/list");

		CourseType dbitem = coursetypeService.getByName(item.getName());

		if (dbitem == null) {
			return mav;
		} else if (dbitem.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			String toUrl=("list");
			mav.setViewName("redirect:" + toUrl);
			SetPageURI(mav);
			return mav;

		} else {

			coursetypeService.update(item.getId(), item.getName(),
					item.getNote());

			String toUrl=("list");
			mav.setViewName("redirect:" + toUrl);
			SetPageURI(mav);
			return mav;

		}
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
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);
		
		Page<CourseType> pagedCourseType = coursetypeService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_CourseType, pagedCourseType);
		
		view.setViewName("coursetype/list");
		SetPageURI(view);
		return view;
	}
	
	
	//得到所有学院
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<CourseType> getAllSchoolList() {		
		return coursetypeService.getAll();
	}

}
