package com.mathtop.course.domain;

public class CourseType extends Simple{

	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -717189803599279454L;

	public CourseType() {

	}

	public CourseType(Simple s) {
		this.setId(s.getId());
		this.setName(s.getName());
		this.setNote(s.getNote());
	}

}