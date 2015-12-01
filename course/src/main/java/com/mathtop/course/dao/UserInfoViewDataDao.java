package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Student;
import com.mathtop.course.domain.Teacher;
import com.mathtop.course.domain.User;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.domain.UserBasicInfoViewData;
import com.mathtop.course.domain.UserInfoViewData;

@Repository
public class UserInfoViewDataDao extends BaseDao<UserInfoViewData> {

	@Autowired
	UserDao userDao;
	
	@Autowired
	TeacherDao teacherDao;

	@Autowired
	StudentDao studentDao;
	
	@Autowired
	UserBasicInfoDao userbasicinfoDao;
	
	private final String GET_USERINFO_BY_t_user_id = "select name,user_contact_value"
			+ " from t_user_contact_info join t_user_contact_type on t_user_contact_info.t_user_contact_type_id=t_user_contact_type.id"
			+ " where t_user_contact_info.t_user_id=?";

	/*
	 * */
	public List<UserInfoViewData> getUserInfoViewData(String t_user_id) {

		List<UserInfoViewData> list = new ArrayList<UserInfoViewData>();

		getJdbcTemplate().query(GET_USERINFO_BY_t_user_id,
				new Object[] { t_user_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						UserInfoViewData uivd = new UserInfoViewData();
						uivd.setT_user_contact_type(rs.getString("name"));
						uivd.setUser_contact_value(rs
								.getString("user_contact_value"));
						list.add(uivd);

					}

				});

		return list;
	}
	
	
	public UserBasicInfoViewData getUserBasicInfoViewDataByt_user_id(String t_user_id) {
		UserBasicInfoViewData usi = new UserBasicInfoViewData();

		User u = userDao.getUserByID(t_user_id);
		usi.setUser(u);

		// 基本信息
		UserBasicInfo ubi = userbasicinfoDao.getUserBasicInfoByt_user_id(t_user_id);

		usi.setUserbasicinfo(ubi);

		Teacher teacher = teacherDao.getTeacherByt_user_id(t_user_id);
		Student student = studentDao.getStudentByt_user_id(t_user_id);
		usi.setTeacher(teacher);
		usi.setStudent(student);

		return usi;
	}
}
