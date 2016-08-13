package com.mathtop.course.domain;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class FileNodePropertyManager {
	private List<FileNodePropertyData> nodes;// 孩子
	
	
	public FileNodePropertyManager(String strJson){
		ParseJson(strJson);
	}
	
	
	public FileNodePropertyData getNodeByFileIndex(int nIndex){
		if(nodes==null)
			return null;
		
		if(nIndex<0 )
			return null;
		
		int nCount=0;
		for(int i=0;i<nodes.size();i++){
			
			nCount+=nodes.get(i).getFilesCount();
			
			if(nIndex<nCount)
				return nodes.get(i);
			
		}
		
		return null;
	}

	public FileNodePropertyData getNode(int nodeID){
		if(nodeID<0)
			return null;
		
		if(nodes==null)
			return null;
		
		for(FileNodePropertyData d:nodes){
			if(d.getNodeID()==nodeID)
				return d;
		}
		return null;
	}

	/**
	 * 解析json
	 * */
	private void ParseJson(String strJson) {
		if(strJson==null || strJson.length()==0)
			return ;
		
		Gson gson = new Gson();
		FileNodePropertyData[] datas=gson.fromJson(strJson, FileNodePropertyData[].class);
		
		setNodes(Arrays.asList(datas));	
		
	}



	public List<FileNodePropertyData> getNodes() {
		return nodes;
	}



	private void setNodes(List<FileNodePropertyData> nodes) {
		this.nodes = nodes;
	}
	
}
