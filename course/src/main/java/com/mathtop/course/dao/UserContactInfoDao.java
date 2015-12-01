package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.UserContactInfo;
import com.mathtop.course.utility.GUID;

@Repository
public class UserContactInfoDao extends BaseDao<UserContactInfo> {
	private final String GET_USERCONTACTINFO_BY_ID = "SELECT t_user_id,t_user_contact_type_id,user_contact_value FROM t_user_contact_info WHERE id=?";
	
	private final String GET_USERCONTACTINFO_BY_t_user_id = "SELECT id,t_user_contact_type_id,user_contact_value FROM t_user_contact_info WHERE t_user_id=?";
	private final String INSERT_USERCONTACTINFO = "INSERT INTO t_user_contact_info (id,t_user_id,t_user_contact_type_id,user_contact_value) VALUES(?,?,?,?)";
	private final String DELETE_USERCONTACTINFO_BY_ID = "DELETE FROM t_user_contact_info WHERE id=?";
	private final String DELETE_USERCONTACTINFO_BY_USER_ID = "DELETE FROM t_user_contact_info WHERE t_user_id=?";
	private final String DELETE_USERCONTACTINFO_BY_CONTACT_TYPE_ID = "DELETE FROM t_user_contact_info WHERE t_user_contact_type_id=?";
	
	
	/**
	 * 更新用户个人信息，更新前先删除以前旧信息
	 * */
	public void UpdateContactInfo(String t_user_id, String[] contacttypeId, String[] user_contact_value) {

		deleteByUserId(t_user_id);

		int contactCount = 0;
		if (contacttypeId != null) {
			contactCount = contacttypeId.length;

		}

		if (contactCount > 0) {

			UserContactInfo[] usercontactinfos = new UserContactInfo[contactCount];

			for (int i = 0; i < contactCount; i++) {

				usercontactinfos[i] = new UserContactInfo();

				usercontactinfos[i].setT_user_contact_type_id(contacttypeId[i]);
				usercontactinfos[i].setUser_contact_value(user_contact_value[i]);
			}

			// 添加联系方式
			if (usercontactinfos != null) {
				for (UserContactInfo u : usercontactinfos)
					u.setT_user_id(t_user_id);

				for (UserContactInfo u : usercontactinfos)
					add(u);
			}
		}
	}
	
	public void UpdateContactInfo(String t_user_id, UserContactInfo[] usercontactinfos) {

		deleteByUserId(t_user_id);

		if (usercontactinfos != null) {
			for (UserContactInfo u : usercontactinfos)
				u.setT_user_id(t_user_id);

			for (UserContactInfo u : usercontactinfos)
				add(u);
		}
	}

	
	/*
	 * 根据ID得到UserContactInfo
	 */
	public UserContactInfo getUserContactInfoByID(String usercontactinfoID) {

		UserContactInfo usercontactinfo = new UserContactInfo();

		getJdbcTemplate().query(GET_USERCONTACTINFO_BY_ID,
				new Object[] { usercontactinfoID }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						usercontactinfo.setId(usercontactinfoID);
						usercontactinfo.setT_user_id(rs.getString("t_user_id"));
						usercontactinfo.setT_user_contact_type_id(rs
								.getString("t_user_contact_type_id"));
						usercontactinfo.setUser_contact_value(rs
								.getString("user_contact_value"));

					}

				});

		return usercontactinfo;
	}

	/*
	 * 根据用户ID得到UserContactInfo
	 */
	public List<UserContactInfo> getUserContactInfoByt_user_id(String t_user_id) {

		List<UserContactInfo> list=new ArrayList<UserContactInfo>();
		
		

		getJdbcTemplate().query(GET_USERCONTACTINFO_BY_t_user_id,
				new Object[] { t_user_id }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						UserContactInfo usercontactinfo = new UserContactInfo();
						usercontactinfo.setId(rs.getString("id"));
						usercontactinfo.setT_user_id(t_user_id);
						usercontactinfo.setT_user_contact_type_id(rs
								.getString("t_user_contact_type_id"));
						usercontactinfo.setUser_contact_value(rs
								.getString("user_contact_value"));
						list.add(usercontactinfo);

					}

				});

		return list;
	}
	
	/*
	 * 
	 */
	public void deleteById(String id) {

		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR};
		getJdbcTemplate().update(DELETE_USERCONTACTINFO_BY_ID, params, types);
		
	}
	
	/*
	 * 
	 */
	public void deleteByUserId(String t_user_id) {

		Object params[] = new Object[] { t_user_id };
		int types[] = new int[] { Types.VARCHAR};
		getJdbcTemplate().update(DELETE_USERCONTACTINFO_BY_USER_ID, params, types);
		
	}
	
	/*
	 * 
	 */
	public void deleteByUserContactTypeId(String t_user_contact_type_id) {

		Object params[] = new Object[] { t_user_contact_type_id };
		int types[] = new int[] { Types.VARCHAR};
		getJdbcTemplate().update(DELETE_USERCONTACTINFO_BY_CONTACT_TYPE_ID, params, types);
		
	}
	

	/* 增加UserContactInfo */
	public String add(UserContactInfo usercontactinfo) {
		String id = GUID.getGUID();
		usercontactinfo.setId(id);
		Object params[] = new Object[] { id, usercontactinfo.getT_user_id(),
				usercontactinfo.getT_user_contact_type_id(),
				usercontactinfo.getUser_contact_value() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR };
		getJdbcTemplate().update(INSERT_USERCONTACTINFO, params, types);
		return id;
	}

}
