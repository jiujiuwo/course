package com.mathtop.course.domain;

import java.util.Date;



public class MailBoxSend extends BaseDomain {

	
	

	 /**
	 * 
	 */
	private static final long serialVersionUID = -1850680052983530478L;
	private String id;
	 private String t_user_id_from;
	 private String t_user_id_to;
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
	public String getT_user_id_from() {
		return t_user_id_from;
	}
	public void setT_user_id_from(String t_user_id_from) {
		this.t_user_id_from = t_user_id_from;
	}
	public String getT_user_id_to() {
		return t_user_id_to;
	}
	public void setT_user_id_to(String t_user_id_to) {
		this.t_user_id_to = t_user_id_to;
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
	
	  
}
