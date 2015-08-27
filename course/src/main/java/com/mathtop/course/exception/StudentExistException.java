package com.mathtop.course.exception;

public class StudentExistException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3402649165917242228L;

	public StudentExistException(String errorMsg)
    {
        super(errorMsg);
    }
}