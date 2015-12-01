package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.UserSessionInfoDao;
import com.mathtop.course.domain.UserSessionInfo;

@Service
public class UserSessionInfoService {
	
	@Autowired
	private UserSessionInfoDao dao;
	
	public UserSessionInfo getUserSessionInfoByUserId(String t_user_id) {
		return dao.getUserSessionInfoByUserId(t_user_id);
	}
}
