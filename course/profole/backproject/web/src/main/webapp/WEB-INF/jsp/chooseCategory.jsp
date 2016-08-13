<%-- 
    Document   : chooseCategory.jsp
    Created on : 2014-10-27, 13:28:56
    Author     : liaojm
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <style type="text/css">body{
                font-family:'微软雅黑'}

            .wenzic{
                width:500px;
                height:50px;
                line-height:50px;
                background:#DCEDDD;}
            .btn{
                width:500px;
                height:50px;
                line-height:50px;
                background:#F0F0F0;}
            .weibiank{
                width:500px;
                margin:auto;}
            </style>
        </head>
        <script src="js/jquery.min.js" type="text/javascript"></script>


        <script>
            $(document).ready(function () {
                var data =${jsonarray};
                var str = "<div><tr><td><h4>操作的标准：</h4></td></tr></div>";
                var showData = data[0];
                var stdNum = showData.stdNum;
                var name = showData.name;
                str += "<div float:center><tr><td colspan='2'><input type='submit' value='确认' /> <input type='reset' value='重置' /></td></tr></div>" +
                        "<td><input type='text' name='std' value='" + stdNum + "' readonly/>" + name + "</td><br>";
                $("#open").html(str);
            });
        </script> 
        <script type="text/javascript">
            $(document).ready(function () {
                var data =${caretoryjson};
                var str = "<div float:center><tr><td><h4>请选择标准类别</h4></td></tr></div>";
                for (var i = 0; i < data.length; i++) {
                    var showData = data[i];
                    var category = showData.category;
                    for (var key in data[i]) {
                        if (key != "category") {
                            str += " <label><div class='wenzic'><input type='checkbox' name='category' value='" + showData[key] + "'  />" + category + "----->" + showData[key] + "</div></label>";
                        }
                    }
                }
                $("#caretory").html(str);
            });
        </script>

        <body>
            <div class="weibiank">

            <form id="form1" name="form1" action="executeCategory.htm" method="post">
                <p>
                <table  border="0" cellspacing="0" cellpadding="1" align="center" id="table">
                    <div id="open" >
                    </div>

                    <div id="caretory">
                    </div>

                </table>



                </p>
            </form>
        </div>
    </body>
</html>