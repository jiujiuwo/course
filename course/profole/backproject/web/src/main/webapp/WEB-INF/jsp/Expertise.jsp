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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css">
            <link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
            <script type="text/javascript" src="js/lhgcore.js"></script>
            <script type="text/javascript" src="js/lhgcalendar.js"></script>
            <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
            <script type="text/javascript" src="js/script.js"></script>
    </head>

    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var data =${jsonarray};
            var str = "  <table style='margin:auto'>" +
                    " <thead>" +
                    "  <tr style='background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';'>" +
                    "     <th>计划号</th>" +
                    "    <th>项目名称</th>" +
                    "    <th>标准名称</th>" +
                    "    <th>开始时间</th>" +
                    "   <th>结束时间</th>" +
                    "   <th>状态</th>" +
                    "  </tr>" +
                    "</thead>" +
                    " <tbody>";
            for (var i = 0; i < data.length; i++) {
                var showData = data[i];
                var c_plantid = showData.c_plantid;
                var n_name = showData.n_name;
                var c_stdname = showData.c_stdname;
                var c_idrealytime = showData.c_idrealytime;
                var c_itovertime = showData.c_itovertime;
                var c_state = showData.c_state;

                str = str + "<tr style='background:#eff7f9'>" +
                        "<td ><a href='experadvice.htm?c_plantid=" + c_plantid + "'>" + c_plantid + "</a></td>" +
                        "<td ><a href='experadvice.htm?c_plantid=" + c_plantid + "'>" + n_name + "</a></td>" +
                        "<td >" + c_stdname + "</td>" +
                        "<td >" + c_idrealytime + "</td>" +
                        "<td >" + c_itovertime + "</td>" +
                        "<td >" + c_state + "</td>" +
                        "</tr>";

            }
            str += "</tbody></table>";
            $("#open").html(str);
        });
    </script>
 

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

    <body bgcolor="#ECECEC">
        <div class="mttop">
            <div class="askpic"></div>
            <!--征求意见<a href="experadvice.htm" target="showframe" class="btn btn-danger" style="float:right; margin-top:5px;">新征求意见</a>-->
        </div>

        <div class="mtotm">
            <form action="expertise.htm" method="post" >
                <div class="mtotf">
                    <div class="mtotfj">计划号：</div> <input id="c_plantid" name="c_plantid" type="text" style="height:25px; width:50%"/>
                    <!--<input type="button" name="butten" onclick="check()" value="确定"/>-->
                </div>
                <div class="mtoto">
                    <div class="mtotfj">标准名称：</div>
                    <input id="c_stdname" name="c_stdname" type="text" style="height:25px; width:50%"/>
                    <!--<input type="button" name="butten" onclick="check()" value="确定"/>-->
                </div>

                <div class="mtoto">
                    <!--征求意见截止时间-->
                    <span class="mtotfj">时间：</span>

                    <input type="text" id="c_itovertime" name="c_itovertime"/>
                </div>
                <div class="mtoto">
                    <a href="javascript:void(0)" onclick=add("2013")><input type="text" id="2013" name="2013" value="2013" style="height:25px; width:25%" readonly></a>
                    <a href="javascript:void(0)" onclick=add("2014")><input type="text" id="2014" name="2014" value="2014" style="height:25px; width:25%"  readonly></a>
                    <input type="text" id="2015" name="2015" onblur="add('2015')" style="height:25px; width:25%"/>
                </div>
                <div class="mtoto">
                    <button type="submit" class="search-submit" > </button>
                </div>
                <span  id="open">
                </span>
            </form>
        </div>
    </body>
</html>

