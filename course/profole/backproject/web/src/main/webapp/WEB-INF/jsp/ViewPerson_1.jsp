<%-- 
    Document   : ViewPerson
    Created on : 2014-11-25, 10:38:03
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>管理员页面显示所有用户</title>
        <link href="css/homePage.css" rel="stylesheet" type="text/css" />

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
                var str = "<tr>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 0, 'int')' style='cursor: pointer;'>用户名</th>" +
                        " <th onclick='tablewwwzzjsnet('table_zzjs_net', 1)' style='cursor: pointer;'>真实姓名</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 2)' style='cursor: pointer;'>公司名称</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 3)' style='cursor: pointer;'>职称/职务</th>" +
//                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 4)' style='cursor: pointer;'>所在地区</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 7)' style='cursor: pointer;'>email</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 5, 'date')' style='cursor: pointer;'>固定电话/手机号码</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 8, 'date')' style='cursor: pointer;'>操作</th>" +
                        "</tr>";
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
                    str = str + "<tr class='show'>" +
                            "<td width='10%'><span id='sid'><a href='altuser.htm?id=" + num + "'>" + username + "</a></span></td>" +
                            "<td width='10%'><span id='sname'><a href='altuser.htm?id=" + num + "'>" + name + "</a></span></td>" +
                            "<td width='10%'><span id='oname'>" + company+ "</span></td>" +
                            "<td width='10%'><span id='sphone'>" + position + "/" + thetitle + "</span></td>" +
//                            "<td width='10%'><span id='district'>" + district + "</span></td>" +
                            "<td width='10%'><span id='email'>" + email + "</span></td>" +
                            "<td width='10%'><span id='tellphone'>" + mobile + "/" + tellphone + "</span></td>" +
                             "<td width='10%'><span id='username'><a href='deluser.htm?id=" + num + "'>删除</a></span></td>"+
                            "</tr>";

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
                var str = "<tr>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 0, 'int')' style='cursor: pointer;'>用户名</th>" +
                        " <th onclick='tablewwwzzjsnet('table_zzjs_net', 1)' style='cursor: pointer;'>真实姓名</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 2)' style='cursor: pointer;'>公司名称</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 3)' style='cursor: pointer;'>职称/职务</th>" +
//                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 4)' style='cursor: pointer;'>所在地区</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 7)' style='cursor: pointer;'>email</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 5, 'date')' style='cursor: pointer;'>固定电话/手机号码</th>" +
                        "<th onclick='tablewwwzzjsnet('table_zzjs_net', 8, 'date')' style='cursor: pointer;'>操作</th>" +
                        "</tr>";

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
                    str = str + "<tr class='show'>" +
                            "<td width='10%'><span id='username'><a href='altuser.htm?id=" + num + "'>" + username + "</a></span></td>" +
                            "<td width='10%'><span id='name'><a href='altuser.htm?id=" + num + "'>" + name + "</a></span></td>" +
                            "<td width='10%'><span id='company'>" + company +"</span></td>" +
                            "<td width='10%'><span id='sphone'>" + position + "/" + thetitle + "</span></td>" +
//                            "<td width='10%'><span id='district'>" + district + "</span></td>" +
                            "<td width='10%'><span id='email'>" + email + "</span></td>" +
                            "<td width='10%'><span id='tellphone'>" + mobile + "/" + tellphone + "</span></td>" +
                           "<td width='10%'><span id='username'><a href='deluser.htm?id=" + num + "'>删除</a></span></td>"+
                            "</tr>";

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

    <body>
        <div class="main">
            <div class="top">
                <div class="topleft">
                    <ul style="list-style-type:none">
                        <a href="homePage.htm"><li>首页</li></a>
                        <a href="managerPage.htm"><li>管理员首页</li></a>
                    </ul>
                </div>
                <div class="topright">
                    <ul style="list-style-type:none">
                        <a href=""><li>admin</li></a>
                        <a href="homePage.htm"><li>退出</li></a>	
                    </ul>
                </div>
            </div>
            <div class="smiddle">
                <div class="smiddlet">
                    <div class="smiddleright">
                        <div class="searchb mt1">
                            <div class="search w960 center">
                                <form action="checkuser.htm" method="post" commandName="user">
                                    <div class="form">
                                        <div class="form_left">
                                            <div class="form_right">
                                                <input name="check" type="text" autocomplete="off" class="search-keyword" id="check" value="按照姓名，单位，省份证号码查询"/>
                                                <input type="submit" class="search-submit" value="搜索"/>
                                            </div>
                                        </div>                
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="smiddlem">
                    <style type="text/css">
                        th, td {
                            text-align:center;
                            width:170px;
                        }
                        th {
                            background-color:#007D28;
                            color:#ffffff;
                        }
                        #open{
                            width:100%;
                            
                        }
                    </style>
                    <div class="wwwzzjsnet">
                        <table id="table_zzjs_net" >
                            <thead>

                                <div id="open" >
                                </div>
                            </thead>




                        </table>
                          <div id="sort"> 
              <td><input type=button name=B1 value="添加人员信息" onClick="addto();" style="border-radius:10px; width:120px; height:20px; background:#0C0;"></td>
            </div>

                    </div>
                </div>
                <div  id="foot" align="center" class="rmiddleb">
                    <input type="button" value="首页" onclick="pagging(10, 'first')"/>
                    <input type="button" value="上一页" onclick="pagging(10, 'pro')"/>
                    <input type="button" value="下一页" onclick="pagging(10, 'next')"/>
                    <input type="button" value="末页" onclick="pagging(10, 'last')"/> 
                </div>

            </div>	
            <div class="bottom">
                <div class="bottomcss">2014 科迪智标 京ICP证000000号</div>
            </div>
        </div>
    </body>
</html>
