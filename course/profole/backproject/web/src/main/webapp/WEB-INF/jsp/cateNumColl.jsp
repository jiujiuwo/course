<%-- 
    Document   : cateNumColl.jsp
    Created on : 2014-10-28, 16:28:56
    Author     : liaojm
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>cateNumColl.jsp</title>
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

    <script src="js/jquery.min.js" type="text/javascript"></script>


    <script type="text/javascript">
        $(document).ready(function () {
            var data =${jsy};
            var str = "<div float:center><tr><td><h4>请选择标准类别</h4></td></tr></div>";
            for (var i = 0; i < data.length; i++) {
                var showData = data[i];
                var category = showData.category;
                str += "<td><h4>" + category + "中的子类含有标准个数：</h4><br></td>";
                for (var key in data[i]) {
                    if (key != "category") {
                        str += "<td><a href='cateStdColl.htm?category=" + key + "'>查看" + key + "类别中含有的标准统计数据</a><br></td>" +
                                "<td>" + key + "中含有标准" + showData[key] + "个<br></td>";
                    }
                }
            }
            $("#caretory").html(str);
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            var data =${jay};
            var showData = data[0];
            var term = showData.term;
            var data = showData.data;
            var str = "<tr><td>仓库中含有术语：<td></tr>" +
                    "<tr><td>" + term + "<td></tr>" +
                    "<tr><td>仓库中含有数据：<td></tr>" +
                    "<tr><td>" + data + "<td></tr>";
            $("#open").html(str);
        });
    </script>


    <body>
        <div id="head" >
            <div align="right"><font size="2px">  游客 <a href="login.htm">登录</a></font></div>
            <div><b><font size="5px">科迪智标 资源库管理系统|标准类别统计数据</font></b></div>
        </div>
        <div id="line" ><hr size="3" color="#080808">
            <form action="#" method="post"/>
            <table  border="0" cellspacing="0" cellpadding="1" align="center" id="table">
                <div id="caretory">
                </div>

                <div id="open">
                </div>
            </table>

        </form>
    </div>
    <div>
        <ul class="treeview side-con" id="tree" style="float: left;">
        </ul>
        <div style="float: left; width: 70%; height: 100%;">
            <iframe id="iframe" style="width: 100%; height: 500px;border: 1px solid #999;padding: 5px 20px;margin-left: -50px;" >

            </iframe>
        </div>
    </div>
</body>
</html>
