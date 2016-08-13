package com.mathtop.course.domain;

public class Resource extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1805770145743122260L;
	
	private String id;
	private String uri;
	private String uriElement;
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
	public String getUriElement() {
		return uriElement;
	}
	public void setUriElement(String uriElement) {
		this.uriElement = uriElement;
	}
	
}
