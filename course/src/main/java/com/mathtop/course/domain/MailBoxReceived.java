package com.mathtop.course.domain;

import java.util.Date;



public class MailBoxReceived extends BaseDomain {

	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8061591395271139353L;
	private String id;
	 private String userIdFrom;
	 private String userIdTo;
	 private String state;
	 private String subject;
	 private String content;
	 private Date senddate;
	 private Date readdate;
	
	 
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
	public Date getReaddate() {
		return readdate;
	}
	public void setReaddate(Date readdate) {
		this.readdate = readdate;
	}
	public boolean isHasRead() {
		MailBoxState s=new MailBoxState(state);
		return s.isRead();
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
