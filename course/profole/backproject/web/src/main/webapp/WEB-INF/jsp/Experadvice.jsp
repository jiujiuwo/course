<%-- 
    Document   : Experadvice
    Created on : 2015-2-2, 15:05:26
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
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
                            <div id="fileDiv" style="margin-left:55px;"></div>
                            <input name="file" type="file" />
                            <input type="text" name="c_plantid" id="plantid" readonly/>
                            <input type="submit"  style=" width:50px; height:30px; margin-bottom:20px;" class="btn btn-primary" value="提交" />
                        </form>
        </div>
    </body>
</html>-->
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
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
    <script language="javascript">
        function addFile() {
            var fileDiv = document.all['fileDiv'];
        }

        function removeFile(obj) {
            obj.removeNode(true);
        }
    </script>
    <body bgcolor="#ECECEC">
        <div class="mttop">
            <div class="askpic"></div>
            新征求意见</div>

        <div class="mtotm">
            <div class="audfddc">
                <div class="audfdot">计划号：</div>
                <div class="audfdotk">
                    <input name="c_plantid" id="c_plantid" readonly  type="text" style="height:34px; width:50%;">
                    <!--                <button type="submit" class="search-submit" > </button>(在征求意见待审核的标准中选择)-->
                </div>
                <div class="audfdot">项目名称：</div>
                <div class="audfdotk">
                    <input name="n_name" id="n_name" readonly type="text" style="height:40px; width:50%;">
                </div>
                <div class="audfdot">标准名称：</div>
                <div class="audfdotk">
                    <input name="c_stdname" id="c_stdname" readonly type="text" style="height:40px; width:50%;">
                </div>
                <div class="audfdot">开始时间：</div>
                <div class="audfdotk">
                    <input type="text" name="c_idrealytime" id="c_idrealytime" readonly style="height:40px; width:50%;"/>
                </div>
                <div class="audfdot">结束时间：</div>
                <div class="audfdotk">
                    <input type="text" name="c_itovertime" id="c_itovertime" readonly style="height:40px; width:50%;"/></tr>
                </div>
                <div class="mtoto">
                    <div class="audfdot"> 

                        <!--      <form enctype="multipart/form-data">
                                <a href="javascript:void(addFile());" class="file filebtno">
                                <input class="fbtn" type="file"  />
                                <img src="image/fujiano.png"  width="110" height="20" /></a>
                              </form>-->
                        <form  action="experadvice.htm" name="test_form" id="getpath"   method="post" ENCTYPE="multipart/form-data">
                            <a href="javascript:void(addFile());" class="file filebtno">
                                <input name="file" type="file" class="fbtn"/>
                                <img src="image/fujiano.png"  width="110" height="20" /></a>
                            <input type="text" name="c_plantid" id="plantid" readonly style="display: none;"/>
                            <input type="submit"  class="btn btn-danger" value="提交" />
                        </form>
                    </div>
                    <!--    <div id="fileDiv"></div><input name="" type="text" style="height:40px; width:40%;">-->

                </div></div>
            <!--            <div class="mtotbtn">
                  <button type="button" class="btn btn-danger">保存</button>
                </div>-->


        </div>
    </body>
</html>

