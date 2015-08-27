package com.mathtop.course.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.cons.PagedObjectConst;
import com.mathtop.course.cons.SelectedObjectConst;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.UserGroupViewData;
import com.mathtop.course.service.GroupService;
import com.mathtop.course.service.UserGroupService;

@Controller
@RequestMapping("/permission/group-user")
public class GroupUserController extends BaseController {

	
	@Autowired
	UserGroupService usergroupService;
	
	@Autowired
	private GroupService groupService;
	
	private final String strPageURI = "permission";

	private void SetPageURI(ModelAndView mav) {
		mav.addObject("pagedURI", strPageURI);
	}

	
	/**
	 * 
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/list")
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
	
}
