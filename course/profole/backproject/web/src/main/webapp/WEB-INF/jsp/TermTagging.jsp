<%-- 
    Document   : TermTagging
    Created on : 2014-9-2, 11:05:50
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
        <style type="text/css">
            body{
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
                var str = " <div class='btn'><input name='input' type='button' style='margin-left:20px;' value='确认' /><input name='input' type='button' style='margin-left:20px;' value='重置' /></div>" +
                        "<div float:center><tr><td><h4>确定列表中标准标签</h4></td></tr></div>";
                for (var i = 0; i < data.length; i++) {
                    var showData = data[i];
                    var stdNum = showData.stdNum;
                    var name = showData.name;
                    str = str + "<div class='wenzic'><input type='checkbox' name='tag1' value='" + stdNum + "' id='CheckboxGroup1_0' />" + stdNum + ":" + name + "</div>" +
                            "<div class='btn'><a href='showItem.htm?stdNum=" + stdNum + "'><input name='t' type='button' style='margin-left:25px;' value='查看' /></a> " +
                            " <a href='changeTag.htm?stdNum=" + stdNum + "'><input name='t' type='button' style='margin-left:20px;' value='确认' /></a> " +
                            " <a href='tag.htm?stdNum=" + stdNum + "'><input name='t' type='button' style='margin-left:20px;' value='取消'/></a></div>";
                }
                $("#table").html(str);
            });
        </script>

        <body>
            <div>
                <h2><b>标签确认</b></h2>
                <form id="form2" name="form2" style="width:100%; float:left; margin-left:20px; " method="post" action="termtagging.htm">
                <table  border="0" cellspacing="0" cellpadding="1" align="center" id="table">

            </form>
        </div>
    </body>
</html>
