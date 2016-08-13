package com.mathtop.course.domain;

public class GroupRole extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1599145760397519476L;
	
	
	private String id;
	private String groupId;
	private String roleId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	


}
