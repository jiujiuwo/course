package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.UserBasicInfoDao;
import com.mathtop.course.domain.UserBasicInfo;
import com.mathtop.course.exception.UserExistException;

@Service
public class UserBasicInfoService {
	
	@Autowired
	private UserBasicInfoDao userbasicinfoDao;

	
	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * 
	 * @param user
	 */
	public String add(UserBasicInfo userbasicinfo) throws UserExistException {			
			return userbasicinfoDao.add(userbasicinfo);		
	}
	
	/**
     * 根据用户名/密码查询 User对象
     * @param userName 用户名
     * @return User
     */
    public UserBasicInfo getUserBasicInfoByt_user_id(String t_user_id){
        return userbasicinfoDao.getUserBasicInfoByt_user_id(t_user_id);
    }
}
