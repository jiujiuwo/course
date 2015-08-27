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
import com.mathtop.course.domain.ExaminationType;
import com.mathtop.course.domain.UserContactType;
import com.mathtop.course.domain.PermissionOperator;
import com.mathtop.course.service.PermissionOperatorService;

@Controller
@RequestMapping("/permission/operator")
public class PermissionrOperatorController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private PermissionOperatorService permissionoperatorService;

	

	private final String strPageURI = "permission";

	private void SetPageURI(ModelAndView mav) {
		mav.addObject("pagedURI", strPageURI);
	}

	
	
	
	/**
	 * 添加权限操作
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, UserContactType ct) {

		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/list");

		PermissionOperator dbdata = permissionoperatorService.getByName(ct.getName());

		if (dbdata == null) {
			return mav;
		} else if (dbdata.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			Integer pageNo = 1;
			Page<PermissionOperator> pagedPermissionOperator = permissionoperatorService
					.getPage(pageNo, CommonConstant.PAGE_SIZE);

			mav.addObject(PagedObjectConst.Paged_PermissionOperator, pagedPermissionOperator);
			String toUrl = ("list.html");
			mav.setViewName("redirect:" + toUrl);
			

		} else {
			permissionoperatorService.Add(ct.getName(), ct.getNote());

			String toUrl = ("list.html");
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
			permissionoperatorService.DELETE(ctId);

		ModelAndView mav = new ModelAndView();
		String toUrl = ("list.html");
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
		SetPageURI(view);
		view.setViewName(strPageURI+"/list");

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<PermissionOperator> pagedPermissionOperator = permissionoperatorService
					.select(ctIdname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_PermissionOperator, pagedPermissionOperator);
			view.setViewName(strPageURI+"/list");

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
	public ModelAndView update(HttpServletRequest request, ExaminationType item) {
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/list");

		PermissionOperator dbitem = permissionoperatorService.getByName(item
				.getName());

		if (dbitem == null) {
			return mav;
		} else if (dbitem.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			String toUrl = ("list");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		} else {

			permissionoperatorService.update(item.getId(), item.getName(),
					item.getNote());

			String toUrl = ("list");
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
	public ModelAndView list(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;
		
		Page<PermissionOperator> pagedPermissionOperator = permissionoperatorService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_PermissionOperator, pagedPermissionOperator);

		view.setViewName(strPageURI+"/operator");
		return view;
	}

	// 得到所有学院
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<PermissionOperator> getall(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = pageNo == null ? 1 : pageNo;
		Page<PermissionOperator> pagedPermissionOperator = permissionoperatorService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);
		return pagedPermissionOperator.getResult();
	}
	
	
	
}
