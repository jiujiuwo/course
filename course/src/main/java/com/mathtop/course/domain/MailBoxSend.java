package com.mathtop.course.domain;

import java.util.Date;



public class MailBoxSend extends BaseDomain {

	
	

	 /**
	 * 
	 */
	private static final long serialVersionUID = -1850680052983530478L;
	private String id;
	 private String userIdFrom;
	 private String userIdTo;
	 private String state;
	 private String subject;
	 private String content;
	 private Date senddate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	public String getUserIdFrom() {
		return userIdFrom;
	}
	public void setUserIdFrom(String userIdFrom) {
		this.userIdFrom = userIdFrom;
	}
	public String getUserIdTo() {
		return userIdTo;
	}
	public void setUserIdTo(String userIdTo) {
		this.userIdTo = userIdTo;
	}
	
	  
}
