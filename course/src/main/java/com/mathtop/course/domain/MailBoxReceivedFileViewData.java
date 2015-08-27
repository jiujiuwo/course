package com.mathtop.course.domain;




public class MailBoxReceivedFileViewData extends BaseDomain {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8840635360153223926L;
	private MailBoxReceivedFile file;
	private MailBoxReceived received;
	public MailBoxReceivedFile getFile() {
		return file;
	}
	public void setFile(MailBoxReceivedFile file) {
		this.file = file;
	}
	public MailBoxReceived getReceived() {
		return received;
	}
	public void setReceived(MailBoxReceived received) {
		this.received = received;
	}
	
}
