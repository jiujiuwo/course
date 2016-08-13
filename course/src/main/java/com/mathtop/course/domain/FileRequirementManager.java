package com.mathtop.course.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mathtop.course.cons.CommonConstant;

public class FileRequirementManager {
	private List<FileRequirementNode> nodes;

	public void addNode(FileRequirementNode node) {
		nodes.add(node);
	}

	public boolean isEmpty() {
		return nodes.size() == 0;
	}

	public int getSize() {
		return nodes.size();
	}

	/**
	 * 取得文件数目
	 */
	public int getFilesCount() {
		int nCount = 0;
		for (FileRequirementNode n : nodes) {
			int t = n.getFilesCount();
			if (t == -1)
				return -1;
			nCount += t;
		}
		return nCount;
	}

	/**
	 * 判断文件个数是否正确
	 */
	public boolean isFileCountRight(FileNodePropertyManager fileNodePropertyManager) {

		if (fileNodePropertyManager == null)
			return false;

		// 每个node都必须满足要求
		List<FileRequirementNode> listNodes = getArray();
		if (listNodes != null) {

			for (FileRequirementNode node : listNodes) {

				if (node.getFilesCount() != CommonConstant.HOMEWORK_FILE_COUNT_INFINITE) {
					FileNodePropertyData data = fileNodePropertyManager.getNode(node.getData().getNodeID());

					if (data == null)
						return false;
					if (!node.isFileCountRight(data.getFilesCount()))
						return false;
				}
			}
		}

		// 每个上传的文件都必须在node中
		List<FileNodePropertyData> list = fileNodePropertyManager.getNodes();

		if (list != null) {
			for (FileNodePropertyData d : list) {
				FileRequirementNode node = getNode(d.getNodeID());
				if (node != null && !node.isFileCountRight(d.getFilesCount()))
					return false;

			}
		}

		return true;
	}

	/**
	 * 文件格式是否包含任意个，同getFilesCount（），如果getFilesCount()返回-1，也表示任意个
	 */
	public boolean isUnLimitedFileCount() {
		if (nodes == null)
			return false;
		if (nodes.size() != 1)
			return false;
		if (nodes.get(0) == null)
			return false;

		return true;
	}

	/**
	 * 针对要求而言，文件类型是否合法
	 */
	public boolean isFileTypeValidate() {
		if (nodes.size() == 0)
			return true;

		for (FileRequirementNode n : nodes) {
			if (!n.isFileTypeValidate())
				return false;
		}
		return true;
	}

	/**
	 * 针对要求而言，文件名字格式是否合法
	 */
	public boolean isFileNameFormatValidate() {
		if (nodes.size() == 0)
			return true;

		for (FileRequirementNode n : nodes) {
			if (!n.isFileNameFormatValidate())
				return false;
		}
		return true;
	}

	// 文件名字是否合法
	public boolean isFileNameValidate(List<String> lstFileNames) {
		int nCount = getFilesCount();
		if (nCount == CommonConstant.HOMEWORK_FILE_COUNT_INFINITE)
			return true;
		if (lstFileNames == null)
			return false;
		if (nCount != lstFileNames.size())
			return false;

		return true;

	}

	/**
	 * 得到孩子
	 */
	private List<FileRequirementNode> getChilderen(FileRequirementData[] datas, FileRequirementData parent) {
		if (datas.length == 0)
			return null;
		if (parent == null)
			return null;

		List<FileRequirementNode> list = new ArrayList<>();
		for (FileRequirementData d : datas) {
			if (d.getParentNodeID() == parent.getNodeID()) {
				FileRequirementNode node = new FileRequirementNode(this);
				node.setData(d);
				node.setChildren(getChilderen(datas, d));

				list.add(node);
			}
		}
		return list;

	}

	/**
	 * 解析json
	 */
	public void ParseJson(String strJson) {

		Gson gson = new Gson();
		FileRequirementData[] datas = gson.fromJson(strJson, FileRequirementData[].class);

		nodes = new ArrayList<>();

		if (datas.length > 0) {
			for (FileRequirementData d : datas) {
				if (d.getParentNodeID() == -1) {
					FileRequirementNode node = new FileRequirementNode(this);
					node.setChildren(getChilderen(datas, d));
					node.setData(d);

					nodes.add(node);

				}
			}
		}
	}

	public List<FileRequirementNode> getNodes() {
		return nodes;
	}

	public FileRequirementNode getNode(int nodeID) {
		if (nodeID < 0)
			return null;

		for (FileRequirementNode d : nodes) {
			FileRequirementNode node = d.getNode(nodeID);
			if (node != null)
				return node;

		}
		return null;
	}

	/**
	 * 形成正确的树逻辑的数组,即父亲下面是孩子
	 */
	public List<FileRequirementNode> getArray() {
		if (nodes.size() == 0)
			return null;

		List<FileRequirementNode> list = new ArrayList<>();
		for (FileRequirementNode d : nodes) {
			if (d.getData().getParentNodeID() < 0)
				d.getArray(list);

		}
		return list;

	}

	/**
	 * 得到父亲节点
	 */
	public FileRequirementNode getParentNode(FileRequirementNode node) {
		if (node == null)
			return null;
		for (FileRequirementNode d : nodes) {
			if (node.getData().getParentNodeID() == d.getData().getNodeID())
				return d;
		}
		return null;
	}

}
