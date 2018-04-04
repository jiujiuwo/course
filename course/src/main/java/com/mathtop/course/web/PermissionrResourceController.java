package com.mathtop.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Permission;
import com.mathtop.course.domain.PermissionOperator;
import com.mathtop.course.domain.PermissionViewData;
import com.mathtop.course.domain.Resource;
import com.mathtop.course.service.PermissionOperatorService;
import com.mathtop.course.service.PermissionService;
import com.mathtop.course.service.ResourceService;

@Controller
@RequestMapping("/permission/permission-resource")
public class PermissionrResourceController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private PermissionOperatorService permissionoperatorService;

	



	@Autowired
	PermissionService permissionService;
	
	@Autowired
	private ResourceService resourceService;

	private final String strPageURI = "permission/permission-resource";

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
	public ModelAndView add(HttpServletRequest request,Permission permission) {

		
		
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI + "/list");

	//	permissionService.add(permission);
		permissionService.add(permission);

		String toUrl = ("list.html");
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
	public ModelAndView list(HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);

		Page<PermissionOperator> pagedPermissionOperator = permissionoperatorService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_PermissionOperator,
				pagedPermissionOperator);
		
		Page<Resource> pagedResource = resourceService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_PermissionResource, pagedResource);
		
		
		Page<PermissionViewData> pagedPermissionViewData = permissionService.getPagePermissionViewData(pageNo,CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_PermissionViewData, pagedPermissionViewData);

		view.setViewName(strPageURI);
		return view;
	}

	//得到所有学院
		@RequestMapping(value = "/getall", method = RequestMethod.GET)
		@ResponseBody
		public List<PermissionViewData> getAll() {			
			return permissionService.getPermissionViewData();
		}
	
	
}
