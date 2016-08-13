<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <style type="text/css">
            body{margin: 0px; }
            #head {background-color: #EAEAEA;height:80px;margin:auto 15px ;}
            #content {height:390px;margin-left:36px;}
            #line{margin-top:-10px;margin-left:15px;margin-right:15px}
            #condition{height:90px}
            .results-con{padding:20px 10px;height:350px;border:1px solid #999;border-radius:5px;margin-right:36px}
            .results-con span{width:98%;display:block;background-color: #EAEAEA;}
            #foot{ height: 20px; margin-top: 150px;}
            table{height:50px;border-color:#C0C0C0; width: 98%; }
            td{text-align:center;}
            .show span {background:none}

        </style>
    </head>
    <link href="css/homePage.css" rel="stylesheet" type="text/css" />
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

            var str = "<tr bgcolor='#CCCCCC'>" +
                    "<th width='10%'>机构id</th>" +
                    " <th width='20%'>机构名</th>" +
                    "<th width='20%'>机构代表</th>" +
                    "<th width='20%'>机构联系人</th>" +
                    "<th width='20%'>地址</th>" +
//                    "<th width='20%'>是否为法人机构</th>" +
                    "<th width='20%'>操作</th>" +
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
                var dealerid = showData.dealerid;
                var username = showData.username;
                var contact = showData.contact;
                var owner = showData.owner;
                var address = showData.address;
//                var isorg = showData.isorg;


                str = str + "<tr class='show'>" +
                        "<td width='10%'><span id='oid'><a href='altorg.htm?dealerid=" + dealerid + "'>" + dealerid + "</a></span></td>" +
                        "<td width='10%'><span id='oname'><a href='altorg.htm?dealerid=" + dealerid + "'>" + username + "</a></span></td>" +
//                        "<td width='10%'><span id='aliasname'>" + aliasname + "</span></td>" +
                        "<td width='10%'><span id='owner'>" + owner + "</span></td>" +
                        "<td width='10%'><span id='contact'>" + contact + "</span></td>" +
                        "<td width='10%'><span id='address'>" + address + "</span></td>" +
//                        "<td width='10%'><span id='parent'>" + parent + "</span></td>" +
//                        "<td width='10%'><span id='isorg'>" + isorg + "</span></td>" +
                        "<td width='10%'><span id='isorg'><a href='delorg.htm?dealerid=" + dealerid + "'>删除</a></span></td>" +
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
           var str = "<tr bgcolor='#CCCCCC'>" +
                    "<th width='10%'>机构id</th>" +
                    " <th width='20%'>机构名</th>" +
                    "<th width='20%'>机构代表</th>" +
                    "<th width='20%'>机构联系人</th>" +
                    "<th width='20%'>地址</th>" +
//                    "<th width='20%'>是否为法人机构</th>" +
                    "<th width='20%'>操作</th>" +
                    "</tr>";
            for (var i = 0; i < k; i++) {
                var showData = data[i];
                var dealerid = showData.dealerid;
                var username = showData.username;
                var contact = showData.contact;
                var owner = showData.owner;
                var address = showData.address;
//                var isorg = showData.isorg;


                str = str + "<tr class='show'>" +
                        "<td width='10%'><span id='oid'><a href='altorg.htm?dealerid=" + dealerid + "'>" + dealerid + "</a></span></td>" +
                        "<td width='10%'><span id='oname'><a href='altorg.htm?dealerid=" + dealerid + "'>" + username + "</a></span></td>" +
//                        "<td width='10%'><span id='aliasname'>" + aliasname + "</span></td>" +
                        "<td width='10%'><span id='owner'>" + owner + "</span></td>" +
                        "<td width='10%'><span id='contact'>" + contact + "</span></td>" +
                        "<td width='10%'><span id='address'>" + address + "</span></td>" +
//                        "<td width='10%'><span id='parent'>" + parent + "</span></td>" +
//                        "<td width='10%'><span id='isorg'>" + isorg + "</span></td>" +
                        "<td width='10%'><span id='isorg'><a href='delorg.htm?dealerid=" + dealerid + "'>删除</a></span></td>" +
                        "</tr>";
            }
            $("#open").html(str);
        });
    </script>


    <script>
        function addto() {
            location.href = "addorg.htm";
        }
    </script>
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
                            <!--                            <div class="search w960 center">
                                                            <form action="checkuser.htm" method="post" commandName="user">
                                                                <div class="form">
                                                                    <div class="form_left">
                                                                        <div class="form_right">
                                                                            <input name="check" type="text" autocomplete="off" class="search-keyword" id="check" value="按照姓名，单位，省份证号码查询"/>
                                                                            <button type="submit" class="search-submit" onclick="return checkinput()">
                                                                                搜索</button>
                                                                        </div>
                                                                    </div>                
                                                                </div>
                                                            </form>
                                                        </div>-->
                        </div>
                    </div>

                </div>
                <div class="smiddlem">
                    <style type="text/css">
                        th, td {
                            padding:6px;
                            text-align:center;
                        }
                        th {
                            background-color:#007D28;
                            color:#ffffff;
                        }
                    </style>
                    <div class="wwwzzjsnet">
                        <table id="table_zzjs_net" >
                            <thead>

                            <div id="open" style="margin:auto; width:1000px">
                            </div>
                            </thead>




                        </table>
                        <div id="sort"> 
                            <td><input type=button name=B1 value="添加机构信息" onClick="addto();" style="border-radius:10px; width:120px; height:20px; background:#0C0;"></td>
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
