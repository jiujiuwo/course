<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.kdzb.tool.Encrypt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String basePath1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	System.out.println("要的就是你"+basePath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="css/kdzb.ico">

<link href="css/bootstrap.min_edit.css" rel="stylesheet">
<link rel="stylesheet" href="css/jquery.treeview.css" />
<link href="css/indexv1.css" rel="stylesheet">
<link href="script/ueditor/themes/default/css/ueditor.css" rel="stylesheet"/>
</head>
<body style="background-color: #f3f3f3;">
	<%
		String userName = (String) request.getParameter("userName");
		String fileName = (String) request.getParameter("fileName");
		String key = (String) request.getParameter("key"); 
		userName = new String(userName.getBytes("utf-8"), "utf-8");
		fileName = new String(fileName.getBytes("utf-8"), "utf-8");
		key = new String(key.getBytes("iso8859-1"), "utf-8");
		
		System.out.println("username---："+userName+"fileName----："+fileName+"key----："+key);

		String filenameEncrypt = Encrypt.encrypt(userName);
		System.out.println("第1个参数加密"+filenameEncrypt);
		String  addr = request.getLocalAddr();
		int port = request.getLocalPort();
		session.setAttribute("editer_userName", userName);
		session.setAttribute("editer_fileName", fileName);
		session.setAttribute("editer_key", key);
	%>
	<div class="container" style="background-color: #fff; width: 1022px">
		<div class="docinfo">
			<h1 id="edittabdoctitle" class="doctitle"><%=fileName%></h1>
		</div>
		<div class="col-sm-2">
			<h3>目录</h3>
			<ul class="treeview" id="tree" style="float: left;"></ul>
		</div>
		
		<div class="col-sm-10">
			<div class="node">
				<h3 id="chaptertitle"></h3>
				<div id="editareabtndiv" align="right"></div>
				<div id="editarea"></div>   <!-- 编辑器的区域展示 -->
				<hr>
			</div>
		</div>
	
	</div>
	
	<script src="script/ueditor/ueditor.config.js"></script>
    <script src="script/ueditor/ueditor.all.js"></script>  
    
  <!--   <script src="script/ueditor/kityformula-plugin/addKityFormulaDialog.js"></script>
	<script src="script/ueditor/kityformula-plugin/getKfContent.js"></script>
	<script src="script/ueditor/kityformula-plugin/defaultFilterFix.js"></script> -->
	<script src="script/ueditor/formula/formula_new.js"></script>
	
	
	<!-- <script src="../script/ckeditor/ckeditor.js"></script> -->
	<script src="script/js/sha1.js"></script>
	<script src="script/js/jQuery.js"></script>
	<script src="script/js/bootstrap.min_edit.js"></script>
	<script src="script/js/jquery.treeview.js"></script>
	<script src="script/js/ajaxfileupload.js"></script>
	<!-- <script src="../script/ckeditor/plugins/image/dialogs/image.js"></script> -->
	<script data-main="script/js/indexv1" src="script/js/require.js"></script>
	 <script type="text/javascript">
          function ajaxFileUpload(obj){    //zh 1_27对图片进行的处理包括上传那一
        	  alert("需要上传图片了！");
        	  var spanElement = obj.parentNode;
        	  var textElement = spanElement.getAttribute("id").replace("cke_", "");
        	  textElement = textElement.replace("_textInput", "");
        	  textElement = parseInt(textElement) - 48;
        	  $.ajaxFileUpload({
        	        url:'editer/ajax!readImgToResource.action',//处理图片脚本
        	        secureuri :false,
        	        fileElementId :'file',//file控件id
        	        spanElementId: spanElement.getAttribute("id"),
        	        dataType : 'json',
        	        success : function (data, status){
        	        	var obj = document.getElementById("cke_" + textElement + "_textInput");
        	        	
        	        	obj.value = data;
        	        	
        	        	alert("提交成功应用");
        	        },
        	        error: function(data, status, e){
        	            alert(e);
        	        }
        	})
               
          }
     	  $(document).ready(function(){
        	  window.username = '<%=userName%>';
        	  window.filenameEncrypt = '<%=filenameEncrypt%>';
        	  window.path = '<%=basePath%>';
        	  window.path1 = '<%=basePath1%>';
        	  window.addr = '<%=addr%>';
        	  window.port = '<%=port%>';
        	  
          }); 
      </script>
</body>
</html>
