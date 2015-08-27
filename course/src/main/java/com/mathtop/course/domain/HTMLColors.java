package com.mathtop.course.domain;

public class HTMLColors {
	private static final String operator_Pink = "#FFC0CB";//粉红
	private static final String operator_DarkOrchid= "#9932CC";//暗兰花紫
	private static final String operator_kyBlue= "#87CEEB";//天蓝色
	private static final String operator_DarkCyan= "#008B8B";	//暗青色
	private static final String operator_LightGreen= "#90EE90";//淡绿色
	private static final String operator_Olive= "#808000";//橄榄
	private static final String operator_Orange= "#FFA500";//橙色
	private static final String operator_Salmon= "#FA8072";//鲜肉/鲑鱼色
	private static final String operator_LightGrey= "#D3D3D3";//浅灰色
	private static String[] colors = {operator_Pink,
		operator_DarkOrchid,
		operator_kyBlue,
		operator_DarkCyan,
		operator_LightGreen,
		operator_Olive,
		operator_Orange,
		operator_Salmon,
		operator_LightGrey
		};
	private Integer nIndex;
	
	public HTMLColors(){
		nIndex=0;
	}
	public String getColor(){
		if(nIndex==colors.length)
			nIndex=0;
		return colors[nIndex++];
	}
}
