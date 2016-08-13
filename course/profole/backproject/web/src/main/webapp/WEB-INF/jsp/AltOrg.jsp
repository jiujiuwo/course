<%@page import="com.lifeful.resource.User"%>
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
        <link href="css/backstage_style.css" rel="stylesheet" type="text/css">
            <link href="css/backstage_bootstrap.min.css" rel="stylesheet" type="text/css" />
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

    <!--    <body>
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
                            <div id="checklogin"></div>
                            <div id="loginout"></div>
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
                               <div class="adneid">地址：</div>
                                <div class="adneio"><input type="text" path="address" name="address" id="address" style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="地址不能为空." jvtipid="spt_address"/>
                                    <span id="spt_address" ></span></div>
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
    -->
    <body bgcolor="#ececec">
        <div class="mttop">
            <div class="mepic"></div>
            机构管理</div>
        <div class="mtotm">
            <div class="mtjmiddle">
                <form action="altorg.htm" method="post" commandName="org">
                    <div class="mtj"><div class="perleft">机构id：</div><div class="perright"><input name="dealerid" id="dealerid"type="text" style="width:100%; height:30px;" Readonly></div></div>
                    <div class="mtj"><div class="perleft">机构名：</div><div class="perright"><input name="username" id="username" type="text" style="width:100%; height:30px;"style="margin:7px 5px; width:130px;" jvpattern="^\S" jverrortip="机构名称不能为空." jvtipid="spt_username"/>
                            <span id="spt_username"></span>
                        </div>
                    </div> 

                    <div class="mtj"><div class="perleft">机构代表：</div><div class="perright"><select name="owner"  id="owner"style="width:100%; height:30px;"jvpattern="^\S" jverrortip="机构代表不能为空." jvtipid="spt_owner">

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
                    </div>
                    <div class="mtj"><div class="perleft">机构联系人：</div><div class="perright"><select name="contact" id="contact" style="width:100%; height:30px;" jvpattern="^\S" jverrortip="机构联系人不能为空." jvtipid="spt_contact">

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
                            </select> <span id="spt_contact"></span></div></div>
                    <div class="mtj"><div class="perleft">地址：</div><div class="perright"><input name="address" id="address" type="text" style="width:100%; height:30px;"></div></div>
                    <div class="perbtn">
                        <button  type="submit" class="btn btn-danger">修改</button>
                        <button  type="submit" class="btn btn-danger">重置</button>
                    </div>

                </form>
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

