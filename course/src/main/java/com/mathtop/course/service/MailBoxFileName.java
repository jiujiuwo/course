package com.mathtop.course.service;

public class MailBoxFileName {
	private String OriginalFilename;//原始文件名
	private String IDFileName;//保存到磁盘上的文件名
	private String ID0FileName;//保存到磁盘上的新的文件名，和上面仅ID不同
	public String getOriginalFilename() {
		return OriginalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		OriginalFilename = originalFilename;
	}
	public String getIDFileName() {
		return IDFileName;
	}
	public void setIDFileName(String iDFileName) {
		IDFileName = iDFileName;
	}
	public String getID0FileName() {
		return ID0FileName;
	}
	public void setID0FileName(String iD0FileName) {
		ID0FileName = iD0FileName;
	}
	
}
