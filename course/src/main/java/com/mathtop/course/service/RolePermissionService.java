package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.RolePermissionDao;
import com.mathtop.course.domain.RolePermission;
import com.mathtop.course.domain.RolePermissionViewData;

@Service
public class RolePermissionService {
	
	@Autowired
	protected RolePermissionDao dao;
	
	public String add(String t_role_id, String t_permission_id) {
		return dao.add(t_role_id, t_permission_id);
	}
	
	public Page<RolePermissionViewData> getPageRolePermissionViewData(String roleId,
			int pageNo, int pageSize) {
		return dao.getPageRolePermissionViewData( roleId,pageNo, pageSize);
	}
	
	public Page<RolePermission> getPage(
			int pageNo, int pageSize) {
		return dao.getPage(pageNo, pageSize);
	}
	
	/*删除
	 * */
	public void deleteByRoleId(String t_role_id){		
		dao.deleteByRoleId(t_role_id);
	}

	
}
