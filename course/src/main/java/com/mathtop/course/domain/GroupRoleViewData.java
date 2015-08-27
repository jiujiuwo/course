package com.mathtop.course.domain;

public class GroupRoleViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7117468791663705900L;
	private GroupRole grouprole;
	private Group group;
	private Role role;
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public GroupRole getGrouprole() {
		return grouprole;
	}
	public void setGrouprole(GroupRole grouprole) {
		this.grouprole = grouprole;
	}

}
