package com.mathtop.course.domain;

public class RolePermission extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6029515573021726701L;
	private String id;
	private String roleId;
	private String permissionId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}


}
