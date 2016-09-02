package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.dao.UserConfigDao;
import com.mathtop.course.domain.UserConfig;

@Service
public class UserConfigService {

	@Autowired
	private UserConfigDao userConfigDao;

	
	public String add(String t_user_id,int page_size){
		return userConfigDao.add(t_user_id, page_size);
	}
    
	boolean isUserConfigExists(String t_user_id){	
		return userConfigDao.isUserConfigExists(t_user_id);
	}	
    
	public void updateByUserId(String t_user_id,int page_size){
		userConfigDao.updateByUserId(t_user_id, page_size);
	}
	
	/**
	 * 得到指定用户的页面大小
	 * */
	public int getPageSize(String t_user_id){	
		
		UserConfig userConfig=userConfigDao.getUserByUserId(t_user_id);
		if(userConfig!=null)
			return userConfig.getPageSize();
		
		return CommonConstant.PAGE_SIZE;
		
	}
	
    public void deleteByUserId(String t_user_id){
    	userConfigDao.deleteById(t_user_id);	
    }

}
