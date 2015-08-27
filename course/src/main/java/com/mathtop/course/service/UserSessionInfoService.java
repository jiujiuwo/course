package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.UserSessionInfoDao;
import com.mathtop.course.domain.UserSessionInfo;

@Service
public class UserSessionInfoService {
	
	@Autowired
	private UserSessionInfoDao dao;
	
	public UserSessionInfo GetUserSessionInfoByt_user_id(String user_id) {
		return dao.GetUserSessionInfoByt_user_id(user_id);
	}
}
