<%@page import="com.lifeful.resource.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="utf-8">
        <title>无标题文档</title>
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css">
        <link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
    </head>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var pageIndex = 0;     //页面索引初始值   
        var pageSize = 10; //每页显示的条数
        var data =${jsonarray};
        var id = data.length;//数据条数
        var currentPage = 0;//当前页面
        var pageCount;//总页数
        var page = "first";
        function pagging(pageSize, page) {
            var start = 0;
            var end = 0;

            var str = " <thead><tr style='background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';'>" +
                    "<th >机构id</th>" +
                    " <th>机构名</th>" +
                    "<th >机构代表</th>" +
                    "<th >机构联系人</th>" +
                    "<th >地址</th>" +
                    "<th >操作</th>" +
                    "</tr></thead>";
            if (id % 10 == 0) {
                pageCount = id / 10;
            } else {
                pageCount = parseInt(id / 10) + 1;
            }

            if (page == "first") {
                currentPage = 1;
                if (pageCount > 1) {
                    start = 0;
                    end = 10;

                } else {
                    start = 0;
                    end = id;
                }
            }

            if (page == "pro") {
                if (currentPage < 2) {
                    return;
                } else {
                    currentPage = currentPage - 1;
                }
                start = (currentPage - 1) * 10;
                end = start + 10;
            }
            if (page == "next") {
                if ((currentPage + 1) > pageCount) {
                    alert("没有下一页");
                    return;
                } else if ((currentPage + 1) < pageCount) {

                    start = currentPage * 10;
                    end = start + 10;
                    currentPage = currentPage + 1;
                } else if ((currentPage + 1) == pageCount) {
                    start = currentPage * 10;
                    end = id;
                    currentPage = currentPage + 1;

                }
            }
            if (page == "last") {
                start = (pageCount - 1) * 10;
                end = id;
            }


            for (var i = start; i < end; i++) {
                var showData = data[i];
                var dealerid = showData.dealerid;
                var username = showData.username;
                var contact = showData.contact;
                var owner = showData.owner;
                var address = showData.address;


                str = str + "<tbody><tr style='background:#eff7f9'>" +
                        "<td ><span id='oid'><a href='altorg.htm?dealerid=" + dealerid + "'>" + dealerid + "</a></span></td>" +
                        "<td ><span id='oname'><a href='altorg.htm?dealerid=" + dealerid + "'>" + username + "</a></span></td>" +
                        "<td ><span id='owner'>" + owner + "</span></td>" +
                        "<td ><span id='contact'>" + contact + "</span></td>" +
                        "<td ><span id='address'>" + address + "</span></td>" +
                        "<td><span id='isorg'><a href='delorg.htm?dealerid=" + dealerid + "'><div class='mebg'></div></a></span></td>" +
                        "</tr></tbody>";
            }
            $("#open").html(str);
        }

    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            var data =${jsonarray};
            var k = 0;
            if (data.length <= 10) {
                k = data.length;
            } else {
                k = 10;
            }
            var str = " <thead><tr style='background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';'>" +
                    "<th >机构id</th>" +
                    " <th>机构名</th>" +
                    "<th >机构代表</th>" +
                    "<th >机构联系人</th>" +
                    "<th >地址</th>" +
                    "<th >操作</th>" +
                    "</tr></thead>";
            for (var i = 0; i < k; i++) {
                var showData = data[i];
                var dealerid = showData.dealerid;
                var username = showData.username;
                var contact = showData.contact;
                var owner = showData.owner;
                var address = showData.address;
//                var isorg = showData.isorg;


                str = str + "<tbody><tr style='background:#eff7f9'>" +
                        "<td ><span id='oid'><a href='altorg.htm?dealerid=" + dealerid + "'>" + dealerid + "</a></span></td>" +
                        "<td ><span id='oname'><a href='altorg.htm?dealerid=" + dealerid + "'>" + username + "</a></span></td>" +
                        "<td ><span id='owner'>" + owner + "</span></td>" +
                        "<td ><span id='contact'>" + contact + "</span></td>" +
                        "<td ><span id='address'>" + address + "</span></td>" +
                        "<td><span id='isorg'><a href='delorg.htm?dealerid=" + dealerid + "'><div class='mebg'></div></a></span></td>" +
                        "</tr></tbody>";
            }
            $("#open").html(str);
        });
    </script>



<body bgcolor="#ececec">
<div class="mttop">
  <div class="mepic"></div>
  机构管理</div>
<div class="memiddle">
  <form method="get" name="" target="_blank" style="width:487px;height:34px; float:left">
    <div id="searchTxt" class="searchTxt" onMouseOver="this.className='searchTxt searchTxtHover';" onMouseOut="this.className='searchTxt';">
      <div class="radius" style="top:-2px;"></div>
      <div class="radius" style="top:31px;"></div>
      <div class="searchMenu"> </div>
      <input name="w" type="text" placeholder="按照机构名、联系人、机构地址查询..." style="font-family: '微软雅黑';width:100%; padding-left:5px; border:none; height:30px;" />
    </div>
    <div class="searchBtn">
      <button id="searchBtn" type="submit"></button>
    </div>
  </form>
  <a href="addorg.htm" target="showframe" class="btn btn-danger" >添加</a>
</div>
<div class="mebuttom">
  <table style="margin:auto">
    <div id="open" >
                </div>
  </table> 
  <div  class="mtfy">
                <ul class="pagination">
                    <li class="fypev"><input type="button" value="首页" onclick="pagging(10, 'first')"/></li>
                    <li class="fypev">  <input type="button" value="上一页" onclick="pagging(10, 'pro')"/></li>
                    <li class="fypev"> <input type="button" value="下一页" onclick="pagging(10, 'next')"/></li>
                    <li class="fypev"> <input type="button" value="末页" onclick="pagging(10, 'last')"/> </li>
                </ul>
            </div>
</div>
</body>
</html>

