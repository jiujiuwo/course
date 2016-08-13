<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link href="css/style_1.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.min_1.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/lhgcore.js"></script>
<script type="text/javascript" src="js/lhgcalendar.js"></script>
<script>
    
      $(document).ready(function () {

                var notice = ${noticeArray};
                var standardApply=${standardArray};
               // s_stdchaname
                var str="<option></option>";
                var str2="<option></option>";
                var str3="";
                for(var i=0;i<notice.length;i++){
                    str=str+"<option value='"+notice[i].n_name+"'>"+notice[i].n_name+"</option>";
                }    
                
                for(var j=0;j<standardApply.length;j++){
                     str2=str2+"<option value='"+standardApply[j].s_stdchaname+"'>"+standardApply[j].s_stdchaname+"</option>";
                     if(standardApply[j].s_state=='2'){
                     str3=str3+"<tr style='background:#eff7f9'><td>"+standardApply[j].s_applytime+"</td><td>"+standardApply[j].n_name+"</td><td>"+standardApply[j].s_stdchaname+"</td><td>"+standardApply[j].s_username+"</td><td>等待审核</td><td><a href='audit.htm?s_stdchaname='"+standardApply[j].s_stdchaname+">等待审核</a></td></tr>";
                    }
                     if(standardApply[j].s_state=='3'){
                     str3=str3+"<tr style='background:#eff7f9'><td>"+standardApply[j].s_applytime+"</td><td>"+standardApply[j].n_name+"</td><td>"+standardApply[j].s_stdchaname+"</td><td>"+standardApply[j].s_username+"</td><td>通过审核</td><td>通过审核</td></tr>";
                    }
                     if(standardApply[j].s_state=='4'){
                     str3=str3+"<tr style='background:#eff7f9'><td>"+standardApply[j].s_applytime+"</td><td>"+standardApply[j].n_name+"</td><td>"+standardApply[j].s_stdchaname+"</td><td>"+standardApply[j].s_username+"</td><td>未通过审核</td><td>未通过审核</td></tr>";
                    }
                }
                $("#n_name").html(str);
                $("#s_stdchaname").html(str2);
                $("#tbody").html(str3);
            });
    
</script>
</head>

<body bgcolor="#ECECEC">
<div class="mttop">
  <div class="mttp"><img src="images/examine.png"></div>
  立项审批</div>
<div class="mtotm">
    
  <div class="mtotf">
    <div class="mtotfj">开始时间：</div>
    <input type="text" name='s_applytime' id="c1" class='s_applytime' style="height:25px; width:20%" onclick="J.calendar.get();"/>
  </div>
  <div class="mtoto">
    <div class="mtotfj">项目名称：</div>
    <select id="n_name" name="n_name" style="height:25px; width:20%">
        
    </select>
  </div>
  <div class="mtoto">
    <div class="mtotfj">项目状态：</div>
    <select name="s_state" id='s_state' style="height:25px;width:20%">
        <option ></option>
      <option value='3'>已审核</option>
      <option value='2'>待审核</option>
      <option value='6'>正在审核</option>
<!--      <option value='3'></option>
      <option value='4'></option>
      <option value='5'></option>
      <option value='6'></option>-->
    </select>
  </div>
     <script>
         function sendInfo() {
                            var time=$(".s_applytime").val();
                            var noticename=$("#n_name").val();
                            var noticestate=$("#s_state").val();
                            var standardname=$("#s_stdchaname").val();
                            //alert(time+","+noticename+","+noticestate+","+standardname);
                            $.ajax({
                                type: "GET", // http请求方式
                                url: "screenStandards.htm",
                                data: "s_applytime=" + time+"&n_name="+noticename+"&s_state="+noticestate+"&s_stdchaname="+standardname, // 发送给服务器段的数据，这里必须要填的,否则“/check.do?method=loginCheck”得不到数据
                                dataType: 'html', // 告诉JQuery返回的数据格式
                                beforeSend: function () {
//                                    alert("before");
                                },
                                success: function (data) {

                                    var objs = jQuery.parseJSON(data);
                                    
                                    var str = "";
                                    
                                    if(objs.length>0){
                                        for(var j=0;j<objs.length;j++){
                                        if(objs[j].s_state=='2'){
                                                            str=str+"<tr style='background:#eff7f9'><td>"+objs[j].s_applytime+"</td><td>"+objs[j].n_name+"</td><td>"+objs[j].s_stdchaname+"</td><td>"+objs[j].s_username+"</td><td>等待审核</td><td>等待审核</td></tr>";
                                                           }
                                                            if(objs[j].s_state=='3'){
                                                            str=str+"<tr style='background:#eff7f9'><td>"+objs[j].s_applytime+"</td><td>"+objs[j].n_name+"</td><td>"+objs[j].s_stdchaname+"</td><td>"+objs[j].s_username+"</td><td>通过审核</td><td>通过审核</td></tr>";
                                                           }
                                                            if(objs[j].s_state=='4'){
                                                            str=str+"<tr style='background:#eff7f9'><td>"+objs[j].s_applytime+"</td><td>"+objs[j].n_name+"</td><td>"+objs[j].s_stdchaname+"</td><td>"+objs[j].s_username+"</td><td>未通过审核</td><td>未通过审核</td></tr>";
                                                           }                                         
                                        }
                                    }
                              
                                 
                                     $("#tbody").html(str);
                                        
                              
                                }, // 定义交互完成，并且服务器正确返回数据时调用的回调函数
                                error: function (e) {
//                                    alert("fail!");
                                }
                            });
                        }
                     

        
        
    </script>
  <div class="mtoto">
    <div class="mtotfj">标准名称：</div>
    <select name="s_stdchaname" id="s_stdchaname" style="width:20%" type="text">
    </select>
    <input name="" type="button" style="background:#5c5c5c;border:none;color:#fff;width:50px;border-radius:0 5px 5px 0;"value="筛选" onclick="sendInfo()">
  </div>
        

  <table style="margin:auto">
    <thead>
      <tr style="background:#0c9eee; color:#fff; text-align:center;font-family:'微软雅黑';">
        <th>申请时间</th>
        <th>项目名称</th>
        <th>标准名称</th><th>申请人</th>
        <th>状态</th><th>审批操作</th>
        
      </tr>
    </thead>
    <tbody id="tbody">
    </tbody>
  </table>
   
<!--  <div class="mtfy">
    <ul class="pagination">
      <li class="fypev"><a href="#">&#8249;上一步</a></li>
      <li class="active fynext" ><a href="#">1</a></li>
      <li class="disabled fynext"><a href="#">2</a></li>
      <li class="fynext"><a href="#">3</a></li>
      <li class="fynext"><a href="#">4</a></li>
      <li class="fynext"><a href="#">5</a></li>
      <li class="fynext"><a href="#">6</a></li>
      <li class="fynext"><a href="#">7</a></li>
      <li class="fynext"><a href="#">8</a></li>
      <li class="fynext"><a href="#">9</a></li>
      <li class="fynext"><a href="#">10</a></li>
      <li class="fypev"><a href="#">下一步&#8250;</a></li>
    </ul>
  </div>-->
</div>
</body>
</html>
