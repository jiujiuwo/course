<%-- 
    Document   : Experadvice
    Created on : 2015-2-2, 15:05:26
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var data = ${
        jsonarray
        };

            var showData = data[0];
            var c_plantid = showData.c_plantid;
            var plantid = showData.c_plantid;
            var n_name = showData.n_name;
            var aliasname = showData.aliasname;
            var c_stdname = showData.c_stdname;
            var c_idrealytime = showData.c_idrealytime;
            var c_itovertime = showData.c_itovertime;

            $("#c_plantid").val(c_plantid);
            $("#plantid").val(c_plantid);
            $("#n_name").val(n_name);
            $("#aliasname").val(aliasname);
            $("#c_stdname").val(c_stdname);
            $("#c_idrealytime").val(c_idrealytime);
            $("#c_itovertime").val(c_itovertime);
        });
    </script>
    <body>
        <div id="firsttj" class="collapse" style="hight: 0px">
            <table>
                <tr>计划号:
                <input type="text" name="c_plantid" id="c_plantid" readonly/></tr>
                <tr>项目名称
                <input type="text" name="n_name" id="n_name" readonly/></tr>
                <tr>标准名称
                <input type="text" name="c_stdname" id="c_stdname" readonly/></tr>
                <tr>开始时间
                <input type="text" name="c_idrealytime" id="c_idrealytime" readonly/></tr>
                <tr>结束时间
                <input type="text" name="c_itovertime" id="c_itovertime" readonly/></tr>
            </table>
            <form  action="experadvice.htm" name="test_form" id="getpath"   method="post" ENCTYPE="multipart/form-data">
                            <!--<div id="fileDiv" style="margin-left:55px;"></div>-->
                            <input name="file" type="file" />
                            <input type="text" name="c_plantid" id="plantid" readonly/>
                            <input type="submit"  style=" width:50px; height:30px; margin-bottom:20px;" class="btn btn-primary" value="提交" />
                        </form>
        </div>
    </body>
</html>
