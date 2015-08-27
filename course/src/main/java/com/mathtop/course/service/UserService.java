package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.UserDao;
import com.mathtop.course.domain.User;
import com.mathtop.course.exception.UserExistException;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	

	
	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * 
	 * @param user
	 */
	public String register(User user) throws UserExistException {
		User u = this.getUserByUserName(user.getUser_name());
		if (u != null) {
			throw new UserExistException("用户名已经存在");
		} else {
			
			return userDao.add(user);
		}
	}
	
	/**
	 * 更新密码，注意穿过的值是加密后的值
	 * */
	public void UpdateUserPassword(String id,String strEncodedPwd){
		userDao.UpdateUserPassword(id, strEncodedPwd);
	}

	/**
     * 根据用户名/密码查询 User对象
     * @param userName 用户名
     * @return User
     */
    public User getUserByt_user_id(String t_user_id){
        return userDao.getUserByID(t_user_id);
    }
    
    /**
     * 根据用户名/密码查询 User对象
     * @param userName 用户名
     * @return User
     */
    public User getUserByUserName(String userName){
    	
    	if(null==userName || userName.isEmpty())
    		return null;
    	
        return userDao.getUserByName(userName);
    }

}
