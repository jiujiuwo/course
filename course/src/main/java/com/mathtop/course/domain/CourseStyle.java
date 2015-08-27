package com.mathtop.course.domain;

public class CourseStyle extends Simple{

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2036637573589018425L;

	public CourseStyle() {

	}

	public CourseStyle(Simple s) {
		this.setId(s.getId());
		this.setName(s.getName());
		this.setNote(s.getNote());
	}

}