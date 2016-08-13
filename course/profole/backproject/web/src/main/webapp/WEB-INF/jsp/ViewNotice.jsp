<%-- 
    Document   : ShowNotice
    Created on : 2015-1-11, 11:51:42
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
    <script type="text/javascript" src="js/lhgcore.js"></script>
    <script type="text/javascript" src="js/lhgcalendar.js"></script>
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

            var str = " <table style='margin:auto' ><thead>" +
                    " <tr style='background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';'>" +
                    "   <th>公告名称</th>" +
                    "   <th>类型</th>" +
                    "  <th>发布时间</th>" +
                    "  <th>截止时间</th>" +
                    "  <th>申请数</th>" +
                    "  <th>待审核数</th>" +
                    "  <th>批准数</th>" +
                    " <th>操作</th>" +
                    " </tr>" +
                    "</thead>" +
                    " <tbody>";

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
                var id = showData.id;
                var n_name = showData.n_name;
                var n_type = showData.n_type;
                var n_begintime = showData.n_begintime;
                var n_overtime = showData.n_overtime;
                var n_state = showData.n_state;


                str = str + "<tr style='background:#eff7f9'>" +
                        "<td ><a href='#?id=" + id + "'>" + n_name + "</a></td>" +
                        "<td ><a href='#?id=" + id + "'>" + n_type + "</a></td>" +
                        "<td >" + n_begintime + "</td>" +
                        "<td >" + n_overtime + "</td>" +
                        "<td ></td>" +
                        "<td >" + n_state + "</td>" +
                        "<td></td>" +
                        " <td><div class='probgo'></div><div class='probgt'></div><div class='probgf'></div></td>" +
                        "</tr>";
            }

            str += " </tbody> </table>";
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
            var str = "  <table style='margin:auto' ><thead>" +
                    " <tr style='background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';'>" +
                    "   <th>公告名称</th>" +
                    "   <th>类型</th>" +
                    "  <th>发布时间</th>" +
                    "  <th>截止时间</th>" +
                    "  <th>申请数</th>" +
                    "  <th>待审核数</th>" +
                    "  <th>批准数</th>" +
                    " <th>操作</th>" +
                    " </tr>" +
                    "</thead>" +
                    " <tbody>";
            for (var i = 0; i < k; i++) {
                var showData = data[i];
                var id = showData.id;
                var n_name = showData.n_name;
                var n_type = showData.n_type;
                var n_begintime = showData.n_begintime;
                var n_overtime = showData.n_overtime;
                var n_state = showData.n_state;


                str = str + "<tr style='background:#eff7f9'>" +
                        "<td ><a href='#?id=" + id + "'>" + n_name + "</a></td>" +
                        "<td ><a href='#?id=" + id + "'>" + n_type + "</a></td>" +
                        "<td >" + n_begintime + "</td>" +
                        "<td >" + n_overtime + "</td>" +
                        "<td ></td>" +
                        "<td >" + n_state + "</td>" +
                        "<td></td>" +
                        " <td><div class='probgo'></div><div class='probgt'></div><div class='probgf'></div></td>" +
                        "</tr>";
            }

            str += " </tbody> </table>";
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



    <body>
        <form action="shownotice.htm" method="post" >
            <div class="demo">


                <div class="mtnr">
                    <div class="mtotfj">发布时间：</div>
                    <input type="text" name="n_time" id="n_time"  style="height:40px; width:20%" onclick="J.calendar.get({to: 'n_time,min'});"/>

                    <div class="mtwz">会议状态：
                        <select type="text" id="n_style" name="n_style" style="height:40px; width:20%">
                            <option>国标</option>
                            <option>行标</option>
                            <option>企标</option>
                        </select>
                    </div>
                </div>

                <button type="submit" class="search-submit" > </button>
            </div>


            <table  id="open">
            </table>
            <!--            <div  id="foot" align="center" class="rmiddleb">
                            <input type="button" value="首页" onclick="pagging(10, 'first')"/>
                            <input type="button" value="上一页" onclick="pagging(10, 'pro')"/>
                            <input type="button" value="下一页" onclick="pagging(10, 'next')"/>
                            <input type="button" value="末页" onclick="pagging(10, 'last')"/> 
                        </div>-->
        </form>
    </body>
</html>

