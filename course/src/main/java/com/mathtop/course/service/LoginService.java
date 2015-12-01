package com.mathtop.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mathtop.course.dao.LoginDao;
import com.mathtop.course.domain.Login;

@Service
public class LoginService {
	@Autowired
	private LoginDao loginDao;
	
	public void Add(Login login){
		if(login!=null){
			loginDao.add(login);
		}
	}
	
	public void Add(String t_user_id,String login_ip){
		if(t_user_id!=null && login_ip!=null){
			loginDao.add(t_user_id,login_ip);
		}
	}
	
	 public void deleteByUserId(String t_user_id){
		 loginDao.deleteByUserId(t_user_id);
	 }
	
}
