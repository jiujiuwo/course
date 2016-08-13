<!doctype html>
<%@ page contentType="text/html" language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/lhgcore.js"></script>
<script type="text/javascript" src="js/lhgcalendar.js"></script>
<script src="js/jquery1.3.2.js" type="text/javascript"></script>
<script type="text/javascript">
     $(document).ready(function () {

                var data = '${announcementCount}';

                var jsonarray=${jsonarray};
                var count=jsonarray.length;
                var pageSize=5;
                var pageCount;
                $("#noticeCount").html(data);
            });
$(function() {
	setTimeout("changeDivStyle();", 100); // 0.1秒后展示结果，因为HTML加载顺序，先加载脚本+样式，再加载body内容。所以加个延时
});
function changeDivStyle(){
//		var o_status = $("#o_status").val();	//获取隐藏框值
	var o_status = 4;
	if(o_status==1){
		$('#produce').css('background', '#5fdefe');
		$('#produceText').css('color', '#5fdefe');
	}else if(o_status==2||o_status==3){
		$('#check').css('background', '#5fdefe');
		$('#checkText').css('color', '#5fdefe');
	}else if(o_status==4){
		$('#create').css('background', '#5fdefe');
		$('#createText').css('color', '#5fdefe');
	}else if(o_status==5){
		$('#delivery').css('background', '#5fdefe');
		$('#deliveryText').css('color', '#5fdefe');
	}else if(o_status>=6){
		$('#received').css('background', '#5fdefe');
		$('#receivedText').css('color', '#5fdefe');
	}
}
</script>
<script type="text/javascript">
$(function(){
$('tr.parent').click(function(){   // 获取所谓的父行
$(this)
	.toggleClass("selected")   // 添加/删除高亮
	.siblings('.child_'+this.id).toggle();  // 隐藏/显示所谓的子行
	});
})</script>
</head>

<body style="OVERFLOW:SCROLL;OVERFLOW-Y:HIDDEN;OVERFLOW-X:HIDDEN">
<!--<div class="top">
  <div class="left">
    <div class="one">欢迎登录	！</div>
    <div class="one" style="text-align:center">
      <div class="dropdown">
        <button type="button"  style=" background:none; border:none;color:#fff;font-size:20px;"
      data-toggle="dropdown"> admin_2015> </button>
        <div class="dropdown-menu tcyhx">
          <div class="yhtx"><img src="image/touxiang.jpg"></div>
          <div class="yhxx">
            <div>admin_2015</div>
            <div class="yhxg"><a href=""> 个人管理</a>|<a href="javascript:"  onClick="showchangepassword()">修改密码</a>
              <div class="sgwk"> 
<script type="text/javascript">
function showchangepassword(){
	var c=$("#change").clone(true);
	$("body").append("<div class='modal-backdrop fade in' id='changepwdwrap'></div>");//创建的蒙版 就是全屏背景黑色半透明屏蔽后面元素的层 就是在body里面创建（append）一个div
	$("#changepwdwrap").append(c);//这边是给div里面写入内容，就是白色的aaaaaa的内容
}
function closes(){
	$("#changepwdwrap").remove();
}
</script>
                <div id="change" class="sgnk">
                  <p id="close"> <font id="close" onclick="closes()">x</font> </p>
                  <div class="sgmm">修改密码</div>
                  <div class="sgnrys">老密码：</div>
                  <input name="" type="text" style=" width:69%;height:30px; float:left; border-radius:0px; border:1px #55b6c4 solid;">
                  <div class="sgnrys">新密码：</div>
                  <input name="" type="text" style="width:69%;height:30px;border-radius:0px; border:1px #55b6c4 solid;">
                  <div class="sgnrys">确认密码：</div>
                  <input name="" type="text" style="width:69%;height:30px;border-radius:0px; border:1px #55b6c4 solid;">
                  <div class="sgbtn">
                    <button type="button" class="btn btn-default" style="width:90%; height:30px; background:#f36948; color:#fff; font-weight:700">提交</button>
                  </div>
                </div>
              </div></div>
          </div>
          <div style="border-bottom:1px #EAEAEA solid; width:90%; margin-left:5%; float:left;"></div>
        </div>
      </div>
    </div>
    <div class="two" style="text-align:right; font-size:20px;"><a href="">退出登录</a></div>
  </div>aa
</div>-->
<div class="tabpage">
<!--  <ul id="myTab" class="nav nav-tabs">
    <li class="active"><a href="#homepage" data-toggle="tab" id="nav0"> 首　　页</a> </li>
    <li><a href="#proapplication" data-toggle="tab" id="nav1"><span class="badge pull-right" id="noticeCount"></span>标准申请</a></li>
    <li><a href="#edit" data-toggle="tab" id="nav2"><span class="badge pull-right">2</span>标准编辑</a></li>
    <li><a href="#query" data-toggle="tab" id="nav3"><span class="badge pull-right">2</span>标准查询</a></li>
    <li><a href="#examination" data-toggle="tab" id="nav4">考试平台</a></li>
  </ul>-->
  <div id="myTabContent" class="tab-content">
    <div class="tab-pane fade " id="homepage">
      
    </div>
    <div class="tab-pane fade" id="proapplication">
      <p>
      <div class="position">
        <p id="lanrenzhijia1" style="float:left;"></p>
        <p id="lanrenzhijia2"></p>
        <script>
Date.prototype.Format = function (fmt) { //javascript时间日期函数
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日        
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
	var time1 = new Date().Format("yyyy年MM月dd日");   //获取日期，格式： 年-月-日
	
	document.getElementById('lanrenzhijia1').innerHTML = time1;  //将时间赋值给t1
</script>　　欢迎您回来　您当前的位置：标准申请 </div>
      <div class="news">
        <div class="shell">
          <div id="div1">
              <c:forEach items="${noticeJsonarray}" var="str">
                             <a href="">${str.n_name}可申请，截止时间是 ${str.n_overtime}</a>

               </c:forEach>          
          </div>
        </div>
        <script language="javascript">
var box=document.getElementById("div1"),can=true;
box.innerHTML+=box.innerHTML;
box.onmouseover=function(){can=false};
box.onmouseout=function(){can=true};
new function (){
	var stop=box.scrollTop%18==0&&!can;
	if(!stop)box.scrollTop==parseInt(box.scrollHeight/2)?box.scrollTop=0:box.scrollTop++;
	setTimeout(arguments.callee,box.scrollTop%18?10:1500);
};
</script> 
      </div>
      <a  href="standard.htm" class="btn btn-danger">申请新标准</a>
       <script>

                        var begintimelocal = null;
                        var overtimelocal = null;
                        var infolocal = null;
                        var statelocal = null;
                        var typelocal = null;


                        function send(obj) {

                            var currentNode = $(obj);
                            var text = currentNode.html();

                            var id = currentNode.parent().parent().attr("id");
                            var start = $("#c10").attr("value");
                            var end = $("#c11").attr("value");


                            if (id == 'select1') {

                                if (text == '至') {
                                    begintimelocal = start;
                                    overtimelocal = end;
                                }
                                else {
                                    begintimelocal = text;
                                    overtimelocal = null;
                                }
                            }
                            if (id == 'select2') {
                                if (text == '全部') {
                                    typelocal = null;
                                }
                                else {
                                    typelocal = text;
                                }
                            }
                            if (id == 'select3') {
                                if (text == '全部') {
                                    statelocal = null;
                                }
                                else {
                                    statelocal = text;
                                }
                            }
                            // alert(begintimelocal+","+overtimelocal+","+typelocal+","+statelocal);
                            test(begintimelocal, overtimelocal, typelocal, statelocal);
                        }

                        function sendInfo() {
                            var text = $(".form-control").val();
                            $.ajax({
                                type: "GET", // http请求方式
                                url: "shownoticebyname.htm",
                                data: "n_info=" + text, // 发送给服务器段的数据，这里必须要填的,否则“/check.do?method=loginCheck”得不到数据
                                dataType: 'html', // 告诉JQuery返回的数据格式
                                beforeSend: function () {
                                    //alert("before");
                                },
                                success: function (data) {

                                    var objs = jQuery.parseJSON(data);

                                    var str = "";
                                    var start = "";

                                    if (objs.length == 0) {
                                        start = "<div class='cengt'><strong>申请时间</strong></div><div class='cengt'><strong>标准名称</strong></div><div class='cengt'><strong>标准类型</strong></div><div class='cengf'><strong>状态</strong></div>";

                                    }
                                    else {
                                        start = "<div class='ceng'><strong>申请时间</strong></div><div class='ceng'><strong>标准名称</strong></div><div class='ceng'><strong>标准类型</strong></div><div class='cengo'><strong>状态</strong></div>";

                                    }
             //                    start="<div class='ceng'><strong>申请时间</strong></div><div class='ceng'><strong>标准名称</strong></div><div class='ceng'><strong>标准类型</strong></div><div class='cengo'><strong>状态</strong></div>";
                                    for (var i = 0; i < objs.length; i++) {

                                        if (i == objs.length - 1) {
                                            str = str + "<div class='cengt'>" + objs[i].n_begintime + "</div><div class='cengt'>" + objs[i].n_info + "</div><div class='cengt'>" + objs[i].n_type + "</div>";
                                            if (objs[i].n_state == '未通过') {
                                                str = str + "<div class='cengf' style='color:red'>" + objs[i].n_state + "</div>";
                                            }
                                            if (objs[i].n_state == '等待审核') {
                                                str = str + "<div class='cengf' style='color:yellow'>" + objs[i].n_state + "</div>";
                                            }
                                            if (objs[i].n_state == '通过') {
                                                str = str + "<div class='cengf'>" + objs[i].n_state + "</div>";
                                            }
                                        }
                                        else {
                                            str = str + "<div class='ceng'>" + objs[i].n_begintime + "</div><div class='ceng'>" + objs[i].n_info + "</div><div class='ceng'>" + objs[i].n_type + "</div>";
                                            if (objs[i].n_state == '未通过') {
                                                str = str + "<div class='cengo' style='color:red'>" + objs[i].n_state + "</div>";
                                            }
                                            if (objs[i].n_state == '等待审核') {
                                                str = str + "<div class='cengo' style='color:yellow'>" + objs[i].n_state + "</div>";
                                            }
                                            if (objs[i].n_state == '通过') {
                                                str = str + "<div class='cengo'>" + objs[i].n_state + "</div>";
                                            }
                                        }

                                    }
                                    str = start + str;

                                    $("#noticeresult").html(str);
                                    //   changeColor();
             //                 $('#' + videoId).remove();
             //                 selectedTR = null;
             //                 //alert("删除成功！！");
             //                 tableWidthEquals("titleTable","videoInfoTable");
             //                 changeColorTR("videoInfoTable");
                                }, // 定义交互完成，并且服务器正确返回数据时调用的回调函数
                                error: function (e) {
                                    alert("fail!");
                                }
                            });
                        }
                        function test(n_begintime, n_overtime, n_type, n_state) {

                            $.ajax({
                                type: "POST", // http请求方式
                                url: "shownotice.htm",
                                data: "n_begintime=" + n_begintime + "&n_overtime=" + n_overtime + "&n_type=" + n_type + "&n_state=" + n_state, // 发送给服务器段的数据，这里必须要填的,否则“/check.do?method=loginCheck”得不到数据
                                dataType: 'html', // 告诉JQuery返回的数据格式
                                beforeSend: function () {
                                    //alert("before");
                                },
                                success: function (data) {

             //                    var objs = eval("(" + data + ")");
                                    var objs = jQuery.parseJSON(data);
             //                    var objs = jQuery.parseJSON(data);

                                    var str = "";
                                    var start = "";

                                    if (objs.length == 0) {
                                        start = "<div class='cengt'><strong>申请时间</strong></div><div class='cengt'><strong>标准名称</strong></div><div class='cengt'><strong>标准类型</strong></div><div class='cengf'><strong>状态</strong></div>";

                                    }
                                    else {
                                        start = "<div class='ceng'><strong>申请时间</strong></div><div class='ceng'><strong>标准名称</strong></div><div class='ceng'><strong>标准类型</strong></div><div class='cengo'><strong>状态</strong></div>";

                                    }
             //                    start="<div class='ceng'><strong>申请时间</strong></div><div class='ceng'><strong>标准名称</strong></div><div class='ceng'><strong>标准类型</strong></div><div class='cengo'><strong>状态</strong></div>";
                                    for (var i = 0; i < objs.length; i++) {

                                        if (i == objs.length - 1) {
                                            str = str + "<div class='cengt'>" + objs[i].n_begintime + "</div><div class='cengt'>" + objs[i].n_info + "</div><div class='cengt'>" + objs[i].n_type + "</div>";
                                            if (objs[i].n_state == '未通过') {
                                                str = str + "<div class='cengf' style='color:red'>" + objs[i].n_state + "</div>";
                                            }
                                            if (objs[i].n_state == '等待审核') {
                                                str = str + "<div class='cengf' style='color:yellow'>" + objs[i].n_state + "</div>";
                                            }
                                            if (objs[i].n_state == '通过') {
                                                str = str + "<div class='cengf'>" + objs[i].n_state + "</div>";
                                            }
                                        }
                                        else {
                                            str = str + "<div class='ceng'>" + objs[i].n_begintime + "</div><div class='ceng'>" + objs[i].n_info + "</div><div class='ceng'>" + objs[i].n_type + "</div>";
                                            if (objs[i].n_state == '未通过') {
                                                str = str + "<div class='cengo' style='color:red'>" + objs[i].n_state + "</div>";
                                            }
                                            if (objs[i].n_state == '等待审核') {
                                                str = str + "<div class='cengo' style='color:yellow'>" + objs[i].n_state + "</div>";
                                            }
                                            if (objs[i].n_state == '通过') {
                                                str = str + "<div class='cengo'>" + objs[i].n_state + "</div>";
                                            }
                                        }

                                    }
                                    str = start + str;

                                    $("#noticeresult").html(str);
                                    //   changeColor();
             //                 $('#' + videoId).remove();
             //                 selectedTR = null;
             //                 //alert("删除成功！！");
             //                 tableWidthEquals("titleTable","videoInfoTable");
             //                 changeColorTR("videoInfoTable");
                                }, // 定义交互完成，并且服务器正确返回数据时调用的回调函数
                                error: function (e) {
                                    alert("fail!");
                                }
                            });
                        }


                    </script>
      <div class="middle">
        <ul class="select">
          <li class="select-result">
            <dl>
              <dt>已选条件：</dt>
              <dd class="select-no">暂时没有选择过滤条件</dd>
            </dl>
          </li>
          <li class="select-list">
            <dl id="select1">
              <dt>时间：</dt>
              <dd class="select-all selected"><a href="#" onclick="send(this)">全部</a></dd>
              <dd><a href="#" onclick="send(this)">2015</a></dd>
              <dd><a href="#" onclick="send(this)">2014</a></dd>
              <dd>
                <input type="text" id="c10" onclick="J.calendar.get({to:'c11,min'});"/>
                 <a onclick="send(this)">至</a>　 </dd>
              <dd>
                <input type="text" id="c11" onclick="J.calendar.get({to:'c10,max'});"/>
              </dd>
            </dl>
          </li>
          <li class="select-list">
            <dl id="select2">
              <dt>标准类型：</dt>
              <dd class="select-all selected"><a href="#" onclick="send(this)">全部</a></dd>
              <dd><a href="#" onclick="send(this)">国标</a></dd>
              <dd><a href="#" onclick="send(this)">行标</a></dd>
              <dd><a href="#" onclick="send(this)">企标</a></dd>
            </dl>
          </li>
          <li class="select-list">
            <dl id="select3">
              <dt>状态：</dt>
              <dd class="select-all selected"><a href="#" onclick="send(this)">全部</a></dd>
              <dd><a href="#" onclick="send(this)">等待审核</a></dd>
              <dd><a href="#" onclick="send(this)">通过</a></dd>
              <dd><a href="#" onclick="send(this)">未通过</a></dd>
            </dl>
          </li>
          <li class="select-list">
            <dl id="select4">
              <dt>标准名称：</dt>
              <div style="margin-left:25px;">
                <form class="bs-example bs-example-form" role="form">
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="input-group">
                        <input type="text" class="form-control" style="float:left;">
                        <div style="float:left"> <span class="input-group-btn">
                          <button class="btn btn-default" type="button" onclick="sendInfo()"> 筛选 </button>
                          </span> </div>
                      </div>
                      <!-- /input-group --> 
                    </div>
                    <!-- /.col-lg-6 --> 
                  </div>
                  <!-- /.row -->
                </form>
              </div>
            </dl>
          </li>
        </ul>
        <div id="noticeresult" style="width:70%; margin:auto; height:350px;">
                            <div class="ceng"><strong>申请时间</strong></div>
                            <div class="ceng"><strong>标准名称</strong></div>
                            <div class="ceng"><strong>标准类型</strong></div>
                            <div class="cengo"><strong>状态</strong></div>

<!--                                      <div class="ceng">2015年02月10日</div>
                                      <div class="ceng">中国标准化</div>
                                      <div class="ceng">国标</div>
                                      <div class="cengo" style="color:red">等待审核</div>
                                      <div class="ceng">2015年02月10日</div>
                                      <div class="ceng">中国标准化</div>
                                      <div class="ceng">行标</div>
                                      <div class="cengo">通过</div>
                                      <div class="cengt">2015年02月10日</div>
                                      <div class="cengt">中国标准化</div>
                                      <div class="cengt">企标</div>
                                      <div class="cengf">未通过</div>-->

                            <c:forEach items="${jsonarray}" var="data" varStatus="stat">

                                <c:choose>
                                    <c:when test="${stat.last}">
                                        <div class="cengt">${data.n_begintime}</div>
                                        <div class="cengt">${data.n_info}</div>
                                        <div class="cengt">${data.n_type}</div>
                                        <c:if test="${data.n_state=='未通过'}">
                                            <div class="cengf" style="color:red">${data.n_state}</div>

                                        </c:if>
                                        <c:if test="${data.n_state=='等待审核'}">
                                            <div class="cengf" style="color:yellow">${data.n_state}</div>

                                        </c:if>
                                        <c:if test="${data.n_state=='通过'}">
                                            <div class="cengf">${data.n_state}</div>

                                        </c:if> 
                                    </c:when>

                                    <c:otherwise>
                                        <div class="ceng">${data.n_begintime}</div>
                                        <div class="ceng">${data.n_info}</div>
                                        <div class="ceng">${data.n_type}</div>
                                        <c:if test="${data.n_state=='未通过'}">
                                            <div class="cengo" style="color:red">${data.n_state}</div>

                                        </c:if>
                                        <c:if test="${data.n_state=='等待审核'}">
                                            <div class="cengo" style="color:yellow">${data.n_state}</div>

                                        </c:if>
                                        <c:if test="${data.n_state=='通过'}">
                                            <div class="cengo">${data.n_state}</div>

                                        </c:if>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </div>
        <div>
<!--          <div class="yahoo"></div>-->
        </div>
      </div>
      <div class="bottom">
        <div class="neirongo">新华社地址：中国北京宣武门西大街57号　|　邮政编码：100803　|　电话总机：(010)63071114</div>
        <div class="neirongt">Copyright &copy;2000-2015 XINHUANET.com All Rights Reserved.</div>
      </div>
      </p>
    </div>
    <div class="tab-pane fade" id="edit">
      <p>
      <div class="position">
        <p id="lanrenzhijia1" style="float:left;"></p>
        <p id="lanrenzhijia2"></p>
        <script>
Date.prototype.Format = function (fmt) { //javascript时间日期函数
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日        
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
	var time1 = new Date().Format("yyyy年MM月dd日");   //获取日期，格式： 年-月-日
	
	document.getElementById('lanrenzhijia1').innerHTML = time1;  //将时间赋值给t1
</script>　　欢迎您回来　您当前的位置：标准申请 </div>
      <table>
        <thead>
          <tr>
            <th>计划号</th>
            <th>标准类型</th>
            <th>项目名称</th>
            <th>标准名称</th>
            <th>标准阶段</th>
            <th>状态</th>
            <th>申请说明</th>
          </tr>
        </thead>
        <tbody>
          <tr class="parent selected" id="row_01">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr class="child_row_01">
            <td colspan="7" ><div style="width:100%; height:150px;">
                <input type="hidden" value=${detailorder.status } id="o_status" />
                <!--后台传到页面的数据-->
                
                <div class="stepInfo">
                  <ul>
                    <li></li>
                    <li></li>
                  </ul>
                  <div class="stepIco stepIco1" id="create">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="createText">草案</div>
                    <div style=" color:#000;margin-left:3em; width:70px; margin-top:2em;">当前时间：</div>
                  </div>
                  <div class="stepIco stepIco2" id="check">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="checkText">征求意见</div>
                    <div  style=" color:#000;margin-left:3em;  width:90px; margin-top:2em;">计划完成时间：</div>
                  </div>
                  <div class="stepIco stepIco3" id="produce">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="produceText">送审</div>
                  </div>
                  <div class="stepIco stepIco4" id="delivery">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="deliveryText">报批</div>
                    <div style=" color:#000; margin-left:-7em; width:70px; margin-top:2em;">延期/正常</div>
                  </div>
                </div>
              </div>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">编辑</button>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">下载</button>
              <button type="button" class="btn btn-primary" data-toggle="collapse" style=" width:100px; height:40px; margin-bottom:20px;"
   data-target="#firsttj"> 提交审核 </button>
              <div id="firsttj" class="collapse in"> 
                <script language="javascript">
function addFile() {
var fileDiv = document.all['fileDiv'];
var strHtml = '<span><input type="text" style=" width:220px; height:20px;"></span>';
fileDiv.innerHTML += strHtml;
}

function removeFile(obj) {
obj.removeNode(true);
}
</script>
                <form enctype="multipart/form-data">
                  <div id="fileDiv" style="margin-left:55px;"></div>
                  <a href="javascript:void(addFile());" class="file filebtn">
                  <input class="fbtn" type="file"  />
                  <img src="image/fujian.jpg" width="90" height="37" /></a>
                </form>
              </div></td>
          </tr>
          <tr class="parent selected" id="row_02">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr class="child_row_02">
            <td colspan="7"><div style="width:100%; height:150px;">
                <input type="hidden" value=${detailorder.status } id="o_status" />
                <!--后台传到页面的数据-->
                
                <div class="stepInfo">
                  <ul>
                    <li></li>
                    <li></li>
                  </ul>
                  <div class="stepIco stepIco1" id="create">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="createText">草案</div>
                    <div style=" color:#000;margin-left:3em; width:70px; margin-top:2em;">当前时间：</div>
                  </div>
                  <div class="stepIco stepIco2" id="check">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="checkText">征求意见</div>
                    <div  style=" color:#000;margin-left:3em;  width:90px; margin-top:2em;">计划完成时间：</div>
                  </div>
                  <div class="stepIco stepIco3" id="produce">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="produceText">送审</div>
                  </div>
                  <div class="stepIco stepIco4" id="delivery">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="deliveryText">报批</div>
                    <div style=" color:#000; margin-left:-7em; width:70px; margin-top:2em;">延期/正常</div>
                  </div>
                </div>
              </div>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">编辑</button>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">下载</button>
              <button type="button" class="btn btn-primary" data-toggle="collapse" style=" width:100px; height:40px; margin-bottom:20px;"
   data-target="#twotj"> 提交审核 </button>
              <div id="twotj" class="collapse in">
                <form enctype="multipart/form-data">
                  <div id="fileDiv" style="margin-left:55px;"></div>
                  <a href="javascript:void(addFile());" class="file filebtn">
                  <input class="fbtn" type="file"  />
                  <img src="image/fujian.jpg" width="90" height="37" /></a>
                </form>
              </div></td>
          </tr>
          <tr class="parent selected" id="row_03">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr class="child_row_03">
            <td colspan="7"><div style="width:100%; height:150px;">
                <input type="hidden" value=${detailorder.status } id="o_status" />
                <!--后台传到页面的数据-->
                
                <div class="stepInfo">
                  <ul>
                    <li></li>
                    <li></li>
                  </ul>
                  <div class="stepIco stepIco1" id="create">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="createText">草案</div>
                    <div style=" color:#000;margin-left:3em; width:70px; margin-top:2em;">当前时间：</div>
                  </div>
                  <div class="stepIco stepIco2" id="check">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="checkText">征求意见</div>
                    <div  style=" color:#000;margin-left:3em;  width:90px; margin-top:2em;">计划完成时间：</div>
                  </div>
                  <div class="stepIco stepIco3" id="produce">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="produceText">送审</div>
                  </div>
                  <div class="stepIco stepIco4" id="delivery">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="deliveryText">报批</div>
                    <div style=" color:#000; margin-left:-7em; width:70px; margin-top:2em;">延期/正常</div>
                  </div>
                </div>
              </div>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">编辑</button>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">下载</button>
              <button type="button" class="btn btn-primary" data-toggle="collapse" style=" width:100px; height:40px; margin-bottom:20px;"
   data-target="#threetj"> 提交审核 </button>
              <div id="threetj" class="collapse in">
                <form enctype="multipart/form-data">
                  <div id="fileDiv" style="margin-left:55px;"></div>
                  <a href="javascript:void(addFile());" class="file filebtn">
                  <input class="fbtn" type="file"  />
                  <img src="image/fujian.jpg" width="90" height="37" /></a>
                </form>
              </div></td>
          </tr>
          <tr class="parent selected" id="row_04">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr class="child_row_04">
            <td colspan="7"><div style="width:100%; height:150px;">
                <input type="hidden" value=${detailorder.status } id="o_status" />
                <!--后台传到页面的数据-->
                
                <div class="stepInfo">
                  <ul>
                    <li></li>
                    <li></li>
                  </ul>
                  <div class="stepIco stepIco1" id="create">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="createText">草案</div>
                    <div style=" color:#000;margin-left:3em; width:70px; margin-top:2em;">当前时间：</div>
                  </div>
                  <div class="stepIco stepIco2" id="check">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="checkText">征求意见</div>
                    <div  style=" color:#000;margin-left:3em;  width:90px; margin-top:2em;">计划完成时间：</div>
                  </div>
                  <div class="stepIco stepIco3" id="produce">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="produceText">送审</div>
                  </div>
                  <div class="stepIco stepIco4" id="delivery">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="deliveryText">报批</div>
                    <div style=" color:#000; margin-left:-7em; width:70px; margin-top:2em;">延期/正常</div>
                  </div>
                </div>
              </div>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">编辑</button>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">下载</button>
              <button type="button" class="btn btn-primary" data-toggle="collapse" style=" width:100px; height:40px; margin-bottom:20px;"
   data-target="#fourtj"> 提交审核 </button>
              <div id="fourtj" class="collapse in">
                <form enctype="multipart/form-data">
                  <div id="fileDiv" style="margin-left:55px;"></div>
                  <a href="javascript:void(addFile());" class="file filebtn">
                  <input class="fbtn" type="file"  />
                  <img src="image/fujian.jpg" width="90" height="37" /></a>
                </form>
              </div></td>
          </tr>
          <tr class="parent selected" id="row_05">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr class="child_row_05">
            <td colspan="7"><div style=" width:100%; height:150px;">
                <input type="hidden" value=${detailorder.status } id="o_status" />
                <!--后台传到页面的数据-->
                
                <div class="stepInfo">
                  <ul>
                    <li></li>
                    <li></li>
                  </ul>
                  <div class="stepIco stepIco1" id="create">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="createText">草案</div>
                    <div style=" color:#000;margin-left:3em; width:70px; margin-top:2em;">当前时间：</div>
                  </div>
                  <div class="stepIco stepIco2" id="check">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="checkText">征求意见</div>
                    <div  style=" color:#000;margin-left:3em;  width:90px; margin-top:2em;">计划完成时间：</div>
                  </div>
                  <div class="stepIco stepIco3" id="produce">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="produceText">送审</div>
                  </div>
                  <div class="stepIco stepIco4" id="delivery">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="deliveryText">报批</div>
                    <div style=" color:#000; margin-left:-7em; width:70px; margin-top:2em;">延期/正常</div>
                  </div>
                </div>
              </div>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">编辑</button>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">下载</button>
              <button type="button" class="btn btn-primary" data-toggle="collapse" style=" width:100px; height:40px; margin-bottom:20px;"
   data-target="#fivetj"> 提交审核 </button>
              <div id="fivetj" class="collapse in">
                <form enctype="multipart/form-data">
                  <div id="fileDiv" style="margin-left:55px;"></div>
                  <a href="javascript:void(addFile());" class="file filebtn">
                  <input class="fbtn" type="file"  />
                  <img src="image/fujian.jpg" width="90" height="37" /></a>
                </form>
              </div></td>
          </tr>
          <tr class="parent selected" id="row_06">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr class="child_row_06">
            <td colspan="7"><div style="width:100%; height:150px;">
                <input type="hidden" value=${detailorder.status } id="o_status" />
                <!--后台传到页面的数据-->
                
                <div class="stepInfo">
                  <ul>
                    <li></li>
                    <li></li>
                  </ul>
                  <div class="stepIco stepIco1" id="create">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="createText">草案</div>
                    <div style=" color:#000;margin-left:3em; width:70px; margin-top:2em;">当前时间：</div>
                  </div>
                  <div class="stepIco stepIco2" id="check">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="checkText">征求意见</div>
                    <div  style=" color:#000;margin-left:3em;  width:90px; margin-top:2em;">计划完成时间：</div>
                  </div>
                  <div class="stepIco stepIco3" id="produce">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="produceText">送审</div>
                  </div>
                  <div class="stepIco stepIco4" id="delivery">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="deliveryText">报批</div>
                    <div style=" color:#000; margin-left:-7em; width:70px; margin-top:2em;">延期/正常</div>
                  </div>
                </div>
              </div>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">编辑</button>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">下载</button>
              <button type="button" class="btn btn-primary" data-toggle="collapse" style=" width:100px; height:40px; margin-bottom:20px;"
   data-target="#sixtj"> 提交审核 </button>
              <div id="sixtj" class="collapse in">
                <form enctype="multipart/form-data">
                  <div id="fileDiv" style="margin-left:55px;"></div>
                  <a href="javascript:void(addFile());" class="file filebtn">
                  <input class="fbtn" type="file"  />
                  <img src="image/fujian.jpg" width="90" height="37" /></a>
                </form>
              </div></td>
          </tr>
          <tr class="parent selected" id="row_07">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr class="child_row_07">
            <td colspan="7"><div style="width:100%; height:150px;">
                <input type="hidden" value=${detailorder.status } id="o_status" />
                <!--后台传到页面的数据-->
                
                <div class="stepInfo">
                  <ul>
                    <li></li>
                    <li></li>
                  </ul>
                  <div class="stepIco stepIco1" id="create">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="createText">草案</div>
                    <div style=" color:#000;margin-left:3em; width:70px; margin-top:2em;">当前时间：</div>
                  </div>
                  <div class="stepIco stepIco2" id="check">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="checkText">征求意见</div>
                    <div  style=" color:#000;margin-left:3em;  width:90px; margin-top:2em;">计划完成时间：</div>
                  </div>
                  <div class="stepIco stepIco3" id="produce">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="produceText">送审</div>
                  </div>
                  <div class="stepIco stepIco4" id="delivery">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="deliveryText">报批</div>
                    <div style=" color:#000; margin-left:-7em; width:70px; margin-top:2em;">延期/正常</div>
                  </div>
                </div>
              </div>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">编辑</button>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">下载</button>
              <button type="button" class="btn btn-primary" data-toggle="collapse" style=" width:100px; height:40px; margin-bottom:20px;"
   data-target="#seventj"> 提交审核 </button>
              <div id="seventj" class="collapse in">
                <form enctype="multipart/form-data">
                  <div id="fileDiv" style="margin-left:55px;"></div>
                  <a href="javascript:void(addFile());" class="file filebtn">
                  <input class="fbtn" type="file"  />
                  <img src="image/fujian.jpg" width="90" height="37" /></a>
                </form>
              </div></td>
          </tr>
          <tr class="parent selected" id="row_08">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr class="child_row_08">
            <td colspan="7"><div style=" width:100%; height:150px;">
                <input type="hidden" value=${detailorder.status } id="o_status" />
                <!--后台传到页面的数据-->
                
                <div class="stepInfo">
                  <ul>
                    <li></li>
                    <li></li>
                  </ul>
                  <div class="stepIco stepIco1" id="create">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="createText">草案</div>
                    <div style=" color:#000;margin-left:3em; width:70px; margin-top:2em;">当前时间：</div>
                  </div>
                  <div class="stepIco stepIco2" id="check">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="checkText">征求意见</div>
                    <div  style=" color:#000;margin-left:3em;  width:90px; margin-top:2em;">计划完成时间：</div>
                  </div>
                  <div class="stepIco stepIco3" id="produce">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="produceText">送审</div>
                  </div>
                  <div class="stepIco stepIco4" id="delivery">
                    <div style="background:#FFF; margin-top:11px; margin-left:-5px;height:2px;width:10px;display: inline-block;">
                      <div style="top:-7px;height: 0px;border: 5px #FFF solid;border-top-color: transparent;border-right-color: 
transparent;border-bottom-color: transparent;display: inline-block;position: relative;left: 10px;"></div>
                    </div>
                    <div class="stepText" id="deliveryText">报批</div>
                    <div style=" color:#000; margin-left:-7em; width:70px; margin-top:2em; ">延期/正常</div>
                  </div>
                </div>
              </div>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">编辑</button>
              <button type="button" style=" width:100px; height:40px; margin-bottom:20px;" class="btn btn-primary">下载</button>
              <button type="button" class="btn btn-primary" data-toggle="collapse" style=" width:100px; height:40px; margin-bottom:20px;"
   data-target="#eighttj"> 提交审核 </button>
              <div id="eighttj" class="collapse in">
                <form enctype="multipart/form-data">
                  <div id="fileDiv" style="margin-left:55px;"></div>
                  <a href="javascript:void(addFile());" class="file filebtn">
                  <input class="fbtn" type="file"  />
                  <img src="image/fujian.jpg" width="90" height="37" /></a>
                </form>
              </div></td>
          </tr>
        </tbody>
      </table>
      <div class="bottom">
        <div class="neirongo">新华社地址：中国北京宣武门西大街57号　|　邮政编码：100803　|　电话总机：(010)63071114</div>
        <div class="neirongt">Copyright &copy;2000-2015 XINHUANET.com All Rights Reserved.</div>
      </div>
      </p>
    </div>
    <div class="tab-pane fade in active" id="query">
      <p>
      <div class="cxmiddle">
        <div class="cxwb">
          <form method="get" name="" target="_blank" style="width:487px;height:34px; float:left">
            <div id="searchTxt" class="searchTxt" onMouseOver="this.className='searchTxt searchTxtHover';" onMouseOut="this.className='searchTxt';">
              <div class="radius" style="top:-2px;"></div>
              <div class="radius" style="top:31px;"></div>
              <div class="searchMenu"> </div>
              <input name="w" type="text" placeholder="Search..." style="width:100%; padding-left:5px; border:none; height:30px;" />
            </div>
            <div class="searchBtn" style="float:left;">
              <button id="searchBtn" type="submit"></button>
            </div>
          </form>
          <div class="cxbtn">
            <button type="button" class="btn btn-info btn-lg" style="width:90px; float:right;" onclick="window.location.href='advanceSearch.htm'">高级检索</button>
            <button type="button" class="btn btn-info btn-lg" style="width:90px; float:right;" onclick="window.location.href='classifySearch.htm'">分类检索</button>
          </div>
          <script type="text/javascript">
$(function(){ 

	$("#searchSelected").click(function(){ 
		$("#searchTab").show();
		$(this).addClass("searchOpen");
	}); 

	$("#searchTab li").hover(function(){
		$(this).addClass("selected");
	},function(){
		$(this).removeClass("selected");
	});
	 
	$("#searchTab li").click(function(){
		$("#searchSelected").html($(this).html());
		$("#searchTab").hide();
		$("#searchSelected").removeClass("searchOpen");
	});
	
});
</script></div>
          <script>
              var pageSize =7;
              
              var standerCount ;
              var itemCount;
               var dataCount;
                var jsonArray;
               var standerJson;
                var itemJson ;
              var dataJson ;
              var countJson;
              var key;
               $(document).ready(function(){
                  
                    key = '${keyword}';
             
                    jsonArray = ${array};

                    standerJson = jsonArray[0].STANDARSD;
                    itemJson = jsonArray[1].Item;
                   dataJson = jsonArray[2].Data;
                    countJson = jsonArray[3];
                   
                   standerCount = countJson.StandarCon;//标准的查询数量
                   itemCount = countJson.ItemCon;	//术语
                    dataCount = countJson.DataCon;//数据
                    var currentPage = 0;
                    var pageCount;

                    var state = "0";//显示的状态 0：标准 1：术语
                    var temp="";
                    var strStander="";
                    var strItem="";
                    var strData="";
                   
                    if(standerCount>=pageSize){
                            for (var i = 0; i < pageSize; i++) {
                           // temp = standerJson[i].StructNum.toString();
                              strStander = strStander + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + standerJson[i].StructNum + "&path=" + standerJson[i].ResourceNo + "&keyword=" + key + "'>" + standerJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + standerJson[i].StandardTitle + "</div> </div>";
                        }
                        $("#SA_TYPE").html(strStander);
                        showPage(1,1);
                    }
                    else{
                          for (var i = 0; i < standerJson.length; i++) {
                           // temp = standerJson[i].StructNum.toString();
                              strStander = strStander + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + standerJson[i].StructNum + "&path=" + standerJson[i].ResourceNo + "&keyword=" + key + "'>" + standerJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + standerJson[i].StandardTitle + "</div> </div>";
                        }
                        $("#SA_TYPE").html(strStander);
                        showPage(1,1);
                     }
                     
                     if(itemCount>=pageSize){
                        for (var i = 0; i < pageSize; i++) {
      
                           strItem = strItem + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + itemJson[i].StructNum + "&path=" + itemJson[i].ResourceNo + "&keyword=" + key + "'>" + itemJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + itemJson[i].StandardTitle + "</div> </div>";
                       }
                       $("#IT_TYPE").html(strItem);
                     }
                     else{
                         for (var i = 0; i < itemJson.length; i++) {
      
                           strItem = strItem + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + itemJson[i].StructNum + "&path=" + itemJson[i].ResourceNo + "&keyword=" + key + "'>" + itemJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + itemJson[i].StandardTitle + "</div> </div>";
                       }
                       $("#IT_TYPE").html(strItem);
                    }
                    
                    if(dataCount>=pageSize){
                      for (var i = 0; i < pageSize; i++) {
    //                     
                        strData = strData + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + dataJson[i].StructNum + "&path=" + dataJson[i].ResourceNo + "&keyword=" + key + "'>" + dataJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + dataJson[i].StandardTitle + "</div> </div>";
                    }

                    $("#DA_TYPE").html(strData);
                }
                
                else{
                     for (var i = 0; i < dataJson.length; i++) {
    //                     
                        strData = strData + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + dataJson[i].StructNum + "&path=" + dataJson[i].ResourceNo + "&keyword=" + key + "'>" + dataJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + dataJson[i].StandardTitle + "</div> </div>";
                    }

                    $("#DA_TYPE").html(strData);
                }
                }); 
              
           var $paging;
function showPage(page,type) {
var newCounts;
  var strStander="";
var strItem="";
var strData="";
$(".yahoo").html("");
if(type == '1'){
    newCounts=standerCount;
    if(page*pageSize<newCounts){
       for (var i = (page-1)*pageSize; i < page*pageSize; i++) {
        // temp = standerJson[i].StructNum.toString();
           strStander = strStander + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + standerJson[i].StructNum + "&path=" + standerJson[i].ResourceNo + "&keyword=" + key + "'>" + standerJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + standerJson[i].StandardTitle + "</div> </div>";
     }
      $("#SA_TYPE").html("");
     $("#SA_TYPE").html(strStander);
        
      
    }
    else{
          for (var i = (page-1)*pageSize; i < newCounts; i++) {
        // temp = standerJson[i].StructNum.toString();
           strStander = strStander + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + standerJson[i].StructNum + "&path=" + standerJson[i].ResourceNo + "&keyword=" + key + "'>" + standerJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + standerJson[i].StandardTitle + "</div> </div>";
     }
      $("#SA_TYPE").html("");
     $("#SA_TYPE").html(strStander);
    }
}
if(type == '2'){
   
    newCounts=itemCount;
     if(page*pageSize<newCounts){
       for (var i = (page-1)*pageSize; i < page*pageSize; i++) {
        // temp = standerJson[i].StructNum.toString();
        strItem = strItem + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + itemJson[i].StructNum + "&path=" + itemJson[i].ResourceNo + "&keyword=" + key + "'>" + itemJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + itemJson[i].StandardTitle + "</div> </div>";
     }
      $("#IT_TYPE").html("");
     $("#IT_TYPE").html(strItem);
        
      
    }
    else{
          for (var i = (page-1)*pageSize; i < newCounts; i++) {
        // temp = standerJson[i].StructNum.toString();
        strItem = strItem + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + itemJson[i].StructNum + "&path=" + itemJson[i].ResourceNo + "&keyword=" + key + "'>" + itemJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + itemJson[i].StandardTitle + "</div> </div>";
     }
      $("#IT_TYPE").html("");
     $("#IT_TYPE").html(strItem);
    }
    }
    
 if(type == '3'){
      newCounts=dataCount;
          if(page*pageSize<newCounts){
       for (var i = (page-1)*pageSize; i < page*pageSize; i++) {
        // temp = standerJson[i].StructNum.toString();
           strData = strData + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + dataJson[i].StructNum + "&path=" + dataJson[i].ResourceNo + "&keyword=" + key + "'>" + dataJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + dataJson[i].StandardTitle + "</div> </div>";
     }
      $("#DA_TYPE").html("");
     $("#DA_TYPE").html(strData);
        
      
    }
    else{
          for (var i = (page-1)*pageSize; i < newCounts; i++) {
        // temp = standerJson[i].StructNum.toString();
              strData = strData + "<div style='height=40px'><div><a style='width:100%;height:20px' href='getRootStruct.htm?id=" + dataJson[i].StructNum + "&path=" + dataJson[i].ResourceNo + "&keyword=" + key + "'>" + dataJson[i].StandardName + "</a></div><div style='text-indent:2em'> " + dataJson[i].StandardTitle + "</div> </div>";
     }
      $("#DA_TYPE").html("");
     $("#DA_TYPE").html(strData);
    }
}
             
var pageCount = newCounts%pageSize==0?newCounts/pageSize:Math.ceil(newCounts/pageSize); 
//pageCount = 20;
pageNo = page;
var prevPage = pageNo - 1;
var nextPage = pageNo + 1;
var strHtml = '';
strHtml += '<ul style="margin-left:40%;margin-top:30px">';
if (prevPage < 1) {
    strHtml += '';
} else {
    strHtml += '<li title="Prev Page" style="float:left"><a href="#" onclick="gotoPage('+prevPage+','+type+')"><</a></li>';
}
if(pageCount==0) {
    strHtml += '<li title="Page0" style="float:left"></li>';
}else if(pageCount>0&&pageCount<=5) {
    for (var i = 1; i <= pageCount; i++) {
        if (i == pageNo) {
            strHtml += '<li title="Page ' + i + '" style="float:left">' + i + '</li>';
        } else {
            strHtml += '<li title="Page ' + i + '" style="float:left"><a href="#" onclick="gotoPage('+i+','+type+')">'+i+'</a>';
        }
    }
}else{
    if(pageNo<=4) {
        for (var i = 1; i <= 5; i++) {
            if (i == pageNo) {
                strHtml += '<li title="Page ' + i + '" style="float:left">' + i + '</li>';
            } else {
                strHtml += '<li title="Page ' + i + '" style="float:left"><a href="#" onclick="gotoPage('+i+','+type+')">'+i+'</a>';
            }
        }
        strHtml += '...<a href="#" onclick="gotoPage('+pageCount+','+type+')">'+pageCount+'</a></li>'
    }else if(pageNo>4&&pageNo<pageCount-3) {
        strHtml += '<li title="Page ' + i + '" style="float:left"><a href="#" onclick="gotoPage(1,)">1</a>...</li>'
        for (var i = pageNo-2; i <= pageNo+2; i++) {
            if (i == pageNo) {
                strHtml += '<li title="Page ' + i + '" style="float:left">' + i + '</li>';
            } else {
                strHtml += '<li title="Page ' + i + '" style="float:left"><a href="#" onclick="gotoPage('+i+','+type+')">'+i+'</a>';
            }
        }
        strHtml += '...<a href="#" onclick="gotoPage('+pageCount+','+type+')">'+pageCount+'</a></li>'
    }else {
        strHtml += '<li title="Page ' + i + '" style="float:left"><a href="#" onclick="gotoPage(1,)">1</a>...</li>'
        for (var i = pageCount-4; i <= pageCount; i++) {
            if (i == pageNo) {
                strHtml += '<li title="Page ' + i + '" style="float:left">' + i + '</li>';
            } else {
                strHtml += '<li title="Page ' + i + '" style="float:left"><a href="#" onclick="gotoPage('+i+','+type+')">'+i+'</a>';
            }
        }
    }
}
if (nextPage > pageCount) {
    strHtml += '';
} else {
    strHtml += '<li title="Next Page" style="float:left"><a href="#" onclick="gotoPage('+nextPage+','+type+')">></a></li>';
//  console.log("ss");
}
strHtml += '<li style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;总共'+pageCount+'页</li>'
strHtml += '</ul>';
$paging = strHtml;
$(".yahoo").append(strHtml);
}
 
function gotoPage(pageNo,type) {
    page = pageNo;
    $(".yahoo").html("");
    showPage(page,type);
} 
 

              
          </script>
       
          
          
        <div id="wrapper" style="margin:auto; width:100%;">
          <div class="content tc">
            <div class="mainContents oh">
              <div class="ctnerWrapper">
                <div class="ctnerBox">
                  <div id="cbox-1" class="cbox tl dn">
                      <div class="ctnerTab pr tc"> <a href="#" class="on" onclick="showPage(1,1)">全文</a><a href="#" onclick="showPage(1,2)">术语</a> <a href="#" onclick="showPage(1,3)">数据</a> <a href="#">指标</a> </div>
                    <div class="hotNavs rtNavs dn oh pt15">
                      <div class="navArea oh" id="SA_TYPE">1</div>
                    </div>
                    <div class="myNavs rtNavs dn  pt15">
                        <div class="navArea oh" id="IT_TYPE"> 2 </div>
                    </div>
                    <div class="myNavs rtNavs dn pt15">
                      <div class="navArea oh" id="DA_TYPE"> 3 </div>
                    </div>
                    <div class="myNavs rtNavs dn pt15">
                      <div class="navArea oh"> 4 </div>
                    </div>
                  </div>
                </div>
              </div>
              <script src="js/jqueryone.min.js"></script> 
              <script src="js/jquery.autocomplete.min.js"></script> 
              <script>

                
                //搜索框自动补全; 
                $('.ctnerTab a').click(function(){
                    if(!$(this).hasClass('on')){
                        $('.ctnerTab a').removeClass('on').eq($(this).index()).addClass('on');
                        $('.rtNavs').stop(true,true).hide(200).eq($(this).index()).show(300);
                    }
                });
                
                $('.menusWrapper a').click(function(){
                    if(!$(this).hasClass('active')){
                        $('.menusWrapper a').removeClass('active').eq($(this).index()).addClass('active');
                        $('.cbox').stop(true,true).animate({top:318},100).hide().eq($(this).index()).animate({top:0},400).show();
                    }
                });
                
                $('.smallPics a').click(function(){
                    if(!$(this).hasClass('active')){
                        $('.smallPics a').removeClass('active').eq($(this).index()).addClass('active');
                        $('.picLink').stop(true,true).removeClass('active').eq($(this).index()).addClass('active');
                    }
                });
                        
                $('.titleT').hover(function(){
                    if(!$(this).hasClass('on')){
                        $('.titleT').removeClass('on').eq($(this).index()).addClass('on');
                        $('.topicB').stop(true,true).hide().eq($(this).index()).show();
                    }
                });
            </script> 
            </div>
            <div>
              <div class="yahoo">
<!--                  <span class="disabled"> < </span><span class="current">1</span><a href="#?page=2">2</a><a href="#?page=3">3</a><a href="#?page=4">4</a><a href="#?page=5">5</a><a href="#?page=6">6</a><a href="#?page=7">7</a>...<a href="#?page=199">199</a><a href="#?page=200">200</a><a href="#?page=2"> > </a>-->
              
              
              
              </div>
            </div>
<!--              <div class="bottom" style="margin-top:40px">
              <div class="neirongo">新华社地址：中国北京宣武门西大街57号　|　邮政编码：100803　|　电话总机：(010)63071114</div>
              <div class="neirongt">Copyright &copy;2000-2015 XINHUANET.com All Rights Reserved.</div>
            </div>-->
          </div>
        </div>
      </div>
      </p>
    </div>
    <div class="tab-pane fade" id="examination">
      <p>dddd</p>
    </div>
  </div>
  <script>
   $(function () {
      $('#myTab li:eq(0) a').tab('show');
	  $("#nav1").trigger("click");
   });
</script> 
</div>
</body>
</html>
