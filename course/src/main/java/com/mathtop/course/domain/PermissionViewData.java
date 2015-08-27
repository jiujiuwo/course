package com.mathtop.course.domain;

public class PermissionViewData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6222815524026627942L;

	private Permission permission;
	private Resource resource;
	private PermissionOperator permissionoperator;
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public PermissionOperator getPermissionoperator() {
		return permissionoperator;
	}
	public void setPermissionoperator(PermissionOperator permissionoperator) {
		this.permissionoperator = permissionoperator;
	}
}
