package com.mathtop.course.domain;



public class CourseTeachingClassForumReplyFile extends BaseDomain {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2016733537694799853L;
	private String id;
	private String forumReplyId;
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
	public String getForumReplyId() {
		return forumReplyId;
	}
	public void setForumReplyId(String forumReplyId) {
		this.forumReplyId = forumReplyId;
	}
  
	
}
