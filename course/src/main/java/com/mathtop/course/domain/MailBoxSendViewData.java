package com.mathtop.course.domain;

import java.util.List;





public class MailBoxSendViewData extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3330336629812884511L;

	private List<MailBoxSendFile> sendfile;
	private MailBoxSend send;
	private UserBasicInfoViewData userFrom;
	private UserBasicInfoViewData userTo;
	
	public MailBoxSend getSend() {
		return send;
	}
	public void setSend(MailBoxSend send) {
		this.send = send;
	}
	
	public List<MailBoxSendFile> getSendfile() {
		return sendfile;
	}
	public void setSendfile(List<MailBoxSendFile> sendfile) {
		this.sendfile = sendfile;
	}
	public UserBasicInfoViewData getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(UserBasicInfoViewData userFrom) {
		this.userFrom = userFrom;
	}
	public UserBasicInfoViewData getUserTo() {
		return userTo;
	}
	public void setUserTo(UserBasicInfoViewData userTo) {
		this.userTo = userTo;
	}
	
	
}
