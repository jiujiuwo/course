package com.mathtop.course.domain;

public class StatisticsData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -690136133257609080L;
	
	private Integer totalCount;//总数
	private Integer submitCount;//提交数
	private Integer replyCount;//回复数
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getSubmitCount() {
		return submitCount;
	}
	public void setSubmitCount(Integer submitCount) {
		this.submitCount = submitCount;
	}
	public Integer getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

}
