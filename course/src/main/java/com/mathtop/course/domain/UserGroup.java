package com.mathtop.course.domain;

public class UserGroup extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 280682936799155796L;
	
	private String id;
	private String userId;
	private String groupId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	

}
