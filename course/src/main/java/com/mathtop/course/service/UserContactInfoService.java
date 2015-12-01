package com.mathtop.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.UserContactInfoDao;
import com.mathtop.course.domain.UserContactInfo;
import com.mathtop.course.exception.UserExistException;

@Service
public class UserContactInfoService {
	
	@Autowired
	private UserContactInfoDao usercontactinfoDao;

	
	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * 
	 * @param user
	 */
	public String add(UserContactInfo usercontactinfo) throws UserExistException {			
			return usercontactinfoDao.add(usercontactinfo);		
	}
	
	public void deleteByUserId(String t_user_id){
		usercontactinfoDao.deleteByUserId(t_user_id);
	}
	
	/**
     * 
     * @param t_user_id 用户id
     * @return User
     */
    public List<UserContactInfo> getUserContactInfoByt_user_id(String t_user_id){
        return usercontactinfoDao.getUserContactInfoByt_user_id(t_user_id);
    }
}
