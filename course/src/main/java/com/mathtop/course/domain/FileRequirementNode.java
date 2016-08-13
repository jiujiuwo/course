package com.mathtop.course.domain;

import java.util.List;

import com.mathtop.course.cons.CommonConstant;
import com.mathtop.course.service.FileNameFormatParser;

public class FileRequirementNode {
	private FileRequirementData data;
	private List<FileRequirementNode> children;// 孩子
	private FileRequirementManager manager;

	public FileRequirementNode(FileRequirementManager manager) {
		this.manager = manager;
	}

	public int getLevel() {
		if (data == null)
			return 0;
		if (data.getParentNodeID() < 0)
			return 0;
		if (manager == null)
			return 0;
		return 1 + manager.getParentNode(this).getLevel();

	}
	
	
	/**
	 * 取得文件数目，包括自己和孩子的。返回HOMEWORK_FILE_COUNT_INFINITE表示数目不受限制，任意个
	 * */
	public int getFilesCount(){
		int nCount=0;
		if(data!=null  && data.isFile()){
			if(data.getFileCount()==-1)
				return CommonConstant.HOMEWORK_FILE_COUNT_INFINITE;
			nCount+=data.getFileCount();
		}
		
		if (children.size() == 0)
			return nCount;

		for (FileRequirementNode c : children){
			if (c.getFilesCount()==CommonConstant.HOMEWORK_FILE_COUNT_INFINITE)
				return CommonConstant.HOMEWORK_FILE_COUNT_INFINITE;
			nCount+=c.getFilesCount();
		}
		return nCount;
		
		
	}

	/**
	 * 孩子文件类型是否正确
	 */
	private boolean isChildrenFileTypeValidate() {
		if (children.size() == 0)
			return true;

		for (FileRequirementNode c : children)
			if (c.isFileTypeValidate())
				return false;
		return true;
	}

	/**
	 * 文件类型是否正确
	 */
	public boolean isFileTypeValidate() {
		if (data == null)
			return false;

		// 判断是否自己正确
		if (data.getFileType() == 0) {

			String[] filetypes = data.getFileTypeData().trim().split(";");
			String filetype = "";
			if (filetypes != null && filetypes.length > 0) {

				for (int i = 0; i < filetypes.length; i++) {
					if (filetypes[i].length() == 0)
						continue;
					if (i != 0 && i != filetypes.length - 1)
						filetype += ";";
					filetype += filetypes[i];

					int nPos = filetypes[i].lastIndexOf('.');

					// 正确的格式必须是*.txt等格式
					if (nPos < 0)
						return false;

					// .前面必须为*或为空
					if (nPos > 0 && !filetypes[i].substring(0, nPos).equals("*")) {

						return false;
					}

					// .后面不能为空
					if (filetypes[i].substring(nPos + 1).length() == 0) {

						return false;
					}

				}
			} else
				return false;

			data.setFileTypeData(filetype);
		}

		// 判断孩子
		return isChildrenFileTypeValidate();
	}

	/**
	 * 孩子文件名称是否正确
	 */
	private boolean isChildrenFileNameFormatValidate() {
		if (children.size() == 0)
			return true;

		for (FileRequirementNode c : children)
			if (c.isFileNameFormatValidate())
				return false;
		return true;
	}
	
	public boolean isFileCountRight(int nCount){
		int n=getFilesCount();
		if(n!= CommonConstant.HOMEWORK_FILE_COUNT_INFINITE && n!=nCount)
			return false;
		return true;
	}

	/**
	 * 文件名称是否正确
	 */
	public boolean isFileNameFormatValidate() {
		if (data == null)
			return false;

		FileNameFormatParser ffp = new FileNameFormatParser();
		if (!ffp.IsFileNameFormatRight(data.getFilenameRequirementVal()))
			return false;

		// 判断孩子
		return isChildrenFileNameFormatValidate();
	}

	public List<FileRequirementNode> getChildren() {
		return children;
	}

	public void setChildren(List<FileRequirementNode> children) {
		this.children = children;
	}

	public FileRequirementData getData() {
		return data;
	}

	public FileRequirementNode getNode(int nodeID) {
		if (nodeID < 0)
			return null;

		if (data.getNodeID() == nodeID)
			return this;

		for (FileRequirementNode c : children) {
			FileRequirementNode node = c.getNode(nodeID);
			if (node != null)
				return node;
		}
		return null;

	}

	public void setData(FileRequirementData data) {
		this.data = data;
	}

	/**
	 * 把自己的孩子加入到数组中
	 */
	public boolean getArray(List<FileRequirementNode> list) {
		if (list == null)
			return false;
		list.add(this);

		if (children.size() == 0)
			return true;

		for (FileRequirementNode c : children)
			if (c.getArray(list))
				return false;
		return true;

	}

}
