package com.mathtop.course.domain;

import java.util.List;





public class MailBoxReceivedViewData extends BaseDomain {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7818434460634762789L;
	private List<MailBoxReceivedFile> receivedfile;
	private MailBoxReceived received;
	private UserBasicInfoViewData userFrom;
	private UserBasicInfoViewData userTo;
	
	
	
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
	public List<MailBoxReceivedFile> getReceivedfile() {
		return receivedfile;
	}
	public void setReceivedfile(List<MailBoxReceivedFile> receivedfile) {
		this.receivedfile = receivedfile;
	}
	public MailBoxReceived getReceived() {
		return received;
	}
	public void setReceived(MailBoxReceived received) {
		this.received = received;
	}
	
	
}
