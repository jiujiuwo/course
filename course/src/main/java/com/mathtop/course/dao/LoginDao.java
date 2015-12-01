package com.mathtop.course.dao;

import java.sql.Timestamp;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.Login;
import com.mathtop.course.utility.DateTimeSql;
import com.mathtop.course.utility.GUID;

@Repository
public class LoginDao extends BaseDao<Login> {
	private final String INSERT_LOGIN = "INSERT INTO t_login(id,t_user_id,login_datetime,login_ip) VALUES(?,?,?,?)";
	
	private final String DELETE_BY_ID = "DELETE FROM t_login WHERE id=?";
	private final String DELETE_BY_USER_ID = "DELETE FROM t_login WHERE t_user_id=?";

	
	/**
	 * 删除指定用户的登录信息
	 * */
	public void deleteById(String id) {
		Object params[] = new Object[] { id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}
	
	public void deleteByUserId(String t_user_id) {
		Object params[] = new Object[] { t_user_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_USER_ID, params, types);
	}

	/**
	 * 增加登录信息
	 * */
	public void add(Login login){
		login.setId(GUID.getGUID());
		Object params[]=new Object[]{login.getId(),login.getT_user_id(),login.getLogin_datetime(),login.getLogin_ip()};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};
		getJdbcTemplate().update(INSERT_LOGIN, params, types);
	}
	
	public void add(String t_user_id,Timestamp login_datetime,String login_ip){		
		Object params[]=new Object[]{GUID.getGUID(),t_user_id,login_datetime,login_ip};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};
		getJdbcTemplate().update(INSERT_LOGIN, params, types);
	}
	
	public void add(String t_user_id,String login_ip){		
		Object params[]=new Object[]{GUID.getGUID(),t_user_id,DateTimeSql.Now(),login_ip};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};
		getJdbcTemplate().update(INSERT_LOGIN, params, types);
	}
}
