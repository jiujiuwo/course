package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.PermissionViewData;
import com.mathtop.course.domain.Role;
import com.mathtop.course.domain.RolePermissionViewData;
import com.mathtop.course.service.GroupRoleService;
import com.mathtop.course.service.PermissionService;
import com.mathtop.course.service.RolePermissionService;
import com.mathtop.course.service.RoleService;
import com.mathtop.course.service.UserGroupService;

@Controller
@RequestMapping("/permission/role-permission")
public class RolePermissionController extends BaseController {

	@Autowired
	UserGroupService usergroupService;

	@Autowired
	GroupRoleService grouproleService;

	@Autowired
	private RoleService roleService;

	@Autowired
	RolePermissionService rolepermissionService;

	@Autowired
	PermissionService permissionService;

	private final String strPageURI = "permission/role-permission";

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
	public ModelAndView add(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName("redirect:/" + strPageURI + "/list.html");

		String t_role_id = request.getParameter("roleId");

		if (t_role_id != null) {
			rolepermissionService.deleteByRoleId(t_role_id);

			String[] permission = request.getParameterValues("permission");

			if (permission != null) {
				for (String t_permission_id : permission)
					rolepermissionService.add(t_role_id, t_permission_id);
			}
		}

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
	public ModelAndView list(
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;

		Page<Role> pagedRole = roleService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_Role, pagedRole);

		// 全部权限
		Page<PermissionViewData> pagedPermissionViewData = permissionService
				.getPagePermissionViewData();
		view.addObject(PagedObjectConst.Paged_PermissionViewData,
				pagedPermissionViewData);

		if (roleId == null && pagedRole.getResult() != null
				&& pagedRole.getResult().size() > 0)
			roleId = pagedRole.getResult().get(0).getId();

		if (roleId != null) {

			Page<RolePermissionViewData> pagedRolePermissionViewData = rolepermissionService
					.getPageRolePermissionViewData(roleId, pageNo,
							CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_RolePermissionViewData,
					pagedRolePermissionViewData);

			view.addObject(SelectedObjectConst.Selected_Role_ID, roleId);

		}

		view.setViewName(strPageURI);
		return view;
	}

}
