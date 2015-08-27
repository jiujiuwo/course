package com.mathtop.course.exception;

public class SchoolExistException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3402649165917242228L;

	public SchoolExistException(String errorMsg)
    {
        super(errorMsg);
    }
}