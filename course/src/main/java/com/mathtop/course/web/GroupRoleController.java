package com.mathtop.course.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.GroupRoleViewData;
import com.mathtop.course.domain.Role;
import com.mathtop.course.service.GroupRoleService;
import com.mathtop.course.service.GroupService;
import com.mathtop.course.service.RolePermissionService;
import com.mathtop.course.service.RoleService;
import com.mathtop.course.service.UserGroupService;

@Controller
@RequestMapping("/permission/group-role")
public class GroupRoleController extends BaseController {

	@Autowired
	private GroupService groupService;

	@Autowired
	UserGroupService usergroupService;

	@Autowired
	GroupRoleService grouproleService;

	@Autowired
	private RoleService roleService;

	@Autowired
	RolePermissionService rolepermissionService;
	
	private final String strPageURI = "permission";

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
	public ModelAndView add(HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		

		String groupid = request.getParameter("groupid");
		String roleid = request.getParameter("roleid");
		if (groupid == null || roleid == null) {
			mav.setViewName("redirect:/" + strPageURI + "/group-role.html");
			return mav;
		}

		// System.out.println(groupid);
		// System.out.println(roleid);

		grouproleService.add(groupid, roleid);
		redirectAttributes.addFlashAttribute(
				SelectedObjectConst.Selected_Group_ID, groupid);
		mav.setViewName("redirect:/permission/group-role/list.html");
		return mav;

	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/DELETE-{t_group_id}-{t_group_role_id}", method = RequestMethod.GET)
	public ModelAndView DELETE(HttpServletRequest request, RedirectAttributes redirectAttributes,@PathVariable String t_group_role_id,@PathVariable String t_group_id
			) {

		grouproleService.deleteById( t_group_role_id);

		
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		

		redirectAttributes.addFlashAttribute(
				SelectedObjectConst.Selected_Group_ID, t_group_id);
		mav.setViewName("redirect:/permission/group-role/list.html");
		return mav;

		
	}

	
	/**
	 * 
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView group_role(HttpServletRequest request,
			@RequestParam(value = "groupId", required = false) String t_group_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = getPageSize(request, pageSize);
		
		Page<Group> pagedGroup = groupService.getAllPage();

		view.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		if (t_group_id == null && pagedGroup.getResult() != null
				&& pagedGroup.getResult().size() > 0)
			t_group_id = pagedGroup.getResult().get(0).getId();

		// 角色
		Page<Role> pagedRole = roleService.getAllPage();

		view.addObject(PagedObjectConst.Paged_Role, pagedRole);

		if (t_group_id != null) {

			Page<GroupRoleViewData> pagedGroupRoleInfo = grouproleService
					.getPageByGroupId(t_group_id, pageNo,
							CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_GroupRoleInfo,
					pagedGroupRoleInfo);

			view.addObject(SelectedObjectConst.Selected_Group_ID, t_group_id);

		}

		view.setViewName(strPageURI + "/group-role");
		return view;
	}

	
}
