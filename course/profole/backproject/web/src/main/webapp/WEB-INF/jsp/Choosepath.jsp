<%-- 
    Document   : Choosepath
    Created on : 2014-10-30, 15:24:17
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link href="css/backstage_style.css" rel="stylesheet" type="text/css">
<link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>

<body bgcolor="#ECECEC">
<div class="mttop">
  <div class="capic"></div>
  <div style="float:left;color:red">导入</div><div style="float:left;">|</div><div class="calianjie"><a href="retrieval.html" target="showframe">导出</a></div></div>

  <div class="mtotm"> 
  <div class="immiddle">
      <form  action="fileUpload.htm" name="test_form" id="getpath"   method="post" ENCTYPE="multipart/form-data">
  <div class="imfenco">添加压缩文件：</div><div class="imfengct"><input name="file" type="file" style="width:100%; height:30px;"></div>
    <div class="imfenco">标准号：</div><div class="imfengct"><input type="text" name="stdNum" id="stdNum" style="width:100%; height:30px;"></div>

  
  <div class="imfenco"></div><div class="imfengct"><button type="submit" class="btn btn-danger">保存</button></div>
        </form>
  </div>
  </div>
</body>
</html>
