package com.mathtop.course.domain;

public class RolePermissionViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8558624177773085756L;

	private Role role;
	private Permission permission;
	private RolePermission rolepermission;
	private PermissionOperator permissionoperator;
	private Resource resource;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public RolePermission getRolepermission() {
		return rolepermission;
	}
	public void setRolepermission(RolePermission rolepermission) {
		this.rolepermission = rolepermission;
	}
	public PermissionOperator getPermissionoperator() {
		return permissionoperator;
	}
	public void setPermissionoperator(PermissionOperator permissionoperator) {
		this.permissionoperator = permissionoperator;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
