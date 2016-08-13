package com.mathtop.course.domain;

import org.springframework.web.multipart.MultipartFile;

public class FileSubmitData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7488564425974574471L;

	private int nodeID;
	private MultipartFile file;
	public int getNodeID() {
		return nodeID;
	}
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getFileName(){
		if(file!=null)
		return file.getOriginalFilename();
		return null;
	}
}
