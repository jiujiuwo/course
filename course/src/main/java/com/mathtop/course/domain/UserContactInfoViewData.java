package com.mathtop.course.domain;

public class UserContactInfoViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6224930921156795923L;

	UserContactInfo usercontactinfo;
	UserContactType usercontacttype;
	public UserContactInfo getUsercontactinfo() {
		return usercontactinfo;
	}
	public void setUsercontactinfo(UserContactInfo usercontactinfo) {
		this.usercontactinfo = usercontactinfo;
	}
	public UserContactType getUsercontacttype() {
		return usercontacttype;
	}
	public void setUsercontacttype(UserContactType usercontacttype) {
		this.usercontacttype = usercontacttype;
	}
}
