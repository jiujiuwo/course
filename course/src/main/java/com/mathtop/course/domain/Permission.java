package com.mathtop.course.domain;

public class Permission extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4289671503720569512L;
	
	private String id;
	private String name;
	private String note;	
	private String permissionOperatorId;
	private String resourceId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPermissionOperatorId() {
		return permissionOperatorId;
	}
	public void setPermissionOperatorId(String permissionOperatorId) {
		this.permissionOperatorId = permissionOperatorId;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	

}
