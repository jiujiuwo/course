package com.mathtop.course.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseDomain implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1386486794187032977L;

	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
