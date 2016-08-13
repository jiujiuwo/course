<%-- 
    Document   : Fileio
    Created on : 2014-8-27, 15:22:07
    Author     : liaojiamin
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>fileimport.jsp</title>
    <style type="text/css">
     #head {background-color: #EAEAEA;height:80px;margin:auto 15px ;}
     #content {height:190px;width:400px;margin:36px auto;}
     #line{margin-top:-10px;margin-left:15px;margin-right:15px}
     td{height:300px;valign:middle;font-size:11px;}
     body{background-color: #F0F8FD;margin: 0px; }
</style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="JavaScript" src="js/jquery-1.3.1.min.js" charset="utf-8"></script>
<script language="JavaScript" src="js/jvalidate-1.0.0.js" charset="utf-8"></script>
 
  </head>

<body>
    <div id="head" >
                    <div align="right"><font size="2px">   游客 <a href="#">登录</a></font></div>
					<div><b><font size="5px"> 科迪智标&mdash;资源库管理系统|资源入库</font></b></div>
      </div>
     
            
            <tr></tr>
      <div id="line" ><hr size="3" color="#080808"></div>
      
    
      <tr>导入成功</tr>
    	
    	 <div id="foot" align="center">
	          <font size="2px">2014  科迪智标 京ICP证000000号</font>

	 </div>
	
  </body>
    
</html>
