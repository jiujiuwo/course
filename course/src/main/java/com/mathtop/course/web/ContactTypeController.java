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
import com.mathtop.course.domain.Simple;
import com.mathtop.course.domain.UserContactType;
import com.mathtop.course.service.ContactTypeService;

@Controller
@RequestMapping("/contacttype")
public class ContactTypeController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private ContactTypeService contacttypeService;

	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, UserContactType ct) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/contacttype/list");

		Simple dbdata = contacttypeService.getByName(ct.getName());

		if (dbdata == null) {
			return mav;
		} else if (dbdata.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			Integer pageNo = 1;
			Page<UserContactType> pagedContactType = contacttypeService.getPage(pageNo,
					CommonConstant.PAGE_SIZE);

			mav.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);
			String toUrl=("list.html");
			mav.setViewName("redirect:" + toUrl);

		} else {
			contacttypeService.Add(ct.getName(), ct.getNote());

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
			contacttypeService.DELETE(ctId);

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
	@RequestMapping(value = "/select-{ctIdname}", method = RequestMethod.GET)
	public ModelAndView select(@PathVariable String ctIdname) {
		ModelAndView view = new ModelAndView();
		view.setViewName("contacttype/list");
		
		

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<UserContactType> pagedSchool = contacttypeService.select(ctIdname, pageNo,
					CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_ContactType, pagedSchool);
			view.setViewName("contacttype/list");

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
	public ModelAndView update(HttpServletRequest request, UserContactType item) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/contacttype/list");

		Simple dbitem = contacttypeService.getByName(item.getName());

		if (dbitem == null) {
			return mav;
		} else if (dbitem.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			String toUrl=("list");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		} else {

			contacttypeService.update(item.getId(), item.getName(),
					item.getNote());

			String toUrl=("list");
			mav.setViewName("redirect:" + toUrl);
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
	public ModelAndView ListAll(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();

		pageNo = pageNo == null ? 1 : pageNo;
		Page<UserContactType> pagedContactType = contacttypeService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_ContactType, pagedContactType);
		
		view.setViewName("contacttype/list");
		return view;
	}
	
	
	//得到所有学院
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<UserContactType> getAllSchoolList() {		
		return contacttypeService.getAll();		
	}

}
