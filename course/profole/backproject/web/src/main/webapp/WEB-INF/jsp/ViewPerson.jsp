<%-- 
    Document   : ViewPerson
    Created on : 2014-11-25, 10:38:03
    Author     : liaojm
--%>

<%@page import="com.lifeful.resource.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>无标题文档</title>
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css">
        <link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
    </head>
    <script language="JavaScript" src="js/jquery.min.js" charset="utf-8"></script>
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
            var str = "<thead><tr style='background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';'>" +
                    "<th >用户名</th>" +
                    " <th >真实姓名</th>" +
                    "<th >公司名称</th>" +
                    "<th >职称/职务</th>" +
                    "<th >email</th>" +
                    "<th>固定电话/手机号码</th>" +
                    "<th>操作</th>" +
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
                var num = showData.id;
                var username = showData.username;
                var name = showData.name;
                var thetitle = showData.thetitle;
                var position = showData.position;
                var email = showData.email;
                var department = showData.department;
                var company = showData.company;
                var district = showData.district;
                var tellphone = showData.tellphone;
                var mobile = showData.mobile;
                str = str + "   <tbody><tr style='background:#eff7f9'>" +
                        "<td ><span id='sid'><a href='altuser.htm?id=" + num + "'>" + username + "</a></span></td>" +
                        "<td ><span id='sname'><a href='altuser.htm?id=" + num + "'>" + name + "</a></span></td>" +
                        "<td ><span id='oname'>" + company + "</span></td>" +
                        "<td ><span id='sphone'>" + position + "/" + thetitle + "</span></td>" +
                        "<td ><span id='email'>" + email + "</span></td>" +
                        "<td ><span id='tellphone'>" + mobile + "/" + tellphone + "</span></td>" +
                        "<td ><span id='username'><a href='deluser.htm?id=" + num + "'>删除</a></span></td>" +
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
            var str = "<thead><tr style='background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';'>" +
                    "<th >用户名</th>" +
                    " <th >真实姓名</th>" +
                    "<th >公司名称</th>" +
                    "<th >职称/职务</th>" +
                    "<th >email</th>" +
                    "<th>固定电话/手机号码</th>" +
                    "<th>操作</th>" +
                    "</tr></thead>";

            for (var i = 0; i < k; i++) {
                var showData = data[i];
                var num = showData.id;
                var username = showData.username;
                var name = showData.name;
                var thetitle = showData.thetitle;
                var position = showData.position;
                var email = showData.email;
                var department = showData.department;
                var company = showData.company;
                var district = showData.district;
                var tellphone = showData.tellphone;
                var mobile = showData.mobile;
                str = str + "   <tbody><tr style='background:#eff7f9'>" +
                        "<td ><span id='sid'><a href='altuser.htm?id=" + num + "'>" + username + "</a></span></td>" +
                        "<td ><span id='sname'><a href='altuser.htm?id=" + num + "'>" + name + "</a></span></td>" +
                        "<td ><span id='oname'>" + company + "</span></td>" +
                        "<td ><span id='sphone'>" + position + "/" + thetitle + "</span></td>" +
                        "<td ><span id='email'>" + email + "</span></td>" +
                        "<td ><span id='tellphone'>" + mobile + "/" + tellphone + "</span></td>" +
                        "<td ><span id='username'><a href='deluser.htm?id=" + num + "'>删除</a></span></td>" +
                        "</tr></tbody>";

            }
            $("#open").html(str);
        });
    </script>
    <script>
        function addto() {
            location.href = "adduser.htm";
        }
    </script>
</head>
<body bgcolor="#ececec">
    <div class="mttop">
        <div class="perpic"></div>
        人员管理</div>
    <div class="memiddle">
        <form action="checkuser.htm" method="post" commandName="user" style="width:487px;height:34px; float:left">
            <div id="searchTxt" class="searchTxt" onMouseOver="this.className = 'searchTxt searchTxtHover';" onMouseOut="this.className = 'searchTxt';">
                <div class="radius" style="top:-2px;"></div>
                <div class="radius" style="top:31px;"></div>
                <div class="searchMenu"> </div>
                  <input name="check" type="text"  style="font-family: '微软雅黑';width:100%; padding-left:5px; border:none; height:30px;" id="check" placeholder="按照姓名、单位、身份证号码查询..."/>
                   <!--<input type="submit" id="submit" name="submit" value="搜索"/>-->
                <!--<input name="w" type="text" placeholder="按照姓名、单位、身份证号码查询..." style="font-family: '微软雅黑';width:100%; padding-left:5px; border:none; height:30px;" />-->
            </div>
            <div class="searchBtn">
                <button id="searchBtn" type="submit"></button>
            </div>
        </form>
        <a href="adduser.htm" class="btn btn-danger" >添加</a>
    </div>
    <div class="mebuttom">
        <table style="margin:auto" id="open">
            <!--                <thead>
                                <tr style="background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';">
                                    <th>用户名</th>
                                    <th>真实姓名</th>
                                    <th>公司名称</th>
                                    <th>职称/职务</th>
                                    <th>email</th>
                                    <th>手机号码</th>
                                    <th>固定号码</th>
                                    <th>操作</th>
                                    <th>人员类别</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr style="background:#eff7f9">
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td><td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
            
                            </tbody>-->
        </table> 
        <!--            <div class="mtfy">
                        <ul class="pagination">
                            <li class="fypev"><a href="#">&#8249;上一步</a></li>
                            <li class="active fynext" ><a href="#">1</a></li>
        
                            <li class="fypev"><a href="#">下一步&#8250;</a></li>
                        </ul>
                    </div>-->
        <div   class="mtfy">
            <ul class="pagination">
                <li class="fypev"><input type="button" value="首页" onclick="pagging(10, 'first')"/></li>
            <li class="fypev"><input type="button" value="上一页" onclick="pagging(10, 'pro')"/></li>
            <li class="fypev"><input type="button" value="下一页" onclick="pagging(10, 'next')"/></li>
            <li class="fypev"><input type="button" value="末页" onclick="pagging(10, 'last')"/></li>
            </ul>
        </div>
    </div>
</body>
</html>

