package com.mathtop.course.domain;

public class FileNodePropertyData extends BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8267416123301691574L;
	private int nodeID;
	private int filesCount;
	public int getNodeID() {
		return nodeID;
	}
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	public int getFilesCount() {
		return filesCount;
	}
	public void setFilesCount(int filesCount) {
		this.filesCount = filesCount;
	}
	
}
