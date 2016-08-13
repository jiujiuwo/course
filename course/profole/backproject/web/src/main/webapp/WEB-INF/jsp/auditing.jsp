<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link href="css/style_1.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.min_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script src="js/jquery-1.3.2.js" type="text/javascript"></script>
<script type="text/javascript" src="js/showdate.js"></script>
<script type="text/javascript" src="js/lhgcore.js"></script>
<script type="text/javascript" src="js/lhgcalendar.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
     $(document).ready(function() {
          var  userArray=${userArray};
          var standArray=${standArray};
          if(standArray!=null){
              for(var i=0;i<standArray.length;i++){
                  if(standArray[i].s_state =='3'){
                      
                      document.getElementById("RadioGroup1_0").checked='true';
                  }
                  if(standArray[i].s_state =='4'){
                      
                      document.getElementById("RadioGroup1_1").checked='true';
                  }
                  if(standArray[i].s_state =='5'){
                      
                      document.getElementById("RadioGroup1_2").checked='true';
                  }
                  if(standArray[i].s_state =='2'){
                      
                      document.getElementById("RadioGroup1_3").checked='true';
                  }
                  $("#s_checkidear").html(standArray[i].s_checkidear);
//                  alert(standArray[i].s_checkidear);
                  document.getElementById("c_stdname").value=standArray[i].s_stdchaname;
                  document.getElementById("c_planid").value=standArray[i].c_planid;
                  document.getElementById("c_type").value=standArray[i].c_type;
//                   $("#fivec_stdname").html(str2);
//                    $("#fivec_planid").html(str2);
                  document.getElementById("fivec_stdname").value=standArray[i].s_stdchaname;
                  document.getElementById("fivec_planid").value=standArray[i].c_planid;
              }
         }
         var str="";
         var str2="";
//         var userSelect=document.getElementById("e_chief").value;
//         alert(userSelect);
         if(userArray!=null){
             
             for(var i=0;i<userArray.length;i++){
                 
                     str=str+"<option value='"+userArray[i].userid+"'>"+userArray[i].username+"</option>";
                
             }
              for(var j=1;j<userArray.length;j++){
                 
                     str2=str2+"<option value='"+userArray[j].userid+"'>"+userArray[j].username+"</option>";
                
             }
         }
         $("#e_chief").html(str);
         $("#select1").html(str2);
          
        });
    
    
    
    
$(function(){	
	//移到右边
	$('#add').click(function(){
		//先判断是否有选中
		if(!$("#select1 option").is(":selected")){			
			alert("请选择需要移动的选项")
		}
		//获取选中的选项，删除并追加给对方
		else{
			$('#select1 option:selected').appendTo('#select2');
		}	
	});
	
	//移到左边
	$('#remove').click(function(){
		//先判断是否有选中
		if(!$("#select2 option").is(":selected")){			
			alert("请选择需要移动的选项")
		}
		else{
			$('#select2 option:selected').appendTo('#select1');
		}
	});
	
	//全部移到右边
	$('#add_all').click(function(){
		//获取全部的选项,删除并追加给对方
		$('#select1 option').appendTo('#select2');
	});
	
	//全部移到左边
	$('#remove_all').click(function(){
		$('#select2 option').appendTo('#select1');
	});
	
	//双击选项
	$('#select1').dblclick(function(){ //绑定双击事件
		//获取全部的选项,删除并追加给对方
		$("option:selected",this).appendTo('#select2'); //追加给对方
	});
	
	//双击选项
	$('#select2').dblclick(function(){
		$("option:selected",this).appendTo('#select1');
	});
	
});
</script>
<!--下一步-->

<script>
$(function(){
	$("#myTabContent1 div .snext").click(
		function(){
			$(this).parents(".tab-pane").removeClass("in active").next().addClass("in active");	
			var s=$(this).parents(".tab-pane").attr("sid");
			var ss=s-1;
			var $in=$("#myTab .diamond:eq("+ss+")");
			var $index=$("#myTab .diamond:eq("+s+")");
			var img=$in.next().attr("src");
			var imgl=img.substr(img.length-5,1);
			imgl==2 && $in.next().attr("src","images/arrow22.png") || $in.next().attr("src","images/arrow11.png");
			$index.addClass("select1");
			$index.children(".step").addClass("select2");
		}
	)
	$("#myTabContent1 div .sprev").click(
		function(){
			$(this).parents(".tab-pane").removeClass("in active").prev().addClass("in active");
			var s=$(this).parents(".tab-pane").attr("sid")-1;
			var ss=s-1;
			var $in=$("#myTab .diamond:eq("+ss+")");
			var $index=$("#myTab .diamond:eq("+s+")");
			var img=$in.next().attr("src");
			var imgl=img.substr(img.length-5,1);
			imgl==2 && $in.next().attr("src","images/arrow2.png") || $in.next().attr("src","images/arrow1.png");
			$index.removeClass("select1");
			$index.children(".step").removeClass("select2");

		}
	)		
})
</script>
</head>

<body bgcolor="#ececec">
<div class="mttop">
  <div class="mttp"><img src="images/examine.png"></div>
  申请审批</div>
<div class="mtotp">
  <div class="stepbg">
    <input type="hidden" value=${detailorder.status } id="o_status" />
    <!--后台传到页面的数据-->
    
    <div class="stepall  nav-tabs" id="myTab">
      <div class="stepdiv">
        <div class="diamond select1">开始
          <div class="step oneo select2" id="create">1 </div>
        </div>
        <img src="images/arrow2.png" width="45%" height="37"></div>
      <div class="stepdiv">
        <div class="diamond">新标准基础数据
          <div class="step twoo" id="check">2 </div>
        </div>
        <img src="images/arrow1.png" width="45%" height="37"> </div>
      <div class="stepdiv">
        <div class="diamond">时间进度
          <div class="step three" id="produce">3 </div>
        </div>
        <img src="images/arrow2.png" width="45%" height="37"> </div>
      <div class="stepdiv">
        <div class="diamond">人员安排
          <div class="step four" id="delivery">4 </div>
        </div>
        <img src="images/arrow1.png" width="45%" height="37"> </div>
      <div class="stepdiv">
        <div class="diamond">结束
          <div class="step five" id="received">5 </div>
        </div>
      </div>
    </div>
    <div class="stepk">
      <div id="myTabContent1" class="tab-content1">
      
        <div class="tab-pane fade in active" id="theo" sid='1'>
            
          <div class="audc">
            <div  class="audfd">审核结果：</div>
            <form name="form1" method="post" action="">
              <label class="audfd">
                <input type="radio" name="s_state" value="单选" id="RadioGroup1_0">
                通过</label>
              <label class="audfd">
                <input type="radio" name="s_state" value="单选" id="RadioGroup1_1">
                未通过</label>
              <label class="audfd">
                <input type="radio" name="s_state" value="单选" id="RadioGroup1_2">
                返回</label>
              <label class="audfd">
                <input type="radio" name="s_state" value="单选" id="RadioGroup1_3">
                修改</label>
            </form>
          </div>
          <div class="audcot">
            <div class="audfd">审核意见：</div>
            <div class="rowsd">
              <textarea name="" cols="" rows="" style="width:100%; height:200px;" id="s_checkidear"></textarea>
            </div>
          </div>
          <div class="btnauto">
            <button type="button" class="btn btn-default">上一步</button>
            <button type="button" class="btn btn-default" onclick='save1()'>保存</button>
           
            <script>
                function save1(){
                    if(document.getElementById("RadioGroup1_0").checked==true){
                        alert("hheh");
                        document.getElementById("hiddens_state").value='3';
                    }
                    if(document.getElementById("RadioGroup1_1").checked=='true'){
                        document.getElementById("hiddens_state").value='4';
                    }
                    if(document.getElementById("RadioGroup1_2").checked=='true'){
                        document.getElementById("hiddens_state").value='5';
                    }
                    if(document.getElementById("RadioGroup1_3").checked=='true'){
                        document.getElementById("hiddens_state").value='5';
                    }
                    
                    document.getElementById("hiddens_checkidear").value=document.getElementById("s_checkidear").value;
                    document.getElementById("form_1").submit();
                    
                }
                
            </script>
            <button type="button" class="btn btn-default snext" id="next">下一步</button>
          </div>
            <form action='checkStandardOne.htm' method='get' target="blankFrame" id='form_1'>
                <input type='hidden' name="s_state" id='hiddens_state'/>
                <input type="hidden" name='s_checkidear' id='hiddens_checkidear'>
                
                
            </form>
          <iframe name="blankFrame" id="blankFrame" style="display: none;"></iframe>
        </div>
        
        <div class="tab-pane fade" id="thet" sid='2'>
          <div class="audfddc">
            <div class="audfdot">申请标准名称：</div>
            <div class="audfdotk">
                <input name="" type="text" style="height:40px; width:100%;" id="c_stdname">
            </div>
            <div class="audfdot">标准计划号：</div>
            <div class="audfdotk">
              <input name="" type="text" style="height:40px; width:100%;" id='c_planid'>
            </div>
            <div class="audfdot">申请标准类型：</div>
            <div class="audfdotk">
              <input name="" type="text" style="height:40px; width:100%;" id='c_type'>
            </div>
            <div class="btnauto">
              <button type="button" class="btn btn-default sprev">上一步</button>
              <button type="button" class="btn btn-default" onclick='save2()'>保存</button>
              
                 <script>
                function save2(){
                   
                    
                    document.getElementById("hiddenc_stdname").value=document.getElementById("c_stdname").value;
                    document.getElementById("hiddenc_planid").value=document.getElementById("c_planid").value;
                    document.getElementById("hiddenc_type").value=document.getElementById("c_type").value;
                    document.getElementById("form_2").submit();
                    alert(document.getElementById("hiddenc_type").value);
                }
                
            </script>
              <button type="button" class="btn btn-default snext" id="next">下一步</button>
            </div>
             <form action='checkStandardTwo.htm' method='get' target="blankFrame" id='form_2'>
                <input type='hidden' name="c_stdname" id='hiddenc_stdname'/>
                <input type="hidden" name='c_planid' id='hiddenc_planid'>
                 <input type="hidden" name='c_type' id='hiddenc_type'>
                
            </form>
          </div>
        </div>
        <div class="tab-pane fade" id="thef" sid='3'>
          <div class="audfddc">
            <div class="audfdoto">征求意见稿完成时间：</div>
            <div class="audfdotko">
              <input type="text" id="c_idrealytime" style="height:40px; width:80%" onclick="J.calendar.get();"/>
            </div>
            <div class="audfdoto">送审稿完成时间：</div>
            <div class="audfdotko">
              <input type="text" id="c_strealytime" style="height:40px; width:80%" onclick="J.calendar.get();"/>
            </div>
            <div class="audfdoto">报批稿完成时间：</div>
            <div class="audfdotko">
              <input type="text" id="c1" style="height:40px; width:80%" onclick="J.calendar.get();"/>
            </div>
            <div class="btnauto">
              <button type="button" class="btn btn-default sprev">上一步</button>
              <button type="button" class="btn btn-default" onclick='save3()'>保存</button>
                   <script>
                function save3(){
                   
                    
                    document.getElementById("hiddenc_idrealytime").value=document.getElementById("c_idrealytime").value;
                    document.getElementById("hiddenc_strealytime").value=document.getElementById("c_strealytime").value;
                    //document.getElementById("hiddenc_type").value=document.getElementById("c_type").value;
                    document.getElementById("form_3").submit();
                    
                }
                
            </script>
              <button type="button" class="btn btn-default snext" id="next">下一步</button>
            </div>
           
            <form action='checkStandardThree.htm' method='get' target="blankFrame" id='form_3'>
                <input type='hidden' name="c_idrealytime" id='hiddenc_idrealytime'/>
                <input type="hidden" name='c_strealytime' id='hiddenc_strealytime'>
                
                
            </form>
          </div>
        </div>
        <div class="tab-pane fade" id="thes" sid='4'>
          <div class="bxzcy">编写组成员：</div>
          <div class="stepkzot">
            <div  class="audfdzot">主编：</div>
            <div class="audfdotk">
              <select name="e_chief" type="text" style="height:40px; width:100%;" id='e_chief'  onchange="changePerson()">
              </select>
            </div>
            <div  class="audfdzot">编辑：</div>
            <div class="selectbox">
              <div class="select-bar">
                  <select multiple="multiple" id="select1">
<!--                  <option value="超级管理员">超级管理员</option>
                  <option value="普通管理员">普通管理员</option>
                  <option value="信息发布员">信息发布员</option>
                  <option value="财务管理员">财务管理员</option>
                  <option value="产品管理员">产品管理员</option>
                  <option value="资源管理员">资源管理员</option>
                  <option value="系统管理员">系统管理员</option>
                  <option value="超级管理员">超级管理员</option>
                  <option value="普通管理员">普通管理员</option>
                  <option value="信息发布员">信息发布员</option>
                  <option value="财务管理员">财务管理员</option>
                  <option value="产品管理员">产品管理员</option>
                  <option value="资源管理员">资源管理员</option>-->
                </select>
              </div>
                <script>
                    
                    function changePerson(){
                       var chief= document.getElementById("e_chief").value;
                       var  userArray=${userArray};
//                       alert(userArray.length);
                       var str="";
                       if(userArray.length>0){
                            for(var i=0;i<userArray.length;i++){
                                if(userArray[i].userid!=chief){
                                    str=str+"<option value='"+userArray[i].userid+"'>"+userArray[i].username+"</option>";
                                }
                            }
                       }
                        $("#select1").html(str);
                        $("#select2").html("");
                    }
                    
                    
                </script>
              <div class="btn-bar">
                <p><span id="add">
                  <input type="button" class="btn" value="添加" title="移动选择项到右侧"/>
                  </span></p>
                <p><span id="remove">
                  <input type="button" class="btn" value="删除" title="移动选择项到左侧"/>
                  </span></p>
              </div>
              <div class="select-bar">
                <select multiple="multiple" id="select2">
                </select>
              </div>
            </div>
            <div class="btnauto">
              <button type="button" class="btn btn-default sprev">上一步</button>
              <button type="button" class="btn btn-default" onclick="save4()">保存</button>
              <button type="button" class="btn btn-default snext" id="next">下一步</button>
            </div>
             <form action='checkStandardFour.htm' method='get' target="blankFrame" id='form_4'>
                <input type='hidden' name="chiefuserid" id='hiddenuserid'/>
                <input type="hidden" name='editoruserid' id='hiddeneditoruserid'>
                
                
            </form>
<script>
    function save4(){
        var chief = $('#e_chief option:selected') .val();
        var editors="";
        var options=document.getElementById("select2").options;
        if(options.length>0){
             for(var i=0;i<options.length;i++){
                 editors=editors+options[i].value+",";
             }
        }
        editors=editors.substring(0,editors.length-1);
       
        document.getElementById("hiddenuserid").value=chief;
        document.getElementById("hiddeneditoruserid").value=editors;
        document.getElementById("form_4").submit();
    }
    
</script>
          </div>
        </div>
        <div class="tab-pane fade" id="thee" sid='5'>
          <div class="jieguo">审批结束！审批结果：
            <div style="color:red; float:right;">通过</div>
          </div>
          <div class="jieguox">
            <div class="audfdgot">申请名称：</div>
            <div class="audfdotk">
              <input name="" type="text" style="height:40px; width:100%; " id="fivec_stdname">
            </div>
          </div>
          <div class="jieguox">
            <div class="audfdgot">计 划 号：</div>
            <div class="audfdotk">
              <input name="" type="text" style="height:40px; width:100%;" id="fivec_planid">
            </div>
          </div>
          <div class="btnauto">
            <button type="button" class="btn btn-default sprev">上一步</button>
            <button type="button" class="btn btn-default" onclick="save5()">完成</button>
          </div>
             <form action='checkStandardFive.htm' method='get' id='form_5'>
                <input type='hidden' name="c_planid" id='hiddenfivec_planid'/>
                <input type="hidden" name='c_stdname' id='hiddenfivec_stdname'>
                
                
            </form>
            
            <script>
                 function save5(){
                  
                    document.getElementById("hiddenfivec_planid").value=document.getElementById("fivec_planid").value;
                    document.getElementById("hiddenfivec_stdname").value=document.getElementById("fivec_stdname").value;
                    document.getElementById("form_5").submit();
                   
    }
                </script>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
