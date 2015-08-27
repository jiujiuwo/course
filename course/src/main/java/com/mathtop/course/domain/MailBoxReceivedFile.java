package com.mathtop.course.domain;



public class MailBoxReceivedFile extends BaseDomain {

	

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4832081916661131375L;
	private String id;
	 private String t_mail_box_received_id;
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
	public String getT_mail_box_received_id() {
		return t_mail_box_received_id;
	}
	public void setT_mail_box_received_id(String t_mail_box_received_id) {
		this.t_mail_box_received_id = t_mail_box_received_id;
	}
	 
}
