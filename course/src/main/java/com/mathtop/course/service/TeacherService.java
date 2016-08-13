package com.mathtop.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.Page;
import com.mathtop.course.dao.TeacherDao;
import com.mathtop.course.dao.TeacherViewDataDao;
import com.mathtop.course.dao.UserBasicInfoDao;
import com.mathtop.course.dao.UserContactInfoDao;
import com.mathtop.course.dao.UserGroupDao;
import com.mathtop.course.domain.Department;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.TeacherViewData;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.domain.UserContactInfo;
import com.mathtop.course.exception.UserExistException;

@Service
public class TeacherService {

	@Autowired
	private TeacherDao teacherDao;

	@Autowired
	private UserService userService;

	@Autowired
	private UserContactInfoDao usercontactinfoDao;

	@Autowired
	private UserBasicInfoDao userbasicinfoDao;

	@Autowired
	private TeacherViewDataDao teacherviewdataDao;

	@Autowired
	UserGroupDao usergroupDao;

	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * 
	 * @param user
	 */
	public String register(Department department, Teacher teacher) throws UserExistException {
		Teacher u = teacherDao.getTeacherByTeacherNum(teacher.getTeacherNum());
		if (u != null) {
			throw new UserExistException("教师工号已经存在");
		} else {

			return teacherDao.add(department, teacher);
		}
	}

	
	/**
	 * 更新教师个人信息，更新内容较为简单，仅包括基本信息
	 * */
	public void updateTeacherInfo(String t_user_id, String user_basic_info_birthday, String user_basic_info_sex, String[] contacttypeId,
			String[] user_contact_value) {

		userbasicinfoDao.UpdateByt_user_id(t_user_id, user_basic_info_birthday, user_basic_info_sex);

		usercontactinfoDao.UpdateContactInfo(t_user_id, contacttypeId, user_contact_value);

	}

	/**
	 * 更新教师个人信息，更新内容全面
	 * */
	public void updateTeacherInfo(String t_user_id, String user_contact_info_name, String user_basic_info_birthday,
			String user_basic_info_sex, String[] contacttypeId, String[] user_contact_value) {

		userbasicinfoDao.UpdateByt_user_id(t_user_id, user_contact_info_name, user_basic_info_birthday, user_basic_info_sex);

		usercontactinfoDao.UpdateContactInfo(t_user_id, contacttypeId, user_contact_value);
	}

	/**
	 * 增加教师信息
	 * */
	public void addTeacher(User user, Department department, Teacher teacher, UserBasicInfo userbasicinfo,
			UserContactInfo[] usercontactinfos, String[] groupId) {
		try {

			// 添加用户
			user.EncoderPassword();
			String t_user_id = userService.register(user);

			// 添加教师
			teacher.setUserId(t_user_id);
			register(department, teacher);

			// 添加用户基本信息
			userbasicinfo.setUserId(t_user_id);
			userbasicinfoDao.add(userbasicinfo);

			// 添加联系方式
			if (usercontactinfos != null) {
				for (UserContactInfo u : usercontactinfos)
					u.setUserId(t_user_id);

				for (UserContactInfo u : usercontactinfos)
					usercontactinfoDao.add(u);
			}

			// groupid
			if (groupId != null) {
				for (String u : groupId)
					usergroupDao.add(u, t_user_id);
			}

		} catch (UserExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 更新教师信息
	 * */
	public void updateTeacher(User user, Teacher teacher, UserBasicInfo userbasicinfo,
			UserContactInfo[] usercontactinfos, String[] groupId) {
		
		if(user==null)
			return;
		String t_user_id=user.getId();
		if(t_user_id==null)
			return ;
		String teacherNum=teacher.getTeacherNum();
		
		teacher=teacherDao.getTeacherByUserId(t_user_id);
		try {			

			// 修改教师
			teacherDao.UpdateTeacherNumById(teacher.getId(), teacherNum);
			
			
			//  修改用户基本信息
			userbasicinfo.setUserId(t_user_id);
			userbasicinfoDao.UpdateByt_user_id(t_user_id, userbasicinfo);

			//  修改联系方式
			
			usercontactinfoDao.UpdateContactInfo(t_user_id,usercontactinfos);
			

			// groupid			
			usergroupDao.update(t_user_id,groupId);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据教师工号得到教师视图
	 * */
	public TeacherViewData getTeacherViewByTeacherNum(String teacherNum) {
		return teacherviewdataDao.getTeacherViewDataByTeacherNum(teacherNum);
	}

	public TeacherViewData getTeacherViewById(String id) {
		return teacherviewdataDao.getTeacherViewDataById(id);
	}

	public TeacherViewData getTeacherViewByt_user_id(String t_user_id) {
		return teacherviewdataDao.getTeacherViewDataByt_user_id(t_user_id);
	}

	/**
	 * 得到学院全体教师视图
	 * */
	public Page<TeacherViewData> getPage(String t_school_id, int pageNo, int pageSize) {
		return teacherviewdataDao.getPage(t_school_id, pageNo, pageSize);
	}

	/**
	 * 得到系部全体教师视图
	 * */
	public List<TeacherViewData> getTeacherViewByt_department_id(String deptid) {
		List<TeacherViewData> list = new ArrayList<TeacherViewData>();
		return list;
	}

	public int getTeacherCount() {
		return teacherDao.getCount();
	}

	public List<TeacherViewData> getTeacherViewByt_school_id(String t_school_id) {
		return teacherviewdataDao.getTeacherViewByt_school_id(t_school_id);
	}
}
