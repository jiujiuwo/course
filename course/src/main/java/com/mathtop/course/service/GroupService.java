package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.GroupDao;
import com.mathtop.course.domain.Group;

@Service
public class GroupService extends SimpleService<GroupDao,Group> {
	
	@Autowired
	protected GroupDao dao;
	
	@Autowired
	UserGroupService userGroupService;
	
	@Autowired
	GroupRoleService groupRoleService;
	
	@Autowired
	CourseTeachingClassStudentGroupService courseTeachingClassStudentGroupService;

	@Override
	public GroupDao getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	/**
	 * 删除组，在t_group删除之前，必须删除相关外键
	 * */
	public void deleteById(String t_group_id){
		
		//用户-组
		userGroupService.deleteByGroupId( t_group_id);
		
		//组-角色
		groupRoleService.deleteByGroupId(t_group_id);
		
		//学生-组
		courseTeachingClassStudentGroupService.deleteByGroupId(t_group_id);
		
		//自己
		dao.deleteById(t_group_id);
		
	}
	
}
