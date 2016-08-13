<%-- 
    Document   : FileimportOne
    Created on : 2014-10-2, 11:05:50
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
            </style>
        </head>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var data =${jsonarray};
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    var showData = data[i];
                    var stdName = showData.stdName;
                    var stdNum = showData.stdNum;
                    str = str + "<div class='wenzic'><input type='checkbox' name='tag'  value='" + stdNum + "' id='CheckboxGroup1_0' />"+
                    stdNum + " : " + stdName + "</div>";
                }
                str += "<div class='btn'><input name='input' type='submit' style='margin-left:20px;' value='确认' />" +
                        " <input name='input' type='reset' style='margin-left:20px;' value='重置' /></div>"
                $("#table").html(str);
            });
        </script>
        <body>
            <div>
                <h2><b>选择列表中标准选中标准将对以上标准打标签</b></h2>
                <form action="importOne.htm" name="form2" style="width:100%; float:left; margin-left:20px; " method="post" action="post">
                <table  border="1" cellspacing="0" cellpadding="1" align="center" id="table">

                </table>
            </form>
        </div>
    </body>
</html>
