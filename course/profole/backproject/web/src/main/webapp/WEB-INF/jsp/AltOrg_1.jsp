<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <link href="css/homePage.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="js/jquery-1.3.1.min.js" charset="utf-8"></script>
        <script language="JavaScript" src="js/jvalidate-1.0.0.js" charset="utf-8"></script>
        <script language="JavaScript" src="js/jmessagebox-1.0.0.js" charset="utf-8"></script>
    </head>
    <script>
        function getId() {
         var id =<%=request.getParameter("oid")%>;
            return id;
        }
    </script>


    <script type="text/javascript">
        $(document).ready(function () {
            var data = ${
        jsonarray
        };

            var showData = data[0];
            var dealerid = showData.dealerid;
            var username = showData.username;
            var aliasname = showData.aliasname;
            var owner = showData.owner;
            var parent = showData.parent;
            var isorg = showData.isorg;
            var tel = showData.tel;
            var address = showData.address;
            var postcode = showData.postcode;
            var country = showData.country;
            var city = showData.city;
            var other = showData.other;

            $("#dealerid").val(dealerid);
            $("#username").val(username);
            $("#aliasname").val(aliasname);
            $("#owner").val(owner);
            $("#parent").val(parent);
            $("#isorg").val(isorg);
            $("#tel").val(tel);
            $("#address").val(address);
            $("#postcode").val(postcode);
            $("#country").val(country);
            $("#city").val(city);
            $("#other").val(other);

        });
    </script>

    <script>
        function updateto() {
            location.href = "showoneorg.htm?dealerid=" + getId();
        }
    </script>
    <body>
        <div class="main">
            <div class="top">
                <div class="topleft">
                    <ul style="list-style-type:none">
                        <a href="homePage.htm"><li>首页</li></a>
                        <a href="managerPage.htm"><li>管理员首页</li></a>
                    </ul>
                </div>
                <div class="topright">
                    <ul style="list-style-type:none">
                        <a href=""><li>admin</li></a>
                        <a href="homePage.htm"><li>退出</li></a>	
                    </ul>
                </div>
            </div>
            <div class="orgmiddle">
                <form action="altorg.htm" method="post" commandName="org">
                    <div class="orgbiankuang">
                        <div class="orgzhongc">
                            <div class="adneid">机构id：</div>
                            <div class="adneio"><input  id="dealerid" name="dealerid" type="text" style="margin:7px 5px; width:130px;" Readonly/></div>
                            <div class="adneid">机构名：</div>
                            <div class="adneit"><input type="text" path="username" name="username" id="username" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="商户名称不能为空." jvtipid="spt_username"/>
                                <span id="spt_username"></span>
                            </div>
<!--                            <div class="adneid">机构别名：</div>
                            <div class="adneio"><input type="text" path="aliasname" name="aliasname" id="aliasname" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="商户名称不能为空." jvtipid="spt_aliasname"/>
                                <span id="spt_aliasname"></span></div>-->
                            <div class="adneid">机构户主：</div>
                            <div class="adneit"><select type="text" path="owner" name="owner" id="owner" style="margin:7px 5px; width:100px;"
                                                        jvpattern="^\S" jverrortip="机构名称不能为空." jvtipid="spt_owner">

                                    <script type="text/javascript">
                                        $(document).ready(function () {
                                            var data = ${arrayuserid};
                                            for (var i = 0; i < data.length; i++) {
                                                var showData = data[i];
                                                var owner = showData.owner;
                                                var name = showData.name;
                                                $("#owner").append("<option value='" + owner + "'>" + name + "</option>");
                                            }
                                        });
                                    </script>
                                </select> <span id="spt_owner"></span></div>
                                
                                <div class="adneid">机构联系人：</div>
                            <div class="adneit"><select type="text" path="contact" name="contact" id="contact" style="margin:7px 5px; width:100px;"
                                                        jvpattern="^\S" jverrortip="机构联系人不能为空." jvtipid="spt_contact">

                                    <script type="text/javascript">
                                        $(document).ready(function () {
                                            var data = ${arrayuserid};
                                            for (var i = 0; i < data.length; i++) {
                                                var showData = data[i];
                                                var owner = showData.owner;
                                                var name = showData.name;
                                                $("#contact").append("<option value='" + owner + "'>" + name + "</option>");
                                            }
                                        });
                                    </script>
                                </select> <span id="spt_contact"></span></div>
<!--                            <div class="adneid">商户上级：</div>
                            <div class="adneio"><select type="text" path="parent" name="parent" id="parent" style="margin:7px 5px; width:100px;"
                                                        jvpattern="^\S" jverrortip="机构名称不能为空." jvtipid="spt_parent">

                                    <script type="text/javascript">
                                        $(document).ready(function () {
                                            var data = ${arraydealerid};
                                            for (var i = 0; i < data.length; i++) {
                                                var showData = data[i];
                                                var parent = showData.parent;
                                                var name = showData.name;
                                                $("#parent").append("<option value='" + parent + "'>" + name + "</option>");
                                            }
                                        });
                                    </script>
                                </select> <span id="spt_parent" ></span></div>-->
<!--                            <div class="adneid">是否法人机构：</div>
                            <div class="adneit"><select type="text"  path="isorg" name="isorg" id="isorg" style="margin:7px 5px; width:60px;" jvpattern="^\S" jverrortip="请选择是否法人(true:是法人机构，false:非法人机构)" jvtipid="spt_isorg"/>
                                <option value="1">true</option>
                                <option value="2">false</option>
                                </select>
                                <span id="spt_isorg" ></span></div>-->
                           <div class="adneid">地址：</div>
                            <div class="adneio"><input type="text" path="address" name="address" id="address" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="地址不能为空." jvtipid="spt_address"/>
                                <span id="spt_address" ></span></div>
                            <!-- <div class="adneid">所在城市：</div>
                            <div class="adneit"><input type="text" path="city" name="city" id="city" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="城市不能为空." jvtipid="spt_city"/>
                                <span id="spt_city" ></span></div>
                            <div class="adneid">所在国家：</div>
                            <div class="adneio"><input type="text" path="country" name="country" id="country" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="所在国家不能为空." jvtipid="spt_country"/>
                                <span id="spt_country" ></span></div>
                            <div class="adneid">邮编：</div>
                            <div class="adneit"><input type="text" path="postcode" name="postcode" id="postcode" style="margin:7px 5px; width:130px;" jvpattern="^[1-9]\d{5}$" jverrortip="填写正确邮编." jvtipid="spt_postcode"/>
                                <span id="spt_postcode" ></span></div>
                            <div class="adneid">联系方式：</div>
                            <div class="adneip"><input type="text" path="tel" name="tel" id="tel" style="margin:7px 5px; width:130px; float:left" jvpattern="^1[3|4|5|8][0-9]\d{4,8}$" jverrortip="填写正确联系方式（移动电话号码）." jvtipid="spt_tel"/>
                                <span id="spt_tel" ></span></div>
                            <div class="adneit"></div>
                            <div class="adneid">其他信息：</div>
                            <div class="adneit"><input type="text" path="other" name="other" id="other" style="margin:7px 5px; width:130px;" jvpattern="^[\w\u4E00-\u9FA5\uF900-\uFA2D]*$" jverrortip="其他信息字数介于7~300个字数之间." value="其他信息默认为空" jvtipid="spt_other"/>
                                <span id="spt_other" ></span></div>-->
                        </div>
                        <div class="altbutton">
                            <input type="submit" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px; float:left;" value="修改" />
                            <input type="reset" name="button" id="button" style="width:70px; height:20px; border:1px #666 solid; border-radius:10px; float:right;" value="重置" />
                        </div>
                    </div>
                </form>
            </div>
            <div class="bottom">
                <div class="bottomcss">2014 科迪智标 京ICP证000000号</div>
            </div>
        </div>
    </body>
    <script language="JavaScript">

        $.jMessageBox.settings.yesButtonText = '确定';
        $('form').jValidate({
            blurvalidate: true
        });
    </script>
</html>

