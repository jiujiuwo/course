package com.mathtop.course.domain;

public class Resource extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1805770145743122260L;
	
	private String id;
	private String uri;
	private String uri_element;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUri_element() {
		return uri_element;
	}
	public void setUri_element(String uri_element) {
		this.uri_element = uri_element;
	}

}
