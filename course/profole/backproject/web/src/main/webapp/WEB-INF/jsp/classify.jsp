
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
<script type="text/javascript" src='jquery-1.7.1.min.js'></script>
<script src="js/jquery-1.3.2.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
$(".subNav").click(function(){
			$(this).toggleClass("currentDd").siblings(".subNav").removeClass("currentDd")
			$(this).toggleClass("currentDt").siblings(".subNav").removeClass("currentDt")
			
			// 修改数字控制速度， slideUp(500)控制卷起速度
			$(this).next(".navContent").slideToggle(500).siblings(".navContent").slideUp(500);
	})	
})
</script>
<script type="text/javascript">
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

<body>
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
            <div class="yhxg"><a href=""> 个人管理</a>|<a href="">密码修改</a></div>
          </div>
          <div style="border-bottom:1px #EAEAEA solid; width:90%; margin-left:5%; float:left;"></div>
        </div>
      </div>
    </div>
    <div class="two" style="text-align:right; font-size:20px;"><a href="">退出登录</a></div>
  </div>
</div>-->
<div class="tabpage">
<!--  <ul id="myTab" class="nav nav-tabs">
    <li class="active"><a href="#homepage" data-toggle="tab" id="nav0"> 首页</a> </li>
    <li><a href="#proapplication" data-toggle="tab" id="nav1">标准申请</a></li>
    <li><a href="#edit" data-toggle="tab" id="nav2">标准编辑</a></li>
    <li><a href="#query" data-toggle="tab" id="nav3"><span class="badge pull-right">2</span>标准查询</a></li>
    <li><a href="#examination" data-toggle="tab" id="nav4">考试平台</a></li>
  </ul>-->
  <div id="myTabContent" class="tab-content">
    <div class="tab-pane fade" id="homepage">
      <div class="sgpwd">
        <div class="sgfh"><a href="">返回</a></div>
        <div class="sgmm">修改密码</div>
        <div class="sgnr">
          <div class="sgnrys">老密码：</div>
          <div>
            <input name="" type="text" style=" width:79%;height:30px; border-radius:0px; border:1px #55b6c4 solid;">
          </div>
        </div>
        <div class="sgnr">
          <div class="sgnrys">新密码：</div>
          <div>
            <input name="" type="text" style="width:79%;height:30px;border-radius:0px; border:1px #55b6c4 solid;">
          </div>
        </div>
        <div class="sgnr">
          <div class="sgnrys">确认密码：</div>
          <div>
            <input name="" type="text" style="width:79%;height:30px;border-radius:0px; border:1px #55b6c4 solid;">
          </div>
        </div>
        <div class="sgbtn">
          <button type="button" class="btn btn-default" style="width:100%; height:30px; background:#f36948; color:#fff; font-weight:700">提交</button>
        </div>
      </div>
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
          <div id="div1"> <a href="" _fcksavedurl="javascript:">XXX项目可申请，截止时间2015年6月1日</a> <a href="" _fcksavedurl="javascript:">XXX项目可申请，截止时间2015年6月1日</a> </div>
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
      <a  href="standard.html" class="btn btn-danger">申请新标准</a>
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
              <dd class="select-all selected"><a href="#">全部</a></dd>
              <dd><a href="#">2015</a></dd>
              <dd><a href="#">2014</a></dd>
              <dd>
                <input type="text" id="c10" onclick="J.calendar.get({to:'c11,min'});"/>
                至　 </dd>
              <dd>
                <input type="text" id="c11" onclick="J.calendar.get({to:'c10,max'});"/>
              </dd>
            </dl>
          </li>
          <li class="select-list">
            <dl id="select2">
              <dt>标准类型：</dt>
              <dd class="select-all selected"><a href="#">全部</a></dd>
              <dd><a href="#">国标</a></dd>
              <dd><a href="#">行标</a></dd>
              <dd><a href="#">企标</a></dd>
            </dl>
          </li>
          <li class="select-list">
            <dl id="select3">
              <dt>状态：</dt>
              <dd class="select-all selected"><a href="#">全部</a></dd>
              <dd><a href="#">等待审核</a></dd>
              <dd><a href="#">通过</a></dd>
              <dd><a href="#">未通过</a></dd>
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
                          <button class="btn btn-default" type="button" style="font-family:'微软雅黑';"> 筛选 </button>
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
        <div style="width:70%; margin:auto; height:250px;">
          <div class="ceng"><strong>申请时间</strong></div>
          <div class="ceng"><strong>标准名称</strong></div>
          <div class="ceng"><strong>标准类型</strong></div>
          <div class="cengo"><strong>状态</strong></div>
          <div class="ceng">2015年02月10日</div>
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
          <div class="cengf">未通过</div>
        </div>
        <div>
          <div class="yahoo"><span class="disabled"> < </span><span class="current">1</span><a href="#?page=2">2</a><a href="#?page=3">3</a><a href="#?page=4">4</a><a href="#?page=5">5</a><a href="#?page=6">6</a><a href="#?page=7">7</a>...<a href="#?page=199">199</a><a href="#?page=200">200</a><a href="#?page=2"> > </a></div>
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
      <!--****************************构造目录树************************************-->
                <script>
                    
                    function spsh(obj){
                        if(document.getElementById("content_"+obj+"").style.display=='none'){
                            document.getElementById("content_"+obj+"").style.display='block';
                          
                        }
                        else{
                            document.getElementById("content_"+obj+"").style.display='none';
                        }
                    }
                    
                    function backStep(){
                        window.location.href='query.htm';
                    }
                    
                  
                    $(document).ready(function () {
                        var data =${jsonarray};
                        var str="";
                         for (var i = 0; i < data.length; i++) {
                        var showData = data[i];
                        var category = showData.category;
                        str = str +" <div class='subNav' onclick='spsh("+i+")'>"+category+"</div><ul id='content_" + i + "' class='navContent' style='display:none;list-style:none;'>";
                         for (var key in data[i]) {
                            if (key != "category") {
                               //str += "<ul><li><a href='findCategory.htm?category=" + showData[key] + "'>" + showData[key] + "</a></li></ul>";
                               
                                       // str+="<li><a href='findCategory.htm?category=" + showData[key] + "'>"+showData[key]+"</a></li>";
//                                      alert(showData[key]);

                                      
//                                      var sData=showData[key].toString().replaceAll(" ","&nbsp;");

                                      str+="<li><a href='#' onclick=query('"+showData[key]+"')>"+showData[key]+"</a></li>";
          
                            }
                        }
                        str += "</ul>";
                    }

                    $("#classfyResults").html(str);
                    
                    }); 
                    
                    
                    
                     
                    </script>
                    
                    
                    <script>
                        
                         function query(obj){
                               alert(obj);
                                   $.ajax({
                                type: "GET", // http请求方式
                                url: "findCategoryQuery.htm",
                                data: "category=" + obj, // 发送给服务器段的数据，这里必须要填的,否则“/check.do?method=loginCheck”得不到数据
                                dataType: 'html', // 告诉JQuery返回的数据格式
                                beforeSend: function () {
//                                    alert("before");
                                },
                                success: function (data) {

                                    var objs = jQuery.parseJSON(data);
                                    
                                    var str = "";
                                   
                                    if(objs.length>0){
                                        for(var i=0;i<objs.length;i++){
//                                                    <tr style="background:#eff7f9">
//                                                        <td></td>
//                                                        <td></td>
//                                                        <td></td>
//                                                        <td></td>
//                                                        <td></td>
//                                                      </tr>         

                                               var showData = objs[i];
                                            var id = showData.stdId;
                                            var path = showData.stdPath;
                                            var StdNum = showData.stdNum;
                                            var StdEngName = showData.stdEngName;
                                            var StdChaName = showData.stdChaName;
                                            var State = showData.state;
                                            str = str + "<tr style='background:#eff7f9'>" +
                                                    "<td><span id='id'>" + (i + 1) + "</span></td>" +
                                                    "<td><span id='StdNum'><a href='getRootStruct.htm?id=" + id + "&path=" + path + "'>" + StdNum + "</a></span></td>" +
                                                    "<td><span id='StdChaName'>" + StdEngName + "</span></td>" +
                                                    "<td><span id='StdEngName'>" + StdChaName + "</span></td>" +
                                                    "<td><span id='State'>" + State + "</span></td>" +
                                                    "</tr>";
                                                                    }
                                                                }
                              
                                 
                                     $("#QueryClassfyResults").html(str);
                                        
                              
                                }, // 定义交互完成，并且服务器正确返回数据时调用的回调函数
                                error: function (e) {
                                    alert("fail!");
                                }
                            });
                    }
                        </script>
    <div class="tab-pane fade in active" id="query">
      <p>
      <div class="position">
        <div id="lanrenzhijia1" style="float:left;"></div>
        <div id="lanrenzhijia2"></div>
        您当前的位置：分类检索
        <div style="float:right;">
            <input onclick="backStep()" name="" type="button" style="background:#0cc5ec;font-family:'微软雅黑'; border:none; border-radius:5px; margin-top:-15px; color:#fff; font-style:14px; width:70px; height:30px;" value="返回>>">
        </div>
      </div>
      <div class="clmiddle">
        <div class="subNavBox" id="classfyResults">
<!--          <div class="subNav">ICS分类</div>
          <ul class="navContent " style="display:none;list-style:none;">
            <li><a href="#equipment" data-toggle="tab">接收和发射设备</a></li>
            <li><a href="#mobile" data-toggle="tab">移动通信服务</a></li>
            <li><a href="#other" data-toggle="tab">其他无线电通信设备</a></li>
          </ul>
          <div class="subNav">中标分类</div>
          <ul class="navContent" style="list-style:none">
            <li><a href="#technology" data-toggle="tab">L01技术管理</a></li>
            <li><a href="#category" data-toggle="tab">类别名称</a></li>
            <li><a href="#quality" data-toggle="tab">L00标准化、质量管理</a></li>
            <li><a href="#economy" data-toggle="tab">L02经济管理</a></li>
          </ul>
          <div class="subNav">组织类别</div>
          <ul class="navContent" style="list-style:none">
            <li><a href="#engineering" data-toggle="tab">GBJ国家工程建设标准</a></li>
            <li><a href="#country" data-toggle="tab">GB国家标准</a></li>
            <li><a href="#hygiene" data-toggle="tab">GBZ国家职业卫生标准</a></li>
          </ul>
          <div class="subNav">科迪智标测试类别</div>
          <ul class="navContent" style="list-style:none">
            <li><a href="#notebook" data-toggle="tab">笔记本类别</a></li>
            <li><a href="#pc" data-toggle="tab">电脑型号</a></li>
          </ul>
          <div  class="subNav">食品生产许可证</div>
          <ul class="navContent" style="list-style:none" id="myTab">
            <li><a href="#food" data-toggle="tab">类别食品</a></li>
          </ul>
          <div class="subNav">ICS2</div>
          <ul class="navContent nav nav-tabs" style="list-style:none" id="myTab">
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
            <li><a href="#"></a></li>
          </ul>-->
        </div>
        <div id="myTabContent" class="tab-content">
          <div class="tab-pane fade" id="equipment">
            <div class="cltsy" style="float:right;width:80%">
              <div style="float:left">共</div>
              <div style="color:red; float:left;">0</div>
              条标准</div>
            <table style="margin:0 1%;float:right;width:81%">
              <thead>
                <tr style="background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';">
                  <th>序号</th>
                  <th>标准号</th>
                  <th>英文标准名称</th>
                  <th>中文标准名称</th>
                  <th>状态</th>
                </tr>
              </thead>
              <tbody id="QueryClassfyResults">
<!--                <tr style="background:#eff7f9">
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                </tr>
                <tr style="background:#eff7f9">
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                </tr>-->
               
              </tbody>
            </table>
<!--            <ul class="pagination">
              <li style=" border:1px #ccc solid; float:left; width:50px;text-align:center;"><a href="#">&#8249;上一步</a></li>
              <li class="active" style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">1</a></li>
              <li class="disabled" style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">2</a></li>
              <li style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">3</a></li>
              <li style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">4</a></li>
              <li style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">5</a></li>
              <li style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">6</a></li>
              <li style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">7</a></li>
              <li style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">8</a></li>
              <li style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">9</a></li>
              <li style=" border:1px #ccc solid; float:left; width:30px;text-align:center;"><a href="#">10</a></li>
              <li style=" border:1px #ccc solid; float:left; width:50px;text-align:center;"><a href="#">下一步&#8250;</a></li>
            </ul>-->
          </div>
          <div class="tab-pane fade" id="mobile">
            <p>mobile</p>
          </div>
          <div class="tab-pane fade" id="other">
            <p>other</p>
          </div>
          <div class="tab-pane fade" id="technology">
            <p>technology</p>
          </div>
          <div class="tab-pane fade" id="category">
            <p>category</p>
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
