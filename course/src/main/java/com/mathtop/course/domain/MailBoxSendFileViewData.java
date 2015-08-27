package com.mathtop.course.domain;




public class MailBoxSendFileViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 371087680098798318L;
	private MailBoxSendFile file;
	private MailBoxSend send;
	public MailBoxSendFile getFile() {
		return file;
	}
	public void setFile(MailBoxSendFile file) {
		this.file = file;
	}
	public MailBoxSend getSend() {
		return send;
	}
	public void setSend(MailBoxSend send) {
		this.send = send;
	}
}
