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
<html>
    <head>
        <meta charset="utf-8">
        <title>会议管理展示，筛选</title>
        <link href="css/AddNotice.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/jquery-1.7.2.min.js" ></script>
    </head>
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var data =${jsonarray};
            var str = "<tr bgcolor='#CCCCCC'>" +
                    "<th width='10%'>会议名称</th>" +
                    " <th width='20%'>会议开始时间/会议结束时间</th>" +
                    "<th width='20%'>会议地点</th>" +
                    "<th width='20%'>会议状态</th>" +
                    "</tr>";
            for (var i = 0; i < data.length; i++) {
                var showData = data[i];
                var m_name = showData.m_name;
                var m_beginning = showData.m_beginning;
                var m_overtime = showData.m_overtime;
                var m_address = showData.m_address;
                var m_state = showData.m_state;
//  c_plantid, n_name,c_stdname,c_idrealytime,c_itovertime,c_state

                str = str + "<tr class='show'>" +
                        "<td width='10%'><span id='n_name'>" + m_name + "</span></td>" +
                        "<td width='10%'><span id='n_type'>" + m_beginning + "/"+m_overtime+"</span></td>" +
                        "<td width='10%'><span id='n_begintime'>" + m_address + "</span></td>" +
                        "<td width='10%'><span id='n_overtime'>" + m_state + "</span></td>" +
                        "</tr>";
            }
            $("#open").html(str);
        });
    </script>
    
    <script type="text/javascript">
        function add(s) {
            if (s == '2013') {
                var c_itovertime = document.getElementById("2013").value;
                document.getElementById("m_beginning").value = c_itovertime;
            }
            if (s == '2014') {
                var c_itovertime = document.getElementById("2014").value;
                document.getElementById("m_beginning").value = c_itovertime;
            }
            if (s == '2015') {
                var c_itovertime = document.getElementById("2015").value;
                document.getElementById("m_beginning").value = c_itovertime;
            }
        }
    </script>

    <body>

        <div class="demo">

            <form action="meetshow.htm" method="post" >
                <table>
                    <div>
                        <!--会议起止时间-->
                        <span>时间：</span>
                    </div>
                    <div>
                        <input type="text" id="m_beginning" name="m_beginning"/>
                    </div>

                    <div>
                        <a href="javascript:void(0)" onclick=add("2013")><input type="text" id="2013" name="2013" value="2013" readonly></a>
                        <a href="javascript:void(0)" onclick=add("2014")><input type="text" id="2014" name="2014" value="2014" readonly></a>
                        <input type="text" id="2015" name="2015" onblur="add('2015')"/>
                    </div>

                    <div>
                        <span>会议名称：</span>
                    </div>
                    <div><input type="text" id="m_name" name="m_name"/></div>
                    <!--<input type="button" name="butten" onclick="check()" value="确定"/>-->

                    <div>
                        <span>会议状态：</span>
                    </div>
                    <div>
                        <select type="text" id="m_state" name="m_state">
                            <option>待召开</option>
                            <option>待总结</option>
                            <option>经总结</option>
                        </select>
                        <!--<input type="button" name="butten" onclick="check()" value="确定"/>-->
                    </div>



                    <div class="altbutton">
                        <input type="submit" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px; float:left;" value="修改" />
                        <input type="reset" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px; float:right;" value="重置" />
                    </div>

                    <span  id="open">
                    </span>
                </table>
            </form>

        </div>

    </body>
</html>

