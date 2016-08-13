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
        function addto() {
            location.href = "select_org.action";
        }
    </script>

    <body bgcolor="#ececec">
        <div class="mttop">
            <div class="mepic"></div>
            机构管理</div>
        <div class="mtotm">
            <div class="mtjmiddle">
                <form action="altorg.htm" method="post" commandName="org">
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

