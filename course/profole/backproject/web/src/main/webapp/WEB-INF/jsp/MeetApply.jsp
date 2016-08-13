<%-- 
    Document   : MeetApply
    Created on : 2015-2-5, 9:56:59
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <title>无标题文档</title>
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css">
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
            <div class="mttp"><img src="image/man.png"></div>
            召开新会议</div>
        <div class="mtotm">
            <form action="meetapply.htm" method="post" ENCTYPE="multipart/form-data">
                <div class="mtotq">
                    <div class="mtotfj">会议名称：</div>
                    <input  name="m_name" id="m_name" type="text" style="height:40px; width:50%">
                </div>
                <div class="mtotw">
                    <div class="mtotfj">开始时间：</div>
                    <input type="text" name="m_beginning" id="m_beginning"  style="height:40px; width:20%" onclick="J.calendar.get({to: 'm_overtime,min'});"/>
                    <!--                    <select name="" style="width:10%; margin-left:2%;">
                                            <option >1</option>
                    
                                        </select>时-->
                </div>
                <div class="mtotw">
                    <div class="mtotfj">结束时间：</div>
                    <input type="text" name="m_overtime" id="m_overtime" style="height:40px; width:20%" onclick="J.calendar.get({to: 'm_beginning,max'});"/>
                    <!--                    <select name="" style="width:10%; margin-left:2%;">
                                            <option >1</option>
                                        </select>时-->
                </div>
                <div class="mtotw">
                    <div class="mtotfj">会议地点：</div>
                    <input name="m_address" id="m_address" type="text" style="height:40px; width:50%">
                </div>
                <div class="mtotw">
                    <div class="mtotfj">会议状态：</div>
                    <!--                    <select name="" style="height:40px;">
                                            <option value='0'>未总结</option>
                                            <option value='1'></option>
                                            <option value='2'></option>
                                            <option value='3'></option>
                                            <option value='4'></option>
                                            <option value='5'></option>
                                            <option value='6'></option>
                                        </select>-->
                    <select style="height:40px;" type="text" name="m_state" id="m_state">
                        <option>待召开</option>
                        <option>待总结</option>
                        <option>已总结</option>
                    </select>
                    <button type="button" class="btn btn-primary" >总结</button>
                </div>
                <div class="mtott">
                    <div class="mtotfj">会议内容：</div>
                    <input type="text" name="m_info" id="m_info" style="width:80%; height:200px;"/>
                </div>
                <div class="mtoto">
                    <div class="mtotfh"> 

                        <!--                        <form enctype="multipart/form-data">
                                                    <a href="javascript:void(addFile());" class="file filebtno">
                                                        <input class="fbtn" type="file"  />
                                                        <img src="image/fujiano.png"  width="110" height="20" /></a>
                                                </form>-->
                        <a href="javascript:void(addFile());" class="file filebtno">
                            <input name="file" type="file" class="fbtn"/>
                            <img src="image/fujiano.png"  width="110" height="20" /></a>
                        <!--<input type="submit"  style=" width:50px; height:30px; margin-bottom:20px;" class="btn btn-primary" value="保存" />-->
                    </div>
                    <!--                    <div id="fileDiv"></div>
                                        <input name="" type="text" style="height:40px; width:50%;">-->
                </div>

                <div class="mtotbtn">
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>
    </body>
</html>

