package com.mathtop.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.UserGroupDao;
import com.mathtop.course.domain.Group;
import com.mathtop.course.domain.UserGroup;
import com.mathtop.course.domain.UserGroupViewData;

@Service
public class UserGroupService {

	@Autowired
	UserGroupDao usergroupDao;

	public String add(String t_group_id, String t_user_id) {
		return usergroupDao.add(t_group_id, t_user_id);
	}

	public Page<UserGroupViewData> getPageByGroupId(String t_group_id, int pageNo, int pageSize) {
		return usergroupDao.getPageByGroupId(t_group_id, pageNo, pageSize);
	}

	public List<UserGroup> getUserGroupByGroupId(String t_group_id) {
		return usergroupDao.getUserGroupByGroupId(t_group_id);
	}

	public List<UserGroupViewData> getUserGroupViewDataByGroupId(String t_group_id) {
		return usergroupDao.getUserGroupViewDataByGroupId(t_group_id);
	}

	public long getCount(String t_group_id) {
		return usergroupDao.getCount(t_group_id);
	}

	public Page<Group> getGroupPageByt_user_id(String t_user_id) {
		return usergroupDao.getGroupPageByt_user_id(t_user_id);
	}

	public void deleteById(String t_user_group_id) {
		usergroupDao.deleteById(t_user_group_id);
	}

	public void deleteByGroupId(String t_group_id) {
		usergroupDao.deleteByGroupId(t_group_id);
	}

	public void deleteByUserId(String t_user_id) {
		usergroupDao.deleteByUserId(t_user_id);
	}

	public void deleteByGroupIdAndUserId(String t_group_id, String t_user_id) {
		usergroupDao.deleteByGroupIdAndUserId(t_group_id, t_user_id);
	}

}
