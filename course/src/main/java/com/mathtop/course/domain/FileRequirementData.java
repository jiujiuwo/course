package com.mathtop.course.domain;

import com.mathtop.course.cons.CommonConstant;

public class FileRequirementData extends BaseDomain{
	/**
	 * 
	 */
	private static final long serialVersionUID = 249555673916752510L;
	private int nodeID;
	private int parentNodeID ;
	private int fileType ;
	private int fileCount;
	private String fileTypeDescription;
	private String fileTypeData ;
	private int fileFormat ;
	private String filenameRequirementVal ;
	
	/**
	 * 目录
	 * */
	public boolean isDirectory(){
		return fileType==CommonConstant.HOMEWORK_FILE_TYPE_DIRECTORY;
	}
	
	/**
	 * 文件类型
	 * */
	public boolean isFile(){
		return fileType==CommonConstant.HOMEWORK_FILE_TYPE_FILE || fileType==CommonConstant.HOMEWORK_FILE_TYPE_FILE_NOT_LIMITED;
	}
	
	/**
	 * 文件类型是否受限制：不限制
	 * */
	public boolean isFileTypeNotLimited(){
		return fileType==CommonConstant.HOMEWORK_FILE_TYPE_FILE_NOT_LIMITED;
	}
	
	/**
	 * 文件类型是否受限制：限制
	 * */
	public boolean isFileTypeLimited(){
		return fileType==CommonConstant.HOMEWORK_FILE_TYPE_FILE;
	}
	
	public boolean isFileNameNotLimited(){
		return fileFormat==CommonConstant.HOMEWORK_FILE_NAME_FORMAT_NOT_LIMITED;
	}
	
	public int getNodeID() {
		return nodeID;
	}
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	public int getParentNodeID() {
		return parentNodeID;
	}
	public void setParentNodeID(int parentNodeID) {
		this.parentNodeID = parentNodeID;
	}
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public String getFileTypeDescription() {
		return fileTypeDescription;
	}
	public void setFileTypeDescription(String fileTypeDescription) {
		this.fileTypeDescription = fileTypeDescription;
	}
	public String getFileTypeData() {
		return fileTypeData;
	}
	public void setFileTypeData(String fileTypeData) {
		this.fileTypeData = fileTypeData;
	}
	public int getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(int fileFormat) {
		this.fileFormat = fileFormat;
	}
	public String getFilenameRequirementVal() {
		return filenameRequirementVal;
	}
	public void setFilenameRequirementVal(String filenameRequirementVal) {
		this.filenameRequirementVal = filenameRequirementVal;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	

}
