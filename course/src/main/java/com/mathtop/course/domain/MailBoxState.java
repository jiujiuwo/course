package com.mathtop.course.domain;

/**
 * 邮箱状态字符串
 * */
public class MailBoxState {
	private final String MailBoxState_READ="r";
	private String state;
	
	public MailBoxState(){
		state="";
	}
	
	public MailBoxState(String state){
		this.state=state;
	}

	public String getState() {
		return state;
	}

	public String setRead() {
		state=MailBoxState_READ;
		return state;
	}
	public boolean isRead(){
		return state.equals(MailBoxState_READ);
	}
}
