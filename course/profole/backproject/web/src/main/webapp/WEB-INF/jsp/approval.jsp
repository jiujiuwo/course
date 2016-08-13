<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <title>无标题文档</title>
        <link href="css/style_1.css" rel="stylesheet" type="text/css">
        <link href="css/bootstrap.min_1.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/lhgcore.js"></script>
<!--        <script src="js/jquery-1.3.2.js" type="text/javascript"></script>-->
        <script type="text/javascript" src="js/lhgcalendar.js"></script>
        <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $(function () {
                
            

            });
        </script>
        <!- ***********************************->
        <script>
            function show(count) {

                $("#demo" + count + "").toggle();

            }
            function tan(){
                   //移到右边
                $('#add').click(function () {
                  
                    //先判断是否有选中
                    if (!$("#select1 option").is(":selected")) {
                       // alert("请选择需要移动的选项")
                    }
                    //获取选中的选项，删除并追加给对方
                    else {
                        $('#select1 option:selected').appendTo('#select2');
                    }
                });

                //移到左边
                $('#remove').click(function () {
                    
                    //先判断是否有选中
                    if (!$("#select2 option").is(":selected")) {
                       // alert("请选择需要移动的选项")
                    }
                    else {
                        $('#select2 option:selected').appendTo('#select1');
                    }
                });

                //全部移到右边
                $('#add_all').click(function () {
                    //获取全部的选项,删除并追加给对方
                    $('#select1 option').appendTo('#select2');
                });

                //全部移到左边
                $('#remove_all').click(function () {
                    $('#select2 option').appendTo('#select1');
                });

                //双击选项
                $('#select1').dblclick(function () { //绑定双击事件
                    //获取全部的选项,删除并追加给对方
                    $("option:selected", this).appendTo('#select2'); //追加给对方
                });

                //双击选项
                $('#select2').dblclick(function () {
                    $("option:selected", this).appendTo('#select1');
                });
            }

        </script>
        <script>
            $(document).ready(function () {


                var comArray =${comArray};
                var noticeArray =${noticeArray};
                var userArray=${userArray};
                var str1 = "<option></option>";
                var str2 = "<option></option>";
                var str3 = "";
                var str4="";
                
                if (noticeArray.length > 0) {
                    for (var i = 0; i < noticeArray.length; i++) {
                        str1 = str1 + "<option value='" + noticeArray[i].n_name + "'>" + noticeArray[i].n_name + "</option>";
                    }
                }
                for(var i=0;i<userArray.length;i++){
                    str4=str4+"<option value='"+userArray[i].username+"'>"+userArray[i].username+"</option>";
                }   
                if (comArray.length > 0) {
                    for (var i = 0; i < comArray.length; i++) {

                        str2 = str2 + "<option value='" + comArray[i].c_stdname + "'>" + comArray[i].c_stdname + "</option>";
                        str3 = str3 + "<tr style='background:#eff7f9'>" +
                                "<td>" + comArray[i].c_plantid + "<div class='appbgs'></div></td>" +
                                "<td>" + comArray[i].c_stdname + "</td>" +
                                "<td>" + comArray[i].c_type + "</td>" +
                                "<td>" + comArray[i].c_stage + "</td>" +
                                "<td>" + comArray[i].c_state + "</td>" +
                                "<td></td>" +
                                "<td><a href='javascript:'  onClick=showchangepassword('"+comArray[i].c_stdname+"')><div class='appbgo'></div></a></td>" +
                                "<td onclick='show(" + i + ")'><div class='appbgf' ></div></td>" +
                                "<td><div class='appbgt'></div></td>" +
                                "</tr>" +
                                "<tr style='background:#f9f9f9' class='editrow'id='demo" + i + "'>" +
                                "<td colspan='9' ><div style='width:100%; height:310px;'>" +
                                "<div class='stepInfo'>" +
                                "<div class='appfd'>编写组成员：</div>" +
                                "<div class='appmiddle'>" +
                                "<div  class='audfdzot'>主编：</div>" +
                                "<div class='appceng'>" +
                                "<select name='chiefeditor' type='text' style='height:40px; width:40%; float:left;' id='chiefeditor'>"+str4+"</select>" +
                                "</div>" +
                                "<div  class='audfdzot'>编辑：</div>" +
                                "<div class='selectbox'>" +
                                "<div class='appselect-bar'>" +
                                "<select multiple='multiple' id='select1'>" +
                                str4 +
                                "</select>" +
                                "</div>" +
                                "<div class='appbtn-bar'>" +
                                "<p><span id='add'>" +
                                "<input type='button' class='btn' value='添加' title='移动选择项到右侧' onclick='tan()'/>" +
                                "</span></p>" +
                                "<p><span id='remove'>" +
                                "<input type='button' class='btn' value='删除' title='移动选择项到左侧'/>" +
                                "</span></p>" +
                                "</div>" +
                                "<div class='appselect-bar'>" +
                                "<select multiple='multiple' id='select2' name='multipleselect2'>" +
                                "</select>" +
                                "</div>" +
                                "<button type='button'  class='btn btn-primary' style='margin-top:10px;' onclick='save2("+i+")'>保存</button>" +
                                "</div>" +
                                "</div>" +
                                "</div>" +
                                "</div></td>" +
                                "</tr>";


                    }


                }


                $("#n_name").html(str1);
                $("#c_stdname").html(str2);
                $("#resultCompliation").html(str3);
            });

            $(document).ready(function () {

                $(".editrow").css("display", 'none');

            });
           
        </script>
        <script>
         function save2(obj){
                var comArray =${comArray};
                var stdname=comArray[obj].c_stdname;
                var chiefeditor=$("#chiefeditor").val();
                var multipleselect2=$("#select2").val();
                
                alert(stdname+","+chiefeditor+","+multipleselect2);
                 $.ajax({
                        type: "GET", // http请求方式
                        url: "draftStandardSavetwo.htm",
                        data: "chiefeditor=" + chiefeditor + "&c_stdname=" + stdname + "&editors=" + multipleselect2, // 发送给服务器段的数据，这里必须要填的,否则“/check.do?method=loginCheck”得不到数据
                        dataType: 'html', // 告诉JQuery返回的数据格式
                        beforeSend: function () {
//                                    alert("before");
                        },
                        success: function (data) {
                                
                        },
                        error: function (e) {

                        }
                    });
            }
         </script>   
        <script language="javascript">
            function addFile() {
                var fileDiv = document.all['fileDiv'];
                var strHtml = '<span><input type="text" style=" width:50%; height:40px;"></span>';
                fileDiv.innerHTML += strHtml;
            }

            function removeFile(obj) {
                obj.removeNode(true);
            }
        </script>
        <!-************************************************************ ->
        <!--<script type="text/javascript">
        $(document).ready(function(){
           var comArray=${comArray};
           
        //   if(comArray.length>0){
        //      for(var i=0;i<comArray.length;i++){
        //           $("#"+i+"").click(function(){
        //            $("#demo"+i+"").toggle();
        //            });
        //      }
        //   }
        //  $("#0").click(function(){
        //  $("#demo0").toggle();
        //  });
           
          $("#one").click(function(){
          $("#demoo").toggle();
          });
          $("#two").click(function(){
          $("#demot").toggle();
          });
          $("#three").click(function(){
          $("#demoh").toggle();
          });
          $("#four").click(function(){
          $("#demof").toggle();
          });
          $("#five").click(function(){
          $("#demov").toggle();
          });
          $("#six").click(function(){
          $("#demos").toggle();
          });
          $("#seven").click(function(){
          $("#demon").toggle();
          });
          $("#eight").click(function(){
          $("#demoe").toggle();
          });
          $("#nine").click(function(){
          $("#demoi").toggle();
          });
        });
        </script>-->
    </head>

    <body bgcolor="#ECECEC">
        <div class="mttop">
            <div class="apppic"></div>
            起草标准管理</div>
        <div class="sgwk">
            <script type="text/javascript">
                function showchangepassword(obj) {
                  
                    document.getElementById("stdnamevalue").value=obj;
                    var c = $("#change").clone(true);
                    $("body").append("<div class='modal-backdrop fade in' id='changepwdwrap'></div>");//创建的模版 就是全屏背景黑色半透明屏蔽后面元素的层 就是在body里面创建（append）一个div
                    $("#changepwdwrap").append(c);//这边是给div里面写入内容
                    
                   
                }
                function closes() {
                    $("#changepwdwrap").remove();
                }
                
                
                 function getFilename(obj){
//                                                alert(obj);
                            var path=obj.toString();
                            var name=path.substring(path.lastIndexOf("\\")+1,path.length);

                            document.getElementById("inputfilename").value=name;
                }
            </script>

            <div id="change" class="sgnk">
                <form id="form" enctype="multipart/form-data" method="post" action="draftStandardSaveone.htm">
                    <p id="close"> <font id="close" onclick="closes()">x</font> </p>
                   
                    <input name="stdname"  type="hidden" id="stdnamevalue"/>
                    <div class="audcq">
                        <div  class="audfdq">审核结果：</div>

                        <label class="audfdq">
                            <input type="radio" name="c_state" value="通过" id="RadioGroup1_0">
                            通过</label>
                        <label class="audfdq">
                            <input type="radio" name="c_state" value="未通过" id="RadioGroup1_1">
                            未通过</label>
                        <label class="audfdq">
                            <input type="radio" name="c_state" value="停止" id="RadioGroup1_2">
                            停止</label>

                    </div>
                    <div class="audcotq">
                        <div class="audfdq">审核意见：</div>
                        <div class="rowsdq">
                            <textarea name="s_checkidear" cols="" rows="" style="width:100%; height:100px;" id="s_checkidear"></textarea>
                        </div>
                    </div>
                    <div class="audcq">
                        <div class="mtotfq"> 


                            <a href="javascript:void(addFile());" class="file filebtno">
                                <input class="fbtn" type="file" name="file" onchange="getFilename(this.value)"/>
                                <img src="images/fujiano.png"  width="110" height="20" /></a>

                        </div>
                        <div id="fileDiv"></div><input name="" type="text" style="height:25px; width:50%; margin-left:10px;" id="inputfilename">
                    </div>
                    <div class="sgbtn">
                        <button type="submit" class="btn btn-default" style="width:90%; height:30px; background:#f36948; color:#fff; font-weight:700" onmousemove="sv()">提交</button>
                    </div>
                </form>
            </div>
            


        </div>
        <div class="mtotm">
            <div class="mtotf">
                <div class="mtotfj">项目名称：</div>
                <select name="n_name" type="text" style="height:25px; width:50%" id="n_name">

                </select>
            </div>
            <div class="mtoto">
                <div class="mtotfj">标准名称：</div>
                <select name="c_stdname" type="text" style="height:25px; width:50%" id="c_stdname">

                </select>
            </div>
            <div class="mtoto">
                <div class="mtotfj">项目状态：</div>
                <select name="c_state" style="height:25px;" id="c_state">
                    <option></option>
                    <option value='已审核'>已审核</option>
                    <option value='等待审核'>等待审核</option>
                    <option value='停用'>停用</option>

                </select>
            </div>
            <div class="mtoto">
                <div class="mtotfj">项目阶段：</div>
                <select name="c_stage" style="height:25px;" id="c_stage">
                    <option></option>
                    <option value='草案'>草案</option>
                    <option value='征求意见'>征求意见</option>
                    <option value='报批'>报批</option>
                </select>
            </div>
            <!-- ******************************************************************************************-->
           
            <div class="mtoto">
                
                <div class="mtotfj">开始时间：</div>
                <input type="text" id="s_applytime" style="height:25px; width:20%" onclick="J.calendar.get();"/>
               
                <input name="" type="button" style="background:#5c5c5c;border:none;color:#fff;width:50px;border-radius:0 5px 5px 0;"value="筛选" onclick="sendInfo()">
            </div>
            <table style="font-family: '微软雅黑';">
                <thead>
                    <tr>
                        <th>计划号</th>
                        <th>标准名称</th>
                        <th>类型</th>
                        <th>阶段</th>
                        <th>状态</th>
                        <th>进展</th>
                        <th>审核</th>
                        <th>修改</th>
                        <th>征求意见</th>
                    </tr>
                </thead>
                <tbody id="resultCompliation">
                    <!--      <tr style="background:#eff7f9">
                            <td><div class="appbgs"></div></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="javascript:"  onClick="showchangepassword()">
                              <div class="appbgo"></div>
                              </a></td>
                            <td id="one"><div class="appbgf" ></div></td>
                            <td><div class="appbgt"></div></td>
                          </tr>
                          <tr id="demoo" style="background:#f9f9f9" class="editrow">
                            <td colspan="9" ><div style="width:100%; height:310px;">
                                <input type="hidden" value=${detailorder.status} id="o_status" />
                                后台传到页面的数据
                                
                                <div class="stepInfo">
                                  <div class="appfd">编写组成员：</div>
                                  <div class="appmiddle">
                                    <div  class="audfdzot">主编：</div>
                                    <div class="appceng">
                                      <input name="" type="text" style="height:40px; width:40%; float:left;">
                                    </div>
                                    <div  class="audfdzot">编辑：</div>
                                    <div class="selectbox">
                                      <div class="appselect-bar">
                                        <select multiple="multiple" id="select1">
                                          <option value="超级管理员">超级管理员</option>
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
                                          <option value="资源管理员">资源管理员</option>
                                        </select>
                                      </div>
                                      <div class="appbtn-bar">
                                        <p><span id="add">
                                          <input type="button" class="btn" value="添加" title="移动选择项到右侧"/>
                                          </span></p>
                                        <p><span id="remove">
                                          <input type="button" class="btn" value="删除" title="移动选择项到左侧"/>
                                          </span></p>
                                      </div>
                                      <div class="appselect-bar">
                                        <select multiple="multiple" id="select2" >
                                        </select>
                                      </div>
                                      <button type="button"  class="btn btn-primary" style=" margin-top:10px;">保存</button>
                                    </div>
                                  </div>
                                </div>
                              </div></td>
                          </tr>-->
                    <!--      <tr style="background:#eff7f9">
                            <td><div class="appbgs"></div></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="javascript:"  onClick="showchangepassword()">
                              <div class="appbgo"></div>
                              </a></td>
                            <td id="two"><div class="appbgf" ></div></td>
                            <td><div class="appbgt"></div></td>
                          </tr>
                          <tr id="demot" style="background:#f9f9f9">
                            <td colspan="9"><div style="width:100%; height:310px;">
                                <input type="hidden" value=${detailorder.status } id="o_status" />
                                后台传到页面的数据
                                
                                <div class="stepInfo">
                                  <div class="appfd">编写组成员：</div>
                                  <div class="appmiddle">
                                    <div  class="audfdzot">主编：</div>
                                    <div class="appceng">
                                      <input name="" type="text" style="height:40px; width:40%; float:left;">
                                    </div>
                                    <div  class="audfdzot">编辑：</div>
                                    <div class="selectbox">
                                      <div class="appselect-bar">
                                        <select multiple="multiple" id="select1">
                                          <option value="超级管理员">超级管理员</option>
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
                                          <option value="资源管理员">资源管理员</option>
                                        </select>
                                      </div>
                                      <div class="appbtn-bar">
                                        <p><span id="add">
                                          <input type="button" class="btn" value="添加" title="移动选择项到右侧"/>
                                          </span></p>
                                        <p><span id="remove">
                                          <input type="button" class="btn" value="删除" title="移动选择项到左侧"/>
                                          </span></p>
                                      </div>
                                      <div class="appselect-bar">
                                        <select multiple="multiple" id="select2">
                                        </select>
                                      </div>
                                      <button type="button"  class="btn btn-primary" style=" margin-top:10px;">保存</button>
                                    </div>
                                  </div>
                                </div>
                              </div></td>
                          </tr>-->
                    <!--      <tr class="parent selected" id="row_03" style="background:#eff7f9">
                            <td><div class="appbgs"></div></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><a href="javascript:"  onClick="showchangepassword()">
                              <div class="appbgo"></div>
                              </a></td>
                            <td id="three"><div class="appbgf" ></div></td>
                            <td><div class="appbgt"></div></td>
                          </tr>-->









                </tbody>
            </table>

            <script>
                function sendInfo() {
                    var time = $("#s_applytime").val();
                    var noticename = $("#n_name").val();
                    var standardname = $("#c_stdname").val();
                    var stage = $("#c_stage").val();
                    var state = $("#c_state").val();
                    alert(time + "," + noticename + "," + standardname + "," + stage + "," + state);
                    $.ajax({
                        type: "GET", // http请求方式
                        url: "draftStandardScreen.htm",
                        data: "s_applytime=" + time + "&n_name=" + noticename + "&c_state=" + state + "&c_stdname=" + standardname + "&c_stage=" + stage, // 发送给服务器段的数据，这里必须要填的,否则“/check.do?method=loginCheck”得不到数据
                        dataType: 'html', // 告诉JQuery返回的数据格式
                        beforeSend: function () {
//                                    alert("before");
                        },
                        success: function (data) {

                            var objs = jQuery.parseJSON(data);

                            var str = "";

                            if (objs.length > 0) {
                                for (var i = 0; i < objs.length; i++) {
                                    str = str + "<tr style='background:#eff7f9'>" +
                                            "<td>" + objs[i].c_plantid + "<div class='appbgs'></div></td>" +
                                            "<td>" + objs[i].c_stdname + "</td>" +
                                            "<td>" + objs[i].c_type + "</td>" +
                                            "<td>" + objs[i].c_stage + "</td>" +
                                            "<td>" + objs[i].c_state + "</td>" +
                                            "<td></td>" +
                                            "<td><a href='javascript:'  onClick='showchangepassword()'><div class='appbgo'></div></a></td>" +
                                            "<td onclick='show(" + i + ")'><div class='appbgf' ></div></td>" +
                                            "<td><div class='appbgt'></div></td>" +
                                            "</tr>" +
                                            "<tr id='demo" + i + "' style='background:#f9f9f9' class='editrow'>" +
                                            "<td colspan='9' ><div style='width:100%; height:310px;'>" +
                                            "<div class='stepInfo'>" +
                                            "<div class='appfd'>编写组成员：</div>" +
                                            "<div class='appmiddle'>" +
                                            "<div  class='audfdzot'>主编：</div>" +
                                            "<div class='appceng'>" +
                                            "<input name='' type='text' style='height:40px; width:40%; float:left;'>" +
                                            "</div>" +
                                            "<div  class='audfdzot'>编辑：</div>" +
                                            "<div class='selectbox'>" +
                                            "<div class='appselect-bar'>" +
                                            "<select multiple='multiple' id='select1'>" +
                                            "<option value='超级管理员'>超级管理员</option><option value='普通管理员'>普通管理员</option>" +
                                            "</select>" +
                                            "</div>" +
                                            "<div class='appbtn-bar'>" +
                                            "<p><span id='add'>" +
                                            "<input type='button' class='btn' value='添加' title='移动选择项到右侧'/>" +
                                            "</span></p>" +
                                            "<p><span id='remove'>" +
                                            "<input type='button' class='btn' value='删除' title='移动选择项到左侧'/>" +
                                            "</span></p>" +
                                            "</div>" +
                                            "<div class='appselect-bar'>" +
                                            "<select multiple='multiple' id='select2'>" +
                                            "</select>" +
                                            "</div>" +
                                            "<button type='button'  class='btn btn-primary' style='margin-top:10px;'>保存</button>" +
                                            "</div>" +
                                            "</div>" +
                                            "</div>" +
                                            "</div></td>" +
                                            "</tr>";

                                }
                            }

                            $("#resultCompliation").html(str);
                            $(".editrow").css("display", 'none');

                        },
                        error: function (e) {

                        }
                    });
                }




            </script>
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
