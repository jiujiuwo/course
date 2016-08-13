<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
        <style type="text/css">
            body{margin: 0px; }
            #head {background-color: #EAEAEA;height:80px;margin:auto 15px ;}
            #content {height:390px;margin-left:36px;}
            #line{margin-top:-10px;margin-left:15px;margin-right:15px}
            #condition{height:90px}
            .results-con{padding:20px 10px;height:350px;border:1px solid #999;border-radius:5px;margin-right:36px}
            .results-con span{width:98%;display:block;background-color: #EAEAEA;}
            #foot{ height: 20px; margin-top: 300px;}
            table{height:50px;border-color:#C0C0C0; width: 98%; }
            td{text-align:center;}
            .show span {background:none}

        </style>


        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
    $(document).ready(function() {
        var data =${jsonarray};

        var showData = data[0];
        $("#stdNum").val(showData.stdNum);
        $("#stdChaName").val(showData.stdChaName);
        $("#stdEngName").val(showData.stdEngName);
        $("#releaseData").val(showData.releaseData);
        $("#ImpleData").val(showData.impleData);
        $("#executeData").val(showData.executeData);
        $("#countryLable").val(showData.countryLable);
        $("#byCountryLable").val(showData.byCountryLable);
        $("#replayData").val(showData.replayData);
        $("#itaStdNum").val(showData.itastdNum);
        $("#byItaStdNum").val(showData.byItaStdName);
        $("#byAdoption").val(showData.byAdoption);
        $("#Ics").val(showData.Ics);
        $("#stdPag").val(showData.stdPag);
        $("#keyDepart").val(showData.keyDepart);
        $("#center").val(showData.center);
        $("#stdAttribute").val(showData.stdAttribute);
        $("#draftCenter").val(showData.draftCenter);
    });
        </script>
    </head>
    <body>
        <div id="head" >
            <div align="right"><font size="2px">  游客 <a href="login.htm">登录</a></font></div>
            <div><b><font size="5px">科迪智标 标准资源库|高级搜索</font></b></div>
        </div>
        <div id="line" ><hr size="3" color="#080808"></div>

        <div id="content">
            <table border="1" cellspacing="0" cellpadding="1" align="center">
                <tr bgcolor="#CCCCCC">
                    <td><b>标准号</b></td>
                    <td colspan="5"><input type="text" name="stdNum" id="stdNum"></td>
                </tr>
                <tr>
                    <td >中文标准名称</td>
                    <td colspan="5"><input type="text" name="stdChaName" id="stdChaName"></td>
                </tr>
                <tr >
                    <td>英文标准名称</td>
                    <td colspan="5"><input type="text" name="stdEngName" id="stdEngName"></td>
                </tr>
                <tr bgcolor=" #FFFFFF ">
                    <td><b>发布日期</b></td>
                    <td><input type="text" name="releaseData" id="releaseData"></td>
                    <td><b>实施日期</b></td>
                    <td><input type="text" name="ImpleData" id="ImpleData"></td>
                    <td><b>首次发布日期</b></td>
                    <td><input type="text" name="executeData" id="executeData"></td>
                </tr>
                <tr >
                    <td>替代国标号</td>
                    <td><input type="text" name="countryLable" id="countryLable"></td>
                    <td>被替代国标号</td>
                    <td><input type="text" name="byCountryLable" id="byCountryLable"></td>
                    <td>废止时间</td>
                    <td><input type="text" name="replayData" id="replayData"></td>
                </tr>
                <tr bgcolor=" #FFFFFF ">
                    <td><b>采用国际标准号</b></td>
                    <td colspan="5"><input type="text" name="itaStdNum" id="itaStdNum"></td>
                </tr>
                <tr bgcolor="#F0F8FD">
                    <td>采用国际标准名称</td>
                    <td colspan="5"><input type="text" name="byItaStdNum" id="byItaStdNum"></td>
                </tr>
                <tr >
                    <td>采用程度</td>
                    <td colspan="5"><input type="text" name="byAdoption" id="byAdoption"></td>
                </tr>
                </tr>
                <tr bgcolor=" #FFFFFF ">
                    <td><b>国际标准分类号</b></td>
                    <td><input type="text" name="Ics" id="Ics"></td>
                    <td><b>中国标准分类号</b></td>
                    <td><input type="text" name="chaIcs" id="chaIcs"></td>
                    <td><b>标准页码</b></td>
                    <td><input type="text" name="stdPag" id="stdPag"></td>
                </tr>
                <tr >
                    <td >主管部门</td>
                    <td><input type="text" name="keyDepart" id="keyDepart"></td>
                    <td>归属单位</td>
                    <td><input type="text" name="center" id="center"></td>
                    <td>标准属性</td>
                    <td><input type="text" name="stdAttribute" id="stdAttribute"></td>
                </tr>
                <tr bgcolor=" #FFFFFF ">
                    <td><b>起草单位</b></td>
                    <td colspan="5"><input type="text" name="draftCenter" id="draftCenter"></td>
                </tr>
            </table>



    </body>
</html>