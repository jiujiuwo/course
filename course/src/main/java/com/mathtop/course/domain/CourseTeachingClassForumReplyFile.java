package com.mathtop.course.domain;



public class CourseTeachingClassForumReplyFile extends BaseDomain {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2016733537694799853L;
	private String id;
	private String t_forum_reply_id;
	  private String filename;
	  private String filepath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getT_forum_reply_id() {
		return t_forum_reply_id;
	}
	public void setT_forum_reply_id(String t_forum_reply_id) {
		this.t_forum_reply_id = t_forum_reply_id;
	}   
	
}
