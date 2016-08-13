<%-- 
    Document   : AddNotice
    Created on : 2015-1-11, 11:04:29
    Author     : liaojm
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>无标题文档</title>
        <link href="css/AddNotice.css" rel="stylesheet" type="text/css">
        <script type="text/javascript">
            function HS_DateAdd(interval, number, date) {
                number = parseInt(number);
                if (typeof (date) == "string") {
                    var date = new Date(date.split("-")[0], date.split("-")[1], date.split("-")[2])
                }
                if (typeof (date) == "object") {
                    var datedate = date
                }
                switch (interval) {
                    case "y":
                        return new Date(date.getFullYear() + number, date.getMonth(), date.getDate());
                        break;
                    case "m":
                        return new Date(date.getFullYear(), date.getMonth() + number, checkDate(date.getFullYear(), date.getMonth() + number, date.getDate()));
                        break;
                    case "d":
                        return new Date(date.getFullYear(), date.getMonth(), date.getDate() + number);
                        break;
                    case "w":
                        return new Date(date.getFullYear(), date.getMonth(), 7 * number + date.getDate());
                        break;
                }
            }
            function checkDate(year, month, date) {
                var enddate = ["31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"];
                var returnDate = "";
                if (year % 4 == 0) {
                    enddate[1] = "29"
                }
                if (date > enddate[month]) {
                    returnDate = enddate[month]
                } else {
                    returnDate = date
                }
                return returnDate;
            }
            function WeekDay(date) {
                var theDate;
                if (typeof (date) == "string") {
                    theDate = new Date(date.split("-")[0], date.split("-")[1], date.split("-")[2]);
                }
                if (typeof (date) == "object") {
                    theDate = date
                }
                return theDate.getDay();
            }
            function HS_calender() {
                var lis = "";
                var style = "";
                /* http://www.webdm.cn*/
                style += "<style type='text/css'>";
                style += ".calender { width:170px; height:auto; font-size:12px; margin-right:14px; background:url(calenderbg.gif) no-repeat right center #fff; border:1px solid #397EAE; padding:1px}";
                style += ".calender ul {list-style-type:none; margin:0; padding:0;}";
                style += ".calender .day { background-color:#EDF5FF; height:20px;}";
                style += ".calender .day li,.calender .date li{ float:left; width:14%; height:20px; line-height:20px; text-align:center}";
                style += ".calender li a { text-decoration:none; font-family:Tahoma; font-size:11px; color:#333}";
                style += ".calender li a:hover { color:#f30; text-decoration:underline}";
                style += ".calender li a.hasArticle {font-weight:bold; color:#f60 !important}";
                style += ".lastMonthDate, .nextMonthDate {color:#bbb;font-size:11px}";
                style += ".selectThisYear a, .selectThisMonth a{text-decoration:none; margin:0 2px; color:#000; font-weight:bold}";
                style += ".calender .LastMonth, .calender .NextMonth{ text-decoration:none; color:#000; font-size:18px; font-weight:bold; line-height:16px;}";
                style += ".calender .LastMonth { float:left;}";
                style += ".calender .NextMonth { float:right;}";
                style += ".calenderBody {clear:both}";
                style += ".calenderTitle {text-align:center;height:20px; line-height:20px; clear:both}";
                style += ".today { background-color:#ffffaa;border:1px solid #f60; padding:2px}";
                style += ".today a { color:#f30; }";
                style += ".calenderBottom {clear:both; border-top:1px solid #ddd; padding: 3px 0; text-align:left}";
                style += ".calenderBottom a {text-decoration:none; margin:2px !important; font-weight:bold; color:#000}";
                style += ".calenderBottom a.closeCalender{float:right}";
                style += ".closeCalenderBox {float:right; border:1px solid #000; background:#fff; font-size:9px; width:11px; height:11px; line-height:11px; text-align:center;overflow:hidden; font-weight:normal !important}";
                style += "</style>";
                var now;
                if (typeof (arguments[0]) == "string") {
                    selectDate = arguments[0].split("-");
                    var year = selectDate[0];
                    var month = parseInt(selectDate[1]) - 1 + "";
                    var date = selectDate[2];
                    now = new Date(year, month, date);
                } else if (typeof (arguments[0]) == "object") {
                    now = arguments[0];
                }
                var lastMonthEndDate = HS_DateAdd("d", "-1", now.getFullYear() + "-" + now.getMonth() + "-01").getDate();
                var lastMonthDate = WeekDay(now.getFullYear() + "-" + now.getMonth() + "-01");
                var thisMonthLastDate = HS_DateAdd("d", "-1", now.getFullYear() + "-" + (parseInt(now.getMonth()) + 1).toString() + "-01");
                var thisMonthEndDate = thisMonthLastDate.getDate();
                var thisMonthEndDay = thisMonthLastDate.getDay();
                var todayObj = new Date();
                today = todayObj.getFullYear() + "-" + todayObj.getMonth() + "-" + todayObj.getDate();
                for (i = 0; i < lastMonthDate; i++) {  // Last Month's Date  
                    lis = "<li class='lastMonthDate'>" + lastMonthEndDate + "</li>" + lis;
                    lastMonthEndDate--;
                }
                for (i = 1; i <= thisMonthEndDate; i++) { // Current Month's Date  
                    if (today == now.getFullYear() + "-" + now.getMonth() + "-" + i) {
                        var todayString = now.getFullYear() + "-" + (parseInt(now.getMonth()) + 1).toString() + "-" + i;
                        lis += "<li><a href=javascript:void(0) class='today' onclick='_selectThisDay(this)' title='" + now.getFullYear() + "-" + (parseInt(now.getMonth()) + 1) + "-" + i + "'>" + i + "</a></li>";
                    } else {
                        lis += "<li><a href=javascript:void(0) onclick='_selectThisDay(this)' title='" + now.getFullYear() + "-" + (parseInt(now.getMonth()) + 1) + "-" + i + "'>" + i + "</a></li>";
                    }
                }
                var j = 1;
                for (i = thisMonthEndDay; i < 6; i++) {  // Next Month's Date  
                    lis += "<li class='nextMonthDate'>" + j + "</li>";
                    j++;
                }
                lis += style;
                var CalenderTitle = "<a href='javascript:void(0)' class='NextMonth' onclick=HS_calender(HS_DateAdd('m',1,'" + now.getFullYear() + "-" + now.getMonth() + "-" + now.getDate() + "'),this) title='Next Month'>?</a>";
                CalenderTitle += "<a href='javascript:void(0)' class='LastMonth' onclick=HS_calender(HS_DateAdd('m',-1,'" + now.getFullYear() + "-" + now.getMonth() + "-" + now.getDate() + "'),this) title='Previous Month'>?</a>";
                CalenderTitle += "<span class='selectThisYear'><a href='javascript:void(0)' onclick='CalenderselectYear(this)' title='Click here to select other year' >" + now.getFullYear() + "</a></span>年<span class='selectThisMonth'><a href='javascript:void(0)' onclick='CalenderselectMonth(this)' title='Click here to select other month'>" + (parseInt(now.getMonth()) + 1).toString() + "</a></span>月";
                if (arguments.length > 1) {
                    arguments[1].parentNode.parentNode.getElementsByTagName("ul")[1].innerHTML = lis;
                    arguments[1].parentNode.innerHTML = CalenderTitle;
                } else {
                    var CalenderBox = style + "<div class='calender'><div class='calenderTitle'>" + CalenderTitle + "</div><div class='calenderBody'><ul class='day'><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li></ul><ul class='date' id='thisMonthDate'>" + lis + "</ul></div><div class='calenderBottom'><a href='javascript:void(0)' class='closeCalender' onclick='closeCalender(this)'>×</a><span><span><a href=javascript:void(0) onclick='_selectThisDay(this)' title='" + todayString + "'>Today</a></span></span></div></div>";
                    return CalenderBox;
                }
            }
            function _selectThisDay(d) {
                var boxObj = d.parentNode.parentNode.parentNode.parentNode.parentNode;
                boxObj.targetObj.value = d.title;
                boxObj.parentNode.removeChild(boxObj);
            }
            function closeCalender(d) {
                var boxObj = d.parentNode.parentNode.parentNode;
                boxObj.parentNode.removeChild(boxObj);
            }
            function CalenderselectYear(obj) {
                var opt = "";
                var thisYear = obj.innerHTML;
                for (i = 1970; i <= 2020; i++) {
                    if (i == thisYear) {
                        opt += "<option value=" + i + " selected>" + i + "</option>";
                    } else {
                        opt += "<option value=" + i + ">" + i + "</option>";
                    }
                }
                opt = "<select onblur='selectThisYear(this)' onchange='selectThisYear(this)' style='font-size:11px'>" + opt + "</select>";
                obj.parentNode.innerHTML = opt;
            }
            function selectThisYear(obj) {
                HS_calender(obj.value + "-" + obj.parentNode.parentNode.getElementsByTagName("span")[1].getElementsByTagName("a")[0].innerHTML + "-1", obj.parentNode);
            }
            function CalenderselectMonth(obj) {
                var opt = "";
                var thisMonth = obj.innerHTML;
                for (i = 1; i <= 12; i++) {
                    if (i == thisMonth) {
                        opt += "<option value=" + i + " selected>" + i + "</option>";
                    } else {
                        opt += "<option value=" + i + ">" + i + "</option>";
                    }
                }
                opt = "<select onblur='selectThisMonth(this)' onchange='selectThisMonth(this)' style='font-size:11px'>" + opt + "</select>";
                obj.parentNode.innerHTML = opt;
            }
            function selectThisMonth(obj) {
                HS_calender(obj.parentNode.parentNode.getElementsByTagName("span")[0].getElementsByTagName("a")[0].innerHTML + "-" + obj.value + "-1", obj.parentNode);
            }
            function HS_setDate(inputObj) {
                var calenderObj = document.createElement("span");
                calenderObj.innerHTML = HS_calender(new Date());
                calenderObj.style.position = "absolute";
                calenderObj.targetObj = inputObj;
                inputObj.parentNode.insertBefore(calenderObj, inputObj.nextSibling);
            }
        </script>  
        <style>  
            body {font-size:12px}  
            td {text-align:center}  
            h1 {font-size:26px;}  
            h4 {font-size:16px;}  
            em {color:#999; margin:0 10px; font-size:11px; display:block}  
        </style>  
    </head>

    <body>
        <form action="addnotice.htm" method="post" commandName="org">
            <div class="cengt"><span style="color:red">　*</span>公告名称：<input name="n_name" id="name" type="text"></div>
            <div class="cengt"><span style="color:red">　*</span>公告类型<select name="n_type" id="n_type"><option>国标</option>
                    <option>行标</option><option>企标</option></select>
            </div>
            <div class="cengt">接受申请时间：<input type="text" name="n_begintime"style="width:70px" onfocus="HS_setDate(this)">　到　<input type="text"  name="n_overtime" style="width:70px" onfocus="HS_setDate(this)"></div>
            <div class="cengt">发布时间：<input type="text" name="n_pubdate" style="width:70px" onfocus="HS_setDate(this)"></div>
            <div class="cengt">内容：<textarea name="n_info" cols="" style="width:500px; height:200px;" rows=""></textarea></div>
            <div class="cengt">附件：<input name="n_path" type="file"></div>
             <div class="cengt"><span style="color:red">　*</span>公告状态<select name="n_state" id="n_state"><option>正常</option>
                    <option>停止</option><option>撤回</option></select>
            </div>
            <div class="altbutton">
                <input type="submit" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px;" value="添加" />
                <input type="reset" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px;" value="重置" />
            </div>
        </form>
    </body>
</html>
