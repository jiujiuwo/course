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
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Resource;
import com.mathtop.course.service.GroupRoleService;
import com.mathtop.course.service.ResourceService;
import com.mathtop.course.service.RolePermissionService;
import com.mathtop.course.service.UserGroupService;

@Controller
@RequestMapping("/permission/resource")
public class ResourceController extends BaseController {

	@Autowired
	UserGroupService usergroupService;

	@Autowired
	GroupRoleService grouproleService;

	@Autowired
	RolePermissionService rolepermissionService;
	
	@Autowired
	private ResourceService resourceService;

	private final String strPageURI = "permission/resource";

	private void SetPageURI(ModelAndView mav) {
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
	public ModelAndView add(HttpServletRequest request, Resource resource) {

		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI + "/list");

		resourceService.add(resource);

		String toUrl = ("list.html");
		mav.setViewName("redirect:" + toUrl);

		return mav;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{resourceId}", method = RequestMethod.GET)
	public ModelAndView DELETE(@PathVariable String resourceId) {
		if (resourceId != null)
			resourceService.delete(resourceId);

		ModelAndView mav = new ModelAndView();
		String toUrl = ("list.html");
		mav.setViewName("redirect:"+ toUrl);
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
		view.setViewName(strPageURI);

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<Resource> pagedPermissionOperator = resourceService
					.select(ctIdname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_PermissionOperator,
					pagedPermissionOperator);
			view.setViewName(strPageURI );

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
	public ModelAndView update(HttpServletRequest request, Resource item) {
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI );

		Resource dbitem = resourceService.getByID(item.getId());

		if (dbitem == null) {
			return mav;
		} else {

			resourceService.update(item.getId(), item.getUri(),item.getUri_element());

			String toUrl = ("list.html");
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
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;

		Page<Resource> pagedResource = resourceService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_PermissionResource, pagedResource);

		view.setViewName(strPageURI);
		return view;
	}

	// 得到所有学院
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	@ResponseBody
	public List<Resource> getall(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = pageNo == null ? 1 : pageNo;
		Page<Resource> pagedResource = resourceService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);
		return pagedResource.getResult();
	}
}
