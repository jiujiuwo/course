package com.mathtop.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.PermissionDao;
import com.mathtop.course.domain.Permission;
import com.mathtop.course.domain.PermissionViewData;

@Service
public class PermissionService {
	
	@Autowired
	protected PermissionDao dao;
	
	public String add(String name, String note,String t_permission_operator_id,String t_resource_id) {
		return dao.add(name, note, t_permission_operator_id, t_resource_id);
	}
	
	public String add(Permission permission) {
		return dao.add(permission.getName(),permission.getNote(),permission.getT_permission_operator_id(),permission.getT_resource_id());
	}
	
	public Page<Permission> getPage(
			int pageNo, int pageSize) {
	return dao.getPage(pageNo, pageSize);
	}
	
	public Page<PermissionViewData> getPagePermissionViewData(
			int pageNo, int pageSize) {
		return dao.getPagePermissionViewData(pageNo, pageSize);
	}
	
	public Page<PermissionViewData> getPagePermissionViewData(
			) {
		return dao.getPagePermissionViewData();
	}
	
	
	public List<PermissionViewData> getPermissionViewData(
			) {
		return dao.getPermissionViewData();
	}
}
