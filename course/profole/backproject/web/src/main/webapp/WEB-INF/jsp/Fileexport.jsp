<%-- 
    Document   : Fileexport
    Created on : 2014-8-27, 15:40:08
    Author     : liaojiamin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>无标题文档</title>
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css">
        <link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
    </head>

    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var data =${jsonarray};
            var str = "";
            for (var i = 0; i < data.length; i++) {
                var showData = data[i];
                var stdNum = showData.stdNum;
                var stdName = showData.stdName;
                str = str + "<tr><td><input type='checkbox' name='stdNum' value='" + stdNum + "'/>" + stdNum + ":" + stdName + "<br></td></tr>";
                //                        "<td><input type='checkbox' name='stdNum' value='" + stdNum + "'/>" + stdNum+":"+stdName+"<br></td>";
            }

            str += "<tr><td style='border:none'><a href='' target='showframe' style='float:right;' class='btn btn-danger' >导出</a></td></tr>";
            $("#table").html(str);
        });
    </script>

    <body bgcolor="#ECECEC">
        <div class="mttop">
            <div class="capic"></div>
            <div class="calianjie"><a href="ChoosePath.htm"  target="showframe">导入</a></div><div style="float:left">|</div><div style="float:left;color:red">导出</div><form name="form1" method="post" action="" style="margin:auto; width:20%;">


                <label style="float:left; margin-right: 20%;"><a href="fileexport.htm" target="showframe">
                        <input type="radio" name="RadioGroup1" value="单选" checked="true" id="RadioGroup1_0">
                        按标准导出</a></label>
                <label style="margin:5px 1%;"><a href="exportforcategory.htm" target="showframe">
                        <input type="radio" name="RadioGroup1" value="单选" id="RadioGroup1_1">
                        按类别导出</a></label>

        </div>
        <div class="mtotm">
            <div class="mtoto">
                <div class="mtotfj">项目名称：</div>
                <input name="" type="text" style="height:25px; width:50%">
            </div>
            <div class="mtoto">
                <div class="mtotfj">标准名称：</div>
                <input name="" style="width:20%" type="text" placeholder="标准名称">
                <input name="" type="button" style="background:red;border:none;color:#fff;width:50px;border-radius:0 5px 5px 0;"value="筛选">
            </div>

            <form action="fileexport.htm" method="post" style="margin:auto; width:25%;">
                <table width="200" border="0" id="table">
                    <tr>
                        <!--<td style="border:none"><a href="" target="showframe" style="float:right;" class="btn btn-danger" >导出</a></td>-->

                    </tr>

                </table>
            </form>
        </div>
    </body>
</html>

<!--  <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>fileexport.jsp</title>
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
        $(document).ready(function() {
         var data =${jsonarray};
         var str="";
            for (var i = 0; i <data.length; i++) {
                var showData = data[i];
                var stdNum = showData.stdNum;
                var stdName=showData.stdName;
                str = str + "<tr><td><input type='checkbox' name='stdNum' value='" + stdNum + "'/>" + stdNum+":"+stdName+"<br></td></tr>";
//                        "<td><input type='checkbox' name='stdNum' value='" + stdNum + "'/>" + stdNum+":"+stdName+"<br></td>";
            }
          
           str+="<tr><td style='border:none'><a href='' target='showframe' style='float:right;' class='btn btn-danger' >导出</a></td></tr>";
            $("#table").html(str);
        });
    </script>


    <body>
        <div id="head" >
            <div align="right"><font size="2px">  游客 <a href="login.htm">登录</a></font></div>
            <div><b><font size="5px">科迪智标 资源库管理系统|资源导出</font></b></div>
        </div>
        
        <div id="line" ><hr size="3" color="#080808"></div>
             <form action="fileexport.htm" method="post"/>
                    <table  border="1" cellspacing="0" cellpadding="1" align="center" id="table">
                        
                        
                        
                          
                        </td></tr>
                    </table>
            </form>
         <div id="foot" align="center" style="bottom: 10px;float: center">
                <div><font size="2px">2014 北京新华社 京ICP证000000号</font></div>
            </div>
    </body>
</html>-->