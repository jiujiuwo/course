package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.GroupRoleDao;
import com.mathtop.course.dao.Page;
import com.mathtop.course.domain.GroupRoleViewData;

@Service
public class GroupRoleService {

	@Autowired
	GroupRoleDao grouproleDao;
	
	public void add(String t_group_id,String t_role_id){
		grouproleDao.add(t_group_id,t_role_id);
	}
	
	public void deleteById(String id){
		grouproleDao.deleteById(id);
	}
	
	/**
	 * 删除组
	 * */
	public void deleteByGroupId(String t_group_id){
		grouproleDao.deleteById(t_group_id);
	}
	
	public Page<GroupRoleViewData> getPageByGroupId(String t_group_id,int pageNo, int pageSize) {
		return grouproleDao.getPageByGroupId(t_group_id, pageNo, pageSize);
	}
}
