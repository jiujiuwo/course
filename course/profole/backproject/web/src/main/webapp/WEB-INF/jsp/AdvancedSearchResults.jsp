<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <link href="css/homePage.css" rel="stylesheet" type="text/css" />
    </head>
   <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var data =${jsonarray};
           
            var str = "<tr bgcolor='#CCCCCC'>" +
                    "<th width='10%'>序号</th>" +
                    " <th width='20%'>标准号</th>" +
                    "<th width='20%'>英文标准名称</th>" +
                    "<th width='20%'>中文标准名称</th>" +
                    "<th width='20%'>状态</th>" +
                    "</tr>";

            for (var i = 0; i < data.length; i++) {
                var showData = data[i];
                var id = showData.stdId;
                var path = showData.stdPath;
                var StdNum = showData.stdNum;
                var StdEngName = showData.stdEngName;
                var StdChaName = showData.stdChaName;
                var State = showData.state;
                str = str + "<tr class='show'>" +
                        "<td width='10%' height='40px'><span id='id'>" + (i + 1) + "</span></td>" +
                        "<td width='10%'><span id='StdNum'><a href='getRootStruct.htm?id=" + id + "&path=" + path + "'>" + StdNum + "</a></span></td>" +
                        "<td width='10%'><span id='StdChaName'>" + StdEngName + "</span></td>" +
                        "<td width='10%'><span id='StdEngName'>" + StdChaName + "</span></td>" +
                        "<td width='10%'><span id='State'>" + State + "</span></td>" +
                        "</tr>";
            }
            $("#table").html(str);
        });
    </script>
            
    <body>
        <div class="main">
            <div class="top">
                <div class="topleft">
                    <ul style="list-style-type:none">
                        <a href="AdvancedSearch.htm"><li>高级检索</li></a>
                        <a href="getCategory.htm"><li>分类检索</li></a>
                        <a href="updateWarehouse.htm"><li>更新资源仓库</li></a>
                    </ul>
                </div>
                <div class="topright">
                    <ul style="list-style-type:none">
                        <a href=""><li>admin</li></a>
                        <a href=""><li>系统管理</li></a>
                        <a href="homePage.htm"><li>退出</li></a>	
                    </ul>
                </div>
            </div>
            <div class="amiddle">
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
                        <table  border="1" cellspacing="0" cellpadding="1" align="center" id="table">


                        </table>
                    </div>
                </div>
                <div class="rmiddleb">
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
        <script>//
//            pagging(pageSize,page);
//            $("#table").html(str); 
//        </script>
</html>
