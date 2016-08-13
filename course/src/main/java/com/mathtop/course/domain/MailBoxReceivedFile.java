package com.mathtop.course.domain;



public class MailBoxReceivedFile extends BaseDomain {

	

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 4832081916661131375L;
	private String id;
	 private String mailBoxReceivedId;
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
	public String getMailBoxReceivedId() {
		return mailBoxReceivedId;
	}
	public void setMailBoxReceivedId(String mailBoxReceivedId) {
		this.mailBoxReceivedId = mailBoxReceivedId;
	}
	
}
