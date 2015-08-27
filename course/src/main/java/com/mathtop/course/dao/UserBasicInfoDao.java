package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.utility.DateTimeSql;
import com.mathtop.course.utility.GUID;

@Repository
public class UserBasicInfoDao extends BaseDao<UserBasicInfo> {
	private final String GET_USERBASICINFO_BY_ID = "SELECT t_user_id,user_contact_info_name,user_contact_info_birthday,user_contact_info_sex,user_contact_info_address FROM t_user_basic_info WHERE id=?";
	private final String GET_USERBASICINFO_BY_t_user_id = "SELECT id,user_contact_info_name,user_contact_info_birthday,user_contact_info_sex,user_contact_info_address FROM t_user_basic_info WHERE t_user_id=?";
	private final String INSERT_USERBASICINFO = "INSERT INTO t_user_basic_info (id,t_user_id,user_contact_info_name,user_contact_info_birthday,user_contact_info_sex,user_contact_info_address) VALUES(?,?,?,?,?,?)";
	private final String DELETE_USERBASICINFO_BY_ID = "DELETE FROM t_user_basic_info WHERE id=?";
	private final String DELETE_USERBASICINFO_BY_USER_ID = "DELETE FROM t_user_basic_info WHERE t_user_id=?";
	private final String UPDATE_USERBASICINFO_0_BY_USER_ID = "update t_user_basic_info set user_contact_info_birthday=?,user_contact_info_sex=? WHERE t_user_id=?";
	private final String UPDATE_USERBASICINFO_1_BY_USER_ID = "update t_user_basic_info set user_contact_info_name=?,set user_contact_info_birthday=?,user_contact_info_sex=? WHERE t_user_id=?";

	/*
	 * 根据ID得到UserContactInfo
	 */
	public UserBasicInfo getUserBasicInfoByID(String userbasicinfoID) {

		UserBasicInfo userbasicinfo = new UserBasicInfo();

		getJdbcTemplate().query(GET_USERBASICINFO_BY_ID, new Object[] { userbasicinfoID }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				userbasicinfo.setId(userbasicinfoID);
				userbasicinfo.setT_user_id(rs.getString("t_user_id"));
				userbasicinfo.setUser_basic_info_name(rs.getString("user_contact_info_name"));
				userbasicinfo.setUser_basic_info_birthday(DateTimeSql.GetDate(rs.getString("user_contact_info_birthday")));
				userbasicinfo.setUser_basic_info_sex(Integer.parseInt(rs.getString("user_contact_info_sex")));
				userbasicinfo.setUser_basic_info_address(rs.getString("user_contact_info_address"));

			}

		});

		if (userbasicinfo.getId() == null)
			return null;
		return userbasicinfo;
	}

	public void UpdateByt_user_id(String t_user_id, String user_basic_info_birthday, String user_basic_info_sex) {
		if (t_user_id == null || user_basic_info_birthday == null || user_basic_info_sex == null)
			return;	

		Object params[] = new Object[] { DateTimeSql.GetDate(user_basic_info_birthday),Integer.parseInt( user_basic_info_sex), t_user_id };
		int types[] = new int[] { Types.TIMESTAMP, Types.INTEGER, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_USERBASICINFO_0_BY_USER_ID, params, types);
	}

	public void UpdateByt_user_id(String t_user_id, String user_contact_info_name,String user_basic_info_birthday, String user_basic_info_sex) {
		if (t_user_id == null || user_basic_info_birthday == null || user_basic_info_sex == null)
			return;

		Object params[] = new Object[] {user_contact_info_name, DateTimeSql.GetDate(user_basic_info_birthday), Integer.parseInt(user_basic_info_sex), t_user_id };
		int types[] = new int[] { Types.VARCHAR,Types.TIMESTAMP, Types.INTEGER, Types.VARCHAR };
		getJdbcTemplate().update(UPDATE_USERBASICINFO_1_BY_USER_ID, params, types);
	}

	/*
	 * 根据用户ID得到UserContactInfo
	 */
	public UserBasicInfo getUserBasicInfoByt_user_id(String t_user_id) {

		UserBasicInfo userbasicinfo = new UserBasicInfo();

		getJdbcTemplate().query(GET_USERBASICINFO_BY_t_user_id, new Object[] { t_user_id }, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				userbasicinfo.setId(rs.getString("id"));
				userbasicinfo.setT_user_id(t_user_id);
				userbasicinfo.setUser_basic_info_name(rs.getString("user_contact_info_name"));
				userbasicinfo.setUser_basic_info_birthday(DateTimeSql.GetDate(rs.getString("user_contact_info_birthday")));
				userbasicinfo.setUser_basic_info_sex(Integer.parseInt(rs.getString("user_contact_info_sex")));
				userbasicinfo.setUser_basic_info_address(rs.getString("user_contact_info_address"));

			}

		});

		if (userbasicinfo.getId() == null)
			return null;
		return userbasicinfo;
	}

	/*
	 * 
	 */
	public void deleteById(String id) {

		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_USERBASICINFO_BY_ID, params, types);

	}

	/*
	 * 
	 */
	public void deleteByUserId(String t_user_id) {

		Object params[] = new Object[] { t_user_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_USERBASICINFO_BY_USER_ID, params, types);

	}

	/* 增加UserContactInfo */
	public String add(UserBasicInfo userbasicinfo) {
		String id = GUID.getGUID();
		userbasicinfo.setId(id);
		Object params[] = new Object[] { id, userbasicinfo.getT_user_id(), userbasicinfo.getUser_basic_info_name(),
				DateTimeSql.GetDate(userbasicinfo.getUser_basic_info_birthday()), userbasicinfo.getUser_basic_info_sex(),
				userbasicinfo.getUser_basic_info_address() };
		int types[] = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER, Types.VARCHAR };
		getJdbcTemplate().update(INSERT_USERBASICINFO, params, types);
		return id;
	}
}
