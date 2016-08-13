<%-- 
    Document   : Expertise
    Created on : 2015-1-11, 11:51:42
    Author     : liaojm
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
    <head>
        <meta charset="utf-8">
        <title>专家意见收集，筛选相应标准</title>
        <link href="css/AddNotice.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/jquery-1.7.2.min.js" ></script>
    </head>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var data =${jsonarray};
            var str = "<tr bgcolor='#CCCCCC'>" +
                    "<th width='10%'>计划号</th>" +
                    " <th width='20%'>项目名称</th>" +
                    "<th width='20%'>标准名称</th>" +
                    "<th width='20%'>开始时间</th>" +
                    "<th width='20%'>结束时间</th>" +
                    "<th width='20%'> 状态</th>" +
                    "</tr>";
            for (var i = 0; i < data.length; i++) {
                var showData = data[i];
                var c_plantid = showData.c_plantid;
                var n_name = showData.n_name;
                var c_stdname = showData.c_stdname;
                var c_idrealytime = showData.c_idrealytime;
                var c_itovertime = showData.c_itovertime;
                var c_state = showData.c_state;
//  c_plantid, n_name,c_stdname,c_idrealytime,c_itovertime,c_state

                str = str + "<tr class='show'>" +
                        "<td width='10%'><span id='n_name'><a href='experadvice.htm?c_plantid=" + c_plantid + "'>" + c_plantid + "</a></span></td>" +
                        "<td width='10%'><span id='n_type'>" + n_name + "</span></td>" +
                        "<td width='10%'><span id='n_begintime'>" + c_stdname + "</span></td>" +
                        "<td width='10%'><span id='n_overtime'>" + c_idrealytime + "</span></td>" +
                        "<td width='10%'><span id='address'></span>" + c_itovertime + "</td>" +
                        "<td width='10%'><span id='n_state'></span>" + c_state + "</td>" +
                        "</tr>";
            }
            $("#open").html(str);
        });
    </script>
    <!--    <script>
            var xmlhttp;
            //用户名检测
            function check() {
                if (window.XMLHttpRequest) {
                    xmlhttp = new XMLHttpRequest();
                } else {
                    xmlhttp = new ActiveXobject("Microsoft.XMLHTTP");
                }
                var c_plantid = document.getElementById("c_plantid").value;
                var c_stdname = document.getElementById("c_stdname").value;
                var c_itovertime = document.getElementById("c_itovertime").value;
                xmlhttp.onreadystatechange = clback;
                xmlhttp.open("post", "checkun.htm?c_plantid=" + c_plantid + "&c_stdname=" + c_stdname + "&c_itovertime=" + c_itovertime, true);
                xmlhttp.send();
            }
    
            function clback() {
                if (xmlhttp.readyState == 4) {
                    var text = xmlhttp.responseText;
                    alert(text);
                    if (text == "true") {
                        document.getElementById("msg").innerHTML = "";
                    } else {
                        document.getElementById("msg").innerHTML = "用户名不存在";
                    }
                }
            }
        </script>-->

    <script type="text/javascript">
        function add(s) {
            if (s == '2013') {
                var c_itovertime = document.getElementById("2013").value;
                document.getElementById("c_itovertime").value = c_itovertime;
            }
            if (s == '2014') {
                var c_itovertime = document.getElementById("2014").value;
                document.getElementById("c_itovertime").value = c_itovertime;
            }
            if (s == '2015') {
                var c_itovertime = document.getElementById("2015").value;
                document.getElementById("c_itovertime").value = c_itovertime;
            }
        }
    </script>

    <body>

        <div class="demo">

            <form action="expertise.htm" method="post" >
                <table>
                    <div>
                    <span>计划号：</span>
                    </div>
                    <div><input type="text" id="c_plantid" name="c_plantid"/></div>
                    <!--<input type="button" name="butten" onclick="check()" value="确定"/>-->
                    
                    <div>
                        <span>标准名称：</span>
                    </div>
                    <div>
                    <input type="text" id="c_stdname" name="c_stdname"/>
                    <!--<input type="button" name="butten" onclick="check()" value="确定"/>-->
                    </div>
                    
                    <div>
                        <!--征求意见截止时间-->
                        <span>时间：</span>
                    </div>
                    <div>
                    <input type="text" id="c_itovertime" name="c_itovertime"/>
                    </div>
                    
                    <div>
                    <a href="javascript:void(0)" onclick=add("2013")><input type="text" id="2013" name="2013" value="2013" readonly></a>
                    <a href="javascript:void(0)" onclick=add("2014")><input type="text" id="2014" name="2014" value="2014" readonly></a>
                    <input type="text" id="2015" name="2015" onblur="add('2015')"/>
                    </div>
                    
                    <span  id="open">
                    </span>
                    <div class="altbutton">
                        <input type="submit" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px; float:left;" value="修改" />
                        <input type="reset" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px; float:right;" value="重置" />
                    </div>
                </table>
            </form>

        </div>

        <!--<script type="text/javascript" src="js/shanxuan.js"></script>-->

        <!--        <div  id="foot" align="center" class="rmiddleb">
                    <input type="button" value="首页" onclick="pagging(10, 'first')"/>
                    <input type="button" value="上一页" onclick="pagging(10, 'pro')"/>
                    <input type="button" value="下一页" onclick="pagging(10, 'next')"/>
                    <input type="button" value="末页" onclick="pagging(10, 'last')"/> 
                </div>-->
    </body>
</html>

