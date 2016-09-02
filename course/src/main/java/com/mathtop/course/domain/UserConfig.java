package com.mathtop.course.domain;

public class UserConfig extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3011408847848458706L;

	private String id;
	private String userId;
	private int pageSize;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
