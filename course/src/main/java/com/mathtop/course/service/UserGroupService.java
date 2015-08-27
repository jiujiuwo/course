package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.UserGroupDao;
import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.UserGroupViewData;

@Service
public class UserGroupService {

	@Autowired
	UserGroupDao usergroupDao;
	
	public Page<UserGroupViewData> getPageByGroupId(String t_group_id,int pageNo, int pageSize) {
		return usergroupDao.getPageByGroupId(t_group_id, pageNo, pageSize);
	}
	
	public Page<Group> getGroupPageByt_user_id(String t_user_id) {
		return usergroupDao.getGroupPageByt_user_id(t_user_id);
	}
}
