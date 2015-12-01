package com.mathtop.course.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.User;
import com.mathtop.course.utility.GUID;

@Repository
public class UserDao extends BaseDao<User> {

	private final String GET_USER_COUNT_BY_USERNAME_AND_USERPASSWORD = "SELECT count(*) FROM t_user WHERE user_name=? and user_password=?";
	private final String GET_USER_BY_USERNAME_AND_USERPASSWORD = "SELECT id FROM t_user WHERE user_name=? and user_password=?";
	private final String GET_USER_BY_USERNAME = "SELECT id,user_password FROM t_user WHERE user_name=?";
	private final String GET_USER_BY_ID = "SELECT user_name,user_password FROM t_user WHERE id=?";
	private final String INSERT_USER = "INSERT INTO t_user(id,user_name,user_password) VALUES(?,?,?)";
	private String UPDATE_BY_ID = "update t_user set user_password=? WHERE id=?";
	private final String DELETE_BY_ID = "DELETE FROM t_user WHERE id=?";

	/*
	 * 删除
	 */
	public void deleteById(String t_user_id) {
		Object params[] = new Object[] { t_user_id };
		int types[] = new int[] { Types.VARCHAR };
		getJdbcTemplate().update(DELETE_BY_ID, params, types);
	}

	
	
	/*根据用户名与密码判断用户是否存在，若存在返回true，否则返回false
	 * */
	boolean isUserExists(String userName,String userPwd){		
		int count= getJdbcTemplate().queryForObject(GET_USER_COUNT_BY_USERNAME_AND_USERPASSWORD,new Object[]{userName,userPwd},Integer.class);
		
		return count > 0?true:false;
	}
	
	public void updateUserPassword(String id,String strEncodedPwd){
		if(id==null || strEncodedPwd==null)
			return;		
		
		Object params[]=new Object[]{strEncodedPwd,id};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(UPDATE_BY_ID, params, types);
	}
	
	
	/*根据用户名与密码得到用户
	 * */
	User getUser(String userName,String userPwd){
		
		User user=new User();
		
		
		getJdbcTemplate().query(GET_USER_BY_USERNAME_AND_USERPASSWORD, new Object[]{userName,userPwd},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getString("id"));	
				user.setUser_name(userName);
				user.setUser_password(userPwd);
				
			}
			
		});
		
		return user;
	}
	
	/*根据用户名得到用户
	 * */
	public User getUserByName(String userName){
		
		User user=new User();
		
		
		getJdbcTemplate().query(GET_USER_BY_USERNAME, new Object[]{userName},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getString("id"));	
				user.setUser_name(userName);
				user.setUser_password(rs.getString("user_password"));
				
				
			}
			
		});
		
	//	System.out.println(user.getUser_password());
		if(user.getId()==null)
			return null;
		return user;
	}
	
	/*根据用户ID得到用户
	 * */
	public User getUserByID(String t_user_id){
		
		User user=new User();
		
		
		
		getJdbcTemplate().query(GET_USER_BY_ID, new Object[]{t_user_id},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(t_user_id);	
				user.setUser_name(rs.getString("user_name"));
				user.setUser_password(rs.getString("user_password"));
				
			}
			
		});
		
		if(user.getUser_name()==null)
			return null;
		return user;
	}
	
	/*增加用户*/
	public String add(User user){
		String id=GUID.getGUID();
		user.setId(id);
		Object params[]=new Object[]{user.getId(),user.getUser_name(),user.getUser_password()};
		int types[]=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		getJdbcTemplate().update(INSERT_USER, params, types);
		return id;
	}
	
	
}
