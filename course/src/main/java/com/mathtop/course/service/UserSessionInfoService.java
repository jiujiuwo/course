package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.CourseTeachingClassViewDataDao;
import com.mathtop.course.dao.GroupDao;
import com.mathtop.course.dao.GroupRoleDao;
import com.mathtop.course.dao.PermissionDao;
import com.mathtop.course.dao.RolePermissionDao;
import com.mathtop.course.dao.StudentDao;
import com.mathtop.course.dao.TeacherDao;
import com.mathtop.course.dao.UserBasicInfoDao;
import com.mathtop.course.dao.UserDao;
import com.mathtop.course.dao.UserGroupDao;
import com.mathtop.course.domain.GroupRole;
import com.mathtop.course.domain.PermissionViewData;
import com.mathtop.course.domain.RolePermission;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.domain.UserGroup;
import com.mathtop.course.domain.UserSessionInfo;

@Service
public class UserSessionInfoService {
	
	
	
	
	@Autowired
	UserDao userDao;

	@Autowired
	GroupDao groupDao;

	@Autowired
	GroupRoleDao grouproleDao;

	@Autowired
	UserGroupDao usergroupDao;

	@Autowired
	UserBasicInfoDao userbasicinfoDao;

	@Autowired
	TeacherDao teacherDao;

	@Autowired
	StudentDao studentDao;

	@Autowired
	PermissionDao permissionDao;

	@Autowired
	RolePermissionDao rolepermissionDao;

	@Autowired
	CourseTeachingClassViewDataDao teachingclassviewdataDao;
	
	@Autowired
	UserConfigService userConfigService;

	public List<PermissionViewData> getPermissionViewDataByUserId(String t_user_id) {		

		List<PermissionViewData> data = new ArrayList<PermissionViewData>();

		// 找到用户所在的组，找到组所具有的角色，找到角色具有的权限
		List<UserGroup> usergrouplist = usergroupDao.getUserGroupByt_user_id(t_user_id);
		for (UserGroup usergroup : usergrouplist) {			
			List<GroupRole> grouprolelist = grouproleDao.getGroupRoleByGroupId(usergroup.getGroupId());
			for (GroupRole grouprole : grouprolelist) {				
				List<RolePermission> rolepermissionlist = rolepermissionDao.getRolePermissionByRoleId(grouprole.getRoleId());
				for (RolePermission rolepermission : rolepermissionlist) {					
					PermissionViewData view = permissionDao.getPermissionViewDataByPermissionId(rolepermission.getPermissionId());
					data.add(view);
				}
			}
		}

		if(data.size()==0)
			return null;
		return data;

	}

	

	public UserSessionInfo getUserSessionInfoByUserId(String t_user_id) {
		
		if(t_user_id==null)
			return null;

		UserSessionInfo usi = new UserSessionInfo();

		User u = userDao.getUserByID(t_user_id);
		usi.setUser(u);
		
		//设置页面信息
		usi.setPageSize(userConfigService.getPageSize(t_user_id));

		// 基本信息
		UserBasicInfo ubi = userbasicinfoDao.getUserBasicInfoByt_user_id(t_user_id);

		usi.setUserbasicinfo(ubi);

		Teacher teacher = teacherDao.getTeacherByUserId(t_user_id);
		Student student = studentDao.getStudentByUserId(t_user_id);
		usi.setTeacher(teacher);
		usi.setStudent(student);

		List<PermissionViewData> list = getPermissionViewDataByUserId(t_user_id);
		usi.setPermissionviewdata(list);

		usi.setTeachingclassviewdata(teachingclassviewdataDao.getCourseTeachingClassViewDataByUserId(t_user_id));

		
		return usi;

	}
}
