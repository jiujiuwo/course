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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.CourseMessage;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.ExaminationType;
import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.GroupRoleViewData;
import com.mathtop.course.domain.Resource;
import com.mathtop.course.domain.Role;
import com.mathtop.course.domain.RolePermissionViewData;
import com.mathtop.course.domain.Simple;
import com.mathtop.course.domain.UserContactType;
import com.mathtop.course.domain.UserGroupViewData;
import com.mathtop.course.domain.PermissionOperator;
import com.mathtop.course.service.GroupRoleService;
import com.mathtop.course.service.GroupService;
import com.mathtop.course.service.PermissionOperatorService;
import com.mathtop.course.service.ResourceService;
import com.mathtop.course.service.RolePermissionService;
import com.mathtop.course.service.RoleService;
import com.mathtop.course.service.UserGroupService;

@Controller
@RequestMapping("/permission")
public class UserGroupController extends BaseController {

	/**
	 * 自动注入
	 */
	@Autowired
	private PermissionOperatorService permissionoperatorService;

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
	
	@Autowired
	private ResourceService resourceService;

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
	@RequestMapping(value = "/group-role-add")
	public ModelAndView add(HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI + "/group-role");

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
		mav.setViewName("redirect:/" + strPageURI + "/group-role.html");
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
			permissionoperatorService.deleteById(ctId);

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
		view.setViewName(strPageURI + "/list");

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<PermissionOperator> pagedPermissionOperator = permissionoperatorService
					.select(ctIdname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject(CourseMessage.Message_errorMsg,
					pagedPermissionOperator);
			view.setViewName(strPageURI + "/list");

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
		mav.setViewName(strPageURI + "/list");

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
	 * 
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/group-user")
	public ModelAndView group_user(
			@RequestParam(value = "groupId", required = false) String t_group_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;

		Page<Group> pagedGroup = groupService.getAllPage();

		view.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		if (t_group_id == null && pagedGroup.getResult() != null
				&& pagedGroup.getResult().size() > 0)
			t_group_id = pagedGroup.getResult().get(0).getId();

		if (t_group_id != null) {

			Page<UserGroupViewData> pagedGroupPersonInfo = usergroupService
					.getPageByGroupId(t_group_id, pageNo,
							CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_GroupPersonInfo,
					pagedGroupPersonInfo);

			view.addObject(SelectedObjectConst.Selected_Group_ID, t_group_id);

		}

		view.setViewName(strPageURI + "/group-user");
		return view;
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/group-role")
	public ModelAndView group_role(
			@RequestParam(value = "groupId", required = false) String t_group_id,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;

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

	/**
	 * 列出全部联系方式类型
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/role-permission")
	public ModelAndView role_permission(
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;

		Page<Role> pagedRole = roleService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_Role, pagedRole);

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

		view.setViewName(strPageURI + "/role-permission");
		return view;
	}

	/**
	 * 列出全部联系方式类型
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/permission-resource")
	public ModelAndView permission_resource(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;

		Page<PermissionOperator> pagedPermissionOperator = permissionoperatorService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_PermissionOperator,
				pagedPermissionOperator);

		view.setViewName(strPageURI + "/permission-resource");
		return view;
	}

	
	
	
	
	
	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/groupadd")
	public ModelAndView groupadd(HttpServletRequest request, Group ct) {

		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/group");

		Group dbdata = groupService.getByName(ct.getName());

		if (dbdata == null) {
			return mav;
		} else if (dbdata.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			Integer pageNo = 1;
			Page<Group> pagedGroup = groupService
					.getPage(pageNo, CommonConstant.PAGE_SIZE);

			mav.addObject(PagedObjectConst.Paged_Group, pagedGroup);
			String toUrl = ("group.html");
			mav.setViewName("redirect:" + toUrl);
			

		} else {
			groupService.add(ct.getName(), ct.getNote());

			String toUrl = ("group.html");
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
	@RequestMapping(value = "/groupdelete-{ctId}", method = RequestMethod.GET)
	public ModelAndView groupdelete(@PathVariable String ctId) {
		if (ctId != null)
			groupService.deleteById(ctId);

		ModelAndView mav = new ModelAndView();
		String toUrl = ("group.html");
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
	@RequestMapping(value = "/groupselect-{ctIdname}", method = RequestMethod.GET)
	public ModelAndView groupselect(@PathVariable String ctIdname) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);
		view.setViewName(strPageURI+"/group");

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<Group> pagedGroup = groupService
					.select(ctIdname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_Group, pagedGroup);
			view.setViewName(strPageURI+"/group");

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
	@RequestMapping(value = "/groupupdate")
	public ModelAndView groupupdate(HttpServletRequest request, ExaminationType item) {
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/list");

		Group dbitem = groupService.getByName(item
				.getName());

		if (dbitem == null) {
			return mav;
		} else if (dbitem.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			String toUrl = ("group");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		} else {

			groupService.update(item.getId(), item.getName(),
					item.getNote());

			String toUrl = ("group");
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
	@RequestMapping(value = "/group")
	public ModelAndView group(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;
		
		Page<Group> pagedGroup = groupService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_Group, pagedGroup);

		view.setViewName(strPageURI+"/group");
		return view;
	}

	// 得到所有学院
	@RequestMapping(value = "/getallgroup", method = RequestMethod.GET)
	@ResponseBody
	public List<Group> getallgroup(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = pageNo == null ? 1 : pageNo;
		Page<Group> pagedExaminationType = groupService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);
		return pagedExaminationType.getResult();
	}
	
	
	
	
	
	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/roleadd")
	public ModelAndView roleadd(HttpServletRequest request, UserContactType ct) {

		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/list");

		Simple dbdata = roleService.getByName(ct.getName());

		if (dbdata == null) {
			return mav;
		} else if (dbdata.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			Integer pageNo = 1;
			Page<Role> pagedRole = roleService
					.getPage(pageNo, CommonConstant.PAGE_SIZE);

			mav.addObject(PagedObjectConst.Paged_Role, pagedRole);
			String toUrl = ("list.html");
			mav.setViewName("redirect:" + toUrl);
			

		} else {
			roleService.add(ct.getName(), ct.getNote());

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
	@RequestMapping(value = "/roledelete-{ctId}", method = RequestMethod.GET)
	public ModelAndView roledelete(@PathVariable String ctId) {
		if (ctId != null)
			roleService.deleteById(ctId);

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
	@RequestMapping(value = "/roleselect-{ctIdname}", method = RequestMethod.GET)
	public ModelAndView roleselect(@PathVariable String ctIdname) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);
		view.setViewName(strPageURI+"/list");

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<Role> pagedRole = roleService
					.select(ctIdname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_Role, pagedRole);
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
	@RequestMapping(value = "/roleupdate")
	public ModelAndView roleupdate(HttpServletRequest request, ExaminationType item) {
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI+"/list");

		Role dbitem = roleService.getByName(item
				.getName());

		if (dbitem == null) {
			return mav;
		} else if (dbitem.getName() != null) {
			mav.addObject(CourseMessage.Message_errorMsg, "联系类型名已经存在");

			String toUrl = ("list");
			mav.setViewName("redirect:" + toUrl);
			return mav;

		} else {

			roleService.update(item.getId(), item.getName(),
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
	@RequestMapping(value = "/role")
	public ModelAndView role(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;
		
		Page<Role> pagedRole = roleService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_Role, pagedRole);

		view.setViewName(strPageURI+"/list");
		return view;
	}

	// 得到所有学院
	@RequestMapping(value = "/getallrole", method = RequestMethod.GET)
	@ResponseBody
	public List<Role> getallrole(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = pageNo == null ? 1 : pageNo;
		Page<Role> pagedRole = roleService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);
		return pagedRole.getResult();
	}

	
	
	/**
	 * 添加权限操作
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/permissionoperatoradd")
	public ModelAndView PermissionOperatorAdd(HttpServletRequest request, UserContactType ct) {

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
			permissionoperatorService.add(ct.getName(), ct.getNote());

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
	@RequestMapping(value = "/permissionoperatordelete-{ctId}", method = RequestMethod.GET)
	public ModelAndView PermissionOperatorDelete(@PathVariable String ctId) {
		if (ctId != null)
			permissionoperatorService.deleteById(ctId);

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
	@RequestMapping(value = "/permissionoperatorselect-{ctIdname}", method = RequestMethod.GET)
	public ModelAndView PermissionOperatorSelect(@PathVariable String ctIdname) {
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
	@RequestMapping(value = "/permissionoperatorupdate")
	public ModelAndView PermissionOperatorUpdate(HttpServletRequest request, ExaminationType item) {
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
	@RequestMapping(value = "/permissionoperator")
	public ModelAndView PermissionOperator(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;
		
		Page<PermissionOperator> pagedPermissionOperator = permissionoperatorService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_PermissionOperator, pagedPermissionOperator);

		view.setViewName(strPageURI+"/list");
		return view;
	}

	// 得到所有学院
	@RequestMapping(value = "/getallpermissionoperator", method = RequestMethod.GET)
	@ResponseBody
	public List<PermissionOperator> getAllPermissionOperator(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = pageNo == null ? 1 : pageNo;
		Page<PermissionOperator> pagedPermissionOperator = permissionoperatorService
				.getPage(pageNo, CommonConstant.PAGE_SIZE);
		return pagedPermissionOperator.getResult();
	}
	
	
	
	/**
	 * 添加学院
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/permissionresourceadd")
	public ModelAndView permissionresourceadd(HttpServletRequest request, Resource resource) {

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
	@RequestMapping(value = "/permissionresourcedelete-{resourceId}", method = RequestMethod.GET)
	public ModelAndView permissionresourcedelete(@PathVariable String resourceId) {
		if (resourceId != null)
			resourceService.delete(resourceId);

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
	@RequestMapping(value = "/permissionresourceselect-{ctIdname}", method = RequestMethod.GET)
	public ModelAndView permissionresourceselect(@PathVariable String ctIdname) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);
		view.setViewName(strPageURI + "/list");

		if (ctIdname != null && ctIdname.length() > 0) {

			int pageNo = 1;
			Page<Resource> pagedPermissionOperator = resourceService
					.select(ctIdname, pageNo, CommonConstant.PAGE_SIZE);

			view.addObject(PagedObjectConst.Paged_PermissionOperator,
					pagedPermissionOperator);
			view.setViewName(strPageURI + "/list");

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
	@RequestMapping(value = "/permissionresourceupdate")
	public ModelAndView permissionresourceupdate(HttpServletRequest request, Resource item) {
		ModelAndView mav = new ModelAndView();
		SetPageURI(mav);
		mav.setViewName(strPageURI + "/list");

		Resource dbitem = resourceService.getByID(item.getId());

		if (dbitem == null) {
			return mav;
		} else {

			resourceService.update(item.getId(), item.getUri(),item.getUriElement());

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
	@RequestMapping(value = "/permissionresource")
	public ModelAndView ListAll(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		ModelAndView view = new ModelAndView();
		SetPageURI(view);

		pageNo = pageNo == null ? 1 : pageNo;

		Page<Resource> pagedResource = resourceService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);

		view.addObject(PagedObjectConst.Paged_PermissionResource, pagedResource);

		view.setViewName(strPageURI + "/list");
		return view;
	}

	// 得到所有学院
	@RequestMapping(value = "/getallpermissionresource", method = RequestMethod.GET)
	@ResponseBody
	public List<Resource> getAllpermissionresource(
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		pageNo = pageNo == null ? 1 : pageNo;
		Page<Resource> pagedResource = resourceService.getPage(pageNo,
				CommonConstant.PAGE_SIZE);
		return pagedResource.getResult();
	}
}
