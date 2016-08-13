<%-- 
    Document   : backstageleft
    Created on : 2015-2-27, 11:24:27
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link href="css/backstage_style.css" rel="stylesheet" type="text/css">
<link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/showdate.js"></script>
<script type="text/javascript">
$(function(){
$(".subNav").click(function(){
			$(this).toggleClass("currentDd").siblings(".subNav").removeClass("currentDd")
			$(this).toggleClass("currentDt").siblings(".subNav").removeClass("currentDt")//下拉列表打开箭头向下
			
			// 修改数字控制速度， slideUp(500)控制卷起速度
			$(this).next(".navContent").slideToggle(500).siblings(".navContent").slideUp(500);
	})	
})
</script>
</head>

<body>

<div class="otmiddle">
  <div class="subNavBoxo">
    <div class="otuser">
      <div class="otceng">
        <div class="othead"><img  class="img-circle"src="image/toux.jpg"></div>
        <div class="otnews">
          <div class="dropdown"> <a  class="dropdown-toggle" id="dropdownMenu1" 
      data-toggle="dropdown"> Admin Zhao <span class="caret"></span> </a>欢迎登录！
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
              <li role="presentation"> <a role="menuitem" tabindex="-1" href="#">aaa</a> </li>
              <li role="presentation"> <a role="menuitem" tabindex="-1" href="#">bbb</a> </li>
              <li role="presentation"> <a role="menuitem" tabindex="-1" href="#"> ccc </a> </li>
              <li role="presentation" class="divider"></li>
              <li role="presentation"> <a role="menuitem" tabindex="-1" href="#">ddd</a> </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="subNav currentDd  currentDt"><img src="images/house.png" width="15" height="15">　　用户首页</div>
    <div class="subNav "><img src="images/pc.png" width="15" height="15">　　业务管理</div>
    <ul class="navContent navContento ">
      <li><a href="shownotice.htm" target="showframe" >立项公告</a></li>
      <li><a href="noticeApproval.htm" target="showframe">立项审批</a></li>
      <li><a href="draftStandardManage.htm" target="showframe">起草标准管理</a></li>
    </ul>
    <div class="subNav "><img src="images/horn.png" width="15" height="15">　　通知通告</div>
    <ul class="navContent navContento">
      <li><a href="meetshow.htm" target="showframe">会议通告</a></li>
      <li><a href="expertise.htm" target="showframe">征求意见通告</a></li>
    </ul>
    <div class="subNav "><img src="images/contacts.png" width="15" height="15">　　用户管理</div>
    <ul class="navContent navContento">
      <li><a href="showuser.htm" target="showframe">人员管理</a></li>
      <li><a href="showorg.htm" target="showframe">机构管理</a></li>
    </ul>
    <div class="subNav "><img src="images/setup.png" width="15" height="15">　　系统管理</div>
    <ul class="navContent navContento">
      <li><a href="fileexport.htm" target="showframe">导入/导出</a></li>
      <li><a href="backups" target="showframe">备份/恢复</a></li>
      <li><a href="categorycollection.htm" target="showframe">统计</a></li>
    </ul>
  </div>
</div>
</body>
</html>

