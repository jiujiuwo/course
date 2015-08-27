package com.mathtop.course.exception;

public class UserExistException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3402649165917242228L;

	public UserExistException(String errorMsg)
    {
        super(errorMsg);
    }
}