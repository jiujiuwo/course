package com.mathtop.course.domain;

public class Simple extends BaseDomain{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2341541849088566774L;
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	private String note;
}
