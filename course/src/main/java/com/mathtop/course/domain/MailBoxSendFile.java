package com.mathtop.course.domain;



public class MailBoxSendFile extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8956792270998512771L;
	
	

	 private String id;
	 private String t_mail_box_send_id;
	 private String filename;
	 private String filepath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getT_mail_box_send_id() {
		return t_mail_box_send_id;
	}
	public void setT_mail_box_send_id(String t_mail_box_send_id) {
		this.t_mail_box_send_id = t_mail_box_send_id;
	}   
}
