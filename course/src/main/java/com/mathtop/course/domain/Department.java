package com.mathtop.course.domain;

public class Department extends Simple{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5247307202979424277L;
	public Department(){
		
	}
	public Department(Simple s){
		this.setId(s.getId());
		this.setName(s.getName());
		this.setNote(s.getNote());
	}
	
}
