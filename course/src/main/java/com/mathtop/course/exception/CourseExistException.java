package com.mathtop.course.exception;

public class CourseExistException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3402649165917242228L;

	public CourseExistException(String errorMsg)
    {
        super(errorMsg);
    }
}