package com.mathtop.course.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.GroupRole;
import com.mathtop.course.domain.PermissionViewData;
import com.mathtop.course.domain.RolePermission;
import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.domain.UserGroup;
import com.mathtop.course.domain.UserSessionInfo;

@Repository
public class UserSessionInfoDao extends BaseDao<UserSessionInfo> {

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
	TeachingClassViewDataDao teachingclassviewdataDao;

	public List<PermissionViewData> getPermissionViewDataByUserId(String t_user_id) {		

		List<PermissionViewData> data = new ArrayList<PermissionViewData>();

		// 找到用户所在的组，找到组所具有的角色，找到角色具有的权限
		List<UserGroup> usergrouplist = usergroupDao.getUserGroupByt_user_id(t_user_id);
		for (UserGroup usergroup : usergrouplist) {			
			List<GroupRole> grouprolelist = grouproleDao.getGroupRoleByGroupId(usergroup.getT_group_id());
			for (GroupRole grouprole : grouprolelist) {				
				List<RolePermission> rolepermissionlist = rolepermissionDao.getRolePermissionByRoleId(grouprole.getT_role_id());
				for (RolePermission rolepermission : rolepermissionlist) {					
					PermissionViewData view = permissionDao.getPermissionViewDataByPermissionId(rolepermission.getT_permission_id());
					data.add(view);
				}
			}
		}

		if(data.size()==0)
			return null;
		return data;

	}

	

	public UserSessionInfo getUserSessionInfoByUserId(String user_id) {

		UserSessionInfo usi = new UserSessionInfo();

		User u = userDao.getUserByID(user_id);
		usi.setUser(u);

		// 基本信息
		UserBasicInfo ubi = userbasicinfoDao.getUserBasicInfoByt_user_id(user_id);

		usi.setUserbasicinfo(ubi);

		Teacher teacher = teacherDao.getTeacherByt_user_id(user_id);
		Student student = studentDao.getStudentByt_user_id(user_id);
		usi.setTeacher(teacher);
		usi.setStudent(student);

		List<PermissionViewData> list = getPermissionViewDataByUserId(user_id);
		usi.setPermissionviewdata(list);

		usi.setTeachingclassviewdata(teachingclassviewdataDao.getCourseTeachingClassViewDataByUserId(user_id));

		
		return usi;

	}
}
