package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.UserConfig;
import com.mathtop.course.utility.GUID;

@Repository
public class UserConfigDao extends BaseDao<UserConfig> {

	
	
	
	private final String GET_USER_CONFIG_BY_ID = "SELECT t_user_id,page_size FROM t_user_config WHERE id=?";
	private final String GET_USER_CONFIG_BY_USER_ID = "SELECT id,page_size FROM t_user_config WHERE t_user_id=?";
	private final String GET_USER_COUNT_BY_USER_ID="select count(*) from t_user_config where t_user_id=?";
	
	private final String INSERT_USER_CONFIG = "INSERT INTO t_user_config(id,t_user_id,page_size) VALUES(?,?,?)";
	
	private String UPDATE_BY_ID = "update t_user_config set t_user_id=?, page_size=? WHERE id=?";	
	private String UPDATE_PAGESIZE_BY_ID = "update t_user_config set page_size=? WHERE id=?";
	private String UPDATE_PAGESIZE_BY_USER_ID = "update t_user_config set page_size=? WHERE t_user_id=?";
	
	//delete
	private final String DELETE_BY_ID = "DELETE FROM t_user_config WHERE id=?";	
	private final String DELETE_BY_USER_ID = "DELETE FROM t_user_config WHERE t_user_id=?";
	
	

	/**
	 * 增加用户配置
	 * */
	public String add(UserConfig userConfig){
		String id=GUID.getGUID();
		userConfig.setId(id);
		Object params[]=new Object[]{userConfig.getId(),userConfig.getUserId(),userConfig.getPageSize()};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		getJdbcTemplate().update(INSERT_USER_CONFIG, params, types);
		return id;
	}
	
	/**
	 * 增加用户配置
	 * */
	public String add(String t_user_id,int page_size){
		String id=GUID.getGUID();		
		Object params[]=new Object[]{id,t_user_id,page_size};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		getJdbcTemplate().update(INSERT_USER_CONFIG, params, types);
		return id;
	}
	

	/**
	 * 删除
	 * */
	public void deleteById(String t_user_config_id) {
		Object params[] = new Object[] { t_user_config_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}
	
	/**
	 * 根据用户Id删除
	 * */
	public void deleteByUserId(String t_user_id) {
		Object params[] = new Object[] { t_user_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_USER_ID, params, types);
	}

	
	
	/**
	 * 根据用户id判断用户是否存在，若存在返回true，否则返回false
	 * */
	public boolean isUserConfigExists(String t_user_id){		
		int count= getJdbcTemplate().queryForObject(GET_USER_COUNT_BY_USER_ID,new Object[]{t_user_id},Integer.class);
		
		return count > 0?true:false;
	}
	
	public void updateById(String id,String t_user_id,int page_size){
		if(id==null || t_user_id==null)
			return;		
		
		Object params[]=new Object[]{t_user_id,page_size,id};
		int types[]=new int[]{Types.VARCHAR,Types.INTEGER,Types.VARCHAR};
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}
	
	
	public void updateById(String id,int page_size){
		if(id==null )
			return;		
		
		Object params[]=new Object[]{page_size,id};
		int types[]=new int[]{Types.INTEGER,Types.VARCHAR};
		getJdbcTemplate().update(UPDATE_PAGESIZE_BY_ID, params, types);
	}
	
	public void updateByUserId(String t_user_id,int page_size){
		if(t_user_id==null )
			return;		
		
		Object params[]=new Object[]{page_size,t_user_id};
		int types[]=new int[]{Types.INTEGER,Types.VARCHAR};
		getJdbcTemplate().update(UPDATE_PAGESIZE_BY_USER_ID, params, types);
	}
	
	/*根据用户名与密码得到用户
	 * */
	UserConfig getById(String id){
		
		UserConfig user=new UserConfig();
		
		
		getJdbcTemplate().query(GET_USER_CONFIG_BY_ID, new Object[]{id},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(id);	
				user.setUserId(rs.getString("t_user_id"));
				user.setPageSize(rs.getInt("page_size"));
				
			}
			
		});
		
		return user;
	}
	
	/*根据用户名得到用户
	 * */
	public UserConfig getUserByUserId(String t_user_id){
		
		UserConfig user=new UserConfig();
		
		
		getJdbcTemplate().query(GET_USER_CONFIG_BY_USER_ID, new Object[]{t_user_id},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getString("id"));	
				user.setUserId(t_user_id);
				user.setPageSize(rs.getInt("page_size"));
				
				
			}
			
		});
		
		if(user.getId()==null)
			return null;
		return user;
	}
	
	
	
}
