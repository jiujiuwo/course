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
        <link href="css/AddNotice.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/jquery-1.7.2.min.js" ></script>
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
                    "<th width='10%'>公告名称</th>" +
                    " <th width='20%'>公告类型</th>" +
                    "<th width='20%'>发布时间</th>" +
                    "<th width='20%'>截止时间</th>" +
                    "<th width='20%'>申请</th>" +
                    "<th width='20%'> 状态</th>" +
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
                var id = showData.id;
                var n_name = showData.n_name;
                var n_type = showData.n_type;
                var n_begintime = showData.n_begintime;
                var n_overtime = showData.n_overtime;
                var n_state = showData.n_state;


                str = str + "<tr class='show'>" +
                        "<td width='10%'><span id='n_name'><a href='altorg.htm?id=" + id + "'>" + n_name + "</a></span></td>" +
                        "<td width='10%'><span id='n_type'><a href='altorg.htm?id=" + id + "'>" + n_type + "</a></span></td>" +
                        "<td width='10%'><span id='n_begintime'>" + n_begintime + "</span></td>" +
                        "<td width='10%'><span id='n_overtime'>" + n_overtime + "</span></td>" +
                        "<td width='10%'><span id='address'></span></td>" +
                        "<td width='10%'><span id='n_state'></span>" + n_state + "</td>" +
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
                    "<th width='10%'>公告名称</th>" +
                    " <th width='20%'>公告类型</th>" +
                    "<th width='20%'>发布时间</th>" +
                    "<th width='20%'>截止时间</th>" +
                    "<th width='20%'>申请</th>" +
                    "<th width='20%'> 状态</th>" +
                    "</tr>";
            for (var i = 0; i < k; i++) {
                var showData = data[i];
                var id = showData.id;
                var n_name = showData.n_name;
                var n_type = showData.n_type;
                var n_begintime = showData.n_begintime;
                var n_overtime = showData.n_overtime;
                var n_state = showData.n_state;


                str = str + "<tr class='show'>" +
                        "<td width='10%'><span id='n_name'><a href='altorg.htm?id=" + id + "'>" + n_name + "</a></span></td>" +
                        "<td width='10%'><span id='n_type'><a href='altorg.htm?id=" + id + "'>" + n_type + "</a></span></td>" +
                        "<td width='10%'><span id='n_begintime'>" + n_begintime + "</span></td>" +
                        "<td width='10%'><span id='n_overtime'>" + n_overtime + "</span></td>" +
                        "<td width='10%'><span id='address'></span></td>" +
                        "<td width='10%'><span id='n_state'></span>" + n_state + "</td>" +
                        "</tr>";
            }
            $("#open").html(str);
        });
    </script>



    <body>

        <div class="demo">

            <div class="sx">
                <span>起止时间：</span>
                <a rel="2012" class="sx_child" name="n_time" href="javascript:;">2012</a>
                <a rel="2013" class="sx_child" name="n_time" href="javascript:;">2013</a>
                <a rel="2014" class="sx_child" name="n_time" href="javascript:;">2014</a>
            </div>

            <div class="sx">
		<span>类型：</span>
		<a rel="1" class="sx_child" name="n_style" href="javascript:;">国标</a>
		<a rel="2" class="sx_child" name="n_style" href="javascript:;">行标</a>
		<a rel="3" class="sx_child" name="n_style" href="javascript:;">企标</a>
	</div>
            <div class="zj">
                <span>删选结果：</span>
                <p class="qcqb">清除全部</p>
            </div>

        </div>

        <script type="text/javascript" src="js/shanxuan.js"></script>
        <script type="text/javascript">
        $('.sx').sx({
            nuv: ".zj", //筛选结果
            zi: "sx_child", //所有筛选范围内的子类
            qingchu: '.qcqb', //清除全部
            over: 'on'//选中状态样式名称
        });
        </script>
        <table  id="open">
        </table>
        <div  id="foot" align="center" class="rmiddleb">
            <input type="button" value="首页" onclick="pagging(10, 'first')"/>
            <input type="button" value="上一页" onclick="pagging(10, 'pro')"/>
            <input type="button" value="下一页" onclick="pagging(10, 'next')"/>
            <input type="button" value="末页" onclick="pagging(10, 'last')"/> 
        </div>
    </body>
</html>

