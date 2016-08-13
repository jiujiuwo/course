package com.mathtop.course.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mathtop.course.domain.UserContactInfo;
import com.mathtop.course.domain.UserContactInfoViewData;
import com.mathtop.course.domain.UserContactType;

@Repository
public class UserContactInfoViewDataDao extends BaseDao<UserContactInfoViewData> {
	
	@Autowired
	private UserContactInfoDao usercontactinfoDao;
	
	@Autowired
	private UserContactTypeDao usercontacttypeDao;
	
	public List<UserContactInfoViewData> getUserContactInfoViewDataByt_user_id(String t_user_id){
		List<UserContactInfoViewData> list=new ArrayList<UserContactInfoViewData>();
		List<UserContactInfo> data=usercontactinfoDao.getUserContactInfoByt_user_id(t_user_id);
		for(UserContactInfo d:data){
			UserContactInfoViewData view=new UserContactInfoViewData();
			
			
			view.setUsercontactinfo(d);
			
			UserContactType type=usercontacttypeDao.getByID(d.getUserContactTypeId());
			view.setUsercontacttype(type);
			list.add(view);
		}
		
		
		return list;
	}
}
