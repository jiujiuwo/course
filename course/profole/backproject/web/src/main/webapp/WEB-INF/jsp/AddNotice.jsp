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
<!--<html>
    <head>
        <meta charset="utf-8">
        <title>无标题文档</title>
        <link href="css/AddNotice.css" rel="stylesheet" type="text/css">
       
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
</html>-->
<html>
    <head>

        <meta charset="utf-8"/>
        <title>无标题文档</title>
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css"/>
        <link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/lhgcore.js"></script>
        <script type="text/javascript" src="js/lhgcalendar.js"></script>	
    </head>
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
    <body bgcolor="#ECECEC">
        <div class="mttop">
            <div class="notpic"></div>
            添加新立项公告</div>

        <div class="mtotm">
            <form action="addnotice.htm" method="post" commandName="org">
                <div class="mtotq">
                    <div class="notfj">公告名称：</div>
                    <input name="n_name" id="name" type="text" style="height:40px; width:50%">
                </div>
                <div class="mtotw">
                    <div class="notfj">公告类型：</div>
                    <!--    <select name="" style="height:40px;">
                          <option value='0'>国标</option>
                          <option value='1'>行标</option>
                          <option value='2'>企标</option>
                        </select>-->
                    <select name="n_type" id="n_type" style="height:40px;">
                        <option>国标</option>
                        <option>行标</option>
                        <option>企标</option>
                    </select>
                </div>
                <div class="mtotw">
                    <div class="notfj">接受申请时间：</div>
                    <input type="text" name="n_begintime" id="c10" style="height:40px; width:20%" onclick="J.calendar.get({to: 'c11,min'});"/>　至  　  <input type="text"  name="n_overtime" id="c11" style="height:40px; width:20%" onclick="J.calendar.get({to: 'c10,max'});"/>
                </div>
                <div class="mtotw">
                    <div class="notfj">发布时间：</div>
                    <input type="text" name="n_pubdate"  id="c1" style="height:40px; width:20%" onclick="J.calendar.get();"/>
                </div>

                <div class="mtott">
                    <div class="notfj">公告内容：</div>
                    <textarea name="n_info"  style="width:80%; height:200px;"></textarea>
                </div>

                <div class="mtoto">
                    <div class="mtotfh"> 
                        <a href="javascript:void(addFile());" class="file filebtno">
                            <input class="fbtn" name="n_path" type="file"  />
                            <img src="image/fujiano.png"  width="110" height="20" /></a>
                    </div>
<!--                    <div id="fileDiv"></div><input name="" type="text" style="height:40px; width:50%;">-->
                </div>

                <div class="mtott">
                    <div class="notfj">公告状态：</div>
                    <select name="n_state" id="n_state"><option>正常</option>
                        <option>停止</option><option>撤回</option></select>
                </div>

                <div class="noto">
                    <div class="mtotbtn">
                        <button type="submit" class="btn btn-primary">保存</button>
                    </div>
                </div>

            </form>
        </div>

    </body>
</html>
