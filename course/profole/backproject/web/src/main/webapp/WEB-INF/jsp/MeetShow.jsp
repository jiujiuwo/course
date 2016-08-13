<%-- 
    Document   : MeetShow
    Created on : 2015-2-5, 11:35:29
    Author     : liaojm
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>无标题文档</title>
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css">
        <link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" src="js/showdate.js"></script>

    </head>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var data =${jsonarray};
            var str = "<div class='mtbtm'>" +
                    "<table width='200' border='0'>" +
                    " <tr>" +
                    " <td style='border-color:#1d5d00;' >会议名称</td>" +
                    " <td style='border-color:#1d5d00;'>召开时间/会议结束时间</td>" +
                    " <td style='border-color:#1d5d00;'>会议地点</td>" +
                    " <td style='border-color:#1d5d00;'>会议状态</td>" +
                    " </tr>";
            for (var i = 0; i < data.length; i++) {
                var showData = data[i];
                var m_name = showData.m_name;
                var m_beginning = showData.m_beginning;
                var m_overtime = showData.m_overtime;
                var m_address = showData.m_address;
                var m_state = showData.m_state;
                str = str + "  <tr><td style='border-color:#1d5d00;'>" + m_name + "</td>" +
                        "<td style='border-color:#1d5d00;'>" + m_beginning + "/" + m_overtime + "</td>" +
                        "<td style='border-color:#1d5d00;'>" + m_address + "</td>" +
                        "<td style='border-color:#1d5d00;'>" + m_state + "</td></tr>";

            }
            str += "</table></div>";
            $("#open").html(str);
        });
    </script>

 

    <body bgcolor="#ececec">
        <div class="mttop">
            <div class="mttp"><img src="image/man.png"></div>
            会议通告<a href="meetapply.htm" target="showframe" class="btn btn-danger" style="float:right; margin-top:5px;">召开新会议</a></div>
        <form action="meetshow.htm" method="post" >
            <div class="mtmd">
                <div class="mtmiddle">
                    <div class="mtimgo"></div>
                    <div class="mtnr">
                        <div class="mtwz">时间：
                            <input type="text" id="m_beginning" name="m_beginning"  style="width:50%; height:30px;"onClick="return Calendar('m_beginning');" class="text_time" />
                        </div>

                    </div>
                </div>
                <div class="mtmiddle">
                    <div class="mtimgt"></div>
                    <div class="mtnr">
                        <div class="mtwz">会议状态：
                            <select type="text" id="m_state" name="m_state" style="width:70%; height:30px;">
                                <option>待召开</option>
                                <option>待总结</option>
                                <option>已总结</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="mtmiddle">
                    <div class="mtimgf"></div>
                    <div class="mtnr">
                        <div class="mtwz">会议名称：
                            <input  type="text" id="m_name" name="m_name" style="height:34px; width:50%; border-radius:0">

                        </div>

                    </div>
                </div>
            </div>
            <button type="submit" class="search-submit" > </button>
            <span  id="open">
            </span>
        </form>

    </body>
</html>
