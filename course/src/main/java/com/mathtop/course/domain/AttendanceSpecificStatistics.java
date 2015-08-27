package com.mathtop.course.domain;

public class AttendanceSpecificStatistics extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 967967576251072284L;
	
	private Integer nCount;
	private AttendanceState state;
	public Integer getnCount() {
		return nCount;
	}
	public void setnCount(Integer nCount) {
		this.nCount = nCount;
	}
	public AttendanceState getState() {
		return state;
	}
	public void setState(AttendanceState state) {
		this.state = state;
	}

}
