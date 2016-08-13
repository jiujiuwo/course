<%-- 
    Document   : addCategoryAction
    Created on : 2014-10-31, 10:10:16
    Author     : liaojm
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>My JSP 'Altperator.jsp' starting page</title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script language="JavaScript" src="js/jquery-1.3.1.min.js" charset="utf-8"></script>
        <script language="JavaScript" src="js/jvalidate-1.0.0.js" charset="utf-8"></script>
        <script language="JavaScript" src="js/jmessagebox-1.0.0.js" charset="utf-8"></script>
        <link rel="stylesheet" type="text/css" href="messagebox/blue/messagebox.css" />

    </head>

    <script type="text/javascript">
        $(document).ready(function () {
            var data =${caretoryjson};
            var showData = data[0];
            var category = showData.category;
            $("#category").html(category);
        });
    </script>

    <body>
        <form action="addCategory.htm" method="post" commandName="org">
            <div >
                <center>
                    <table  border="0" cellspacing="0" cellpadding="0" width="100%" align="center">    	
                        <tr>
                            <td>大类别名称：</td>
                            <td><input type="text" path="category" name="category" id="category"  value=<%=request.getParameter("category") %> jvpattern="^\S" jverrortip="类别名称不能为空." jvtipid="spt_category"/>
                                <span id="spt_category" ></span></td>
                        </tr>

                        <tr>
                            <td>小类别1：</td>
                            <td><input type="text" path="onekey" name="onekey" id="one" value="类别英文或拼音缩写" jvpattern="^\S" jverrortip="请填写类别英文或者拼音缩写." jvtipid="spt_onekey"/>
                                <span id="spt_onekey" ></span></td>
                            <td><input type="text" path="one" name="one" id="one" value="类别名称" jvpattern="^\S" jverrortip="类别1不能为空，其他细分类别可以为空." jvtipid="spt_one"/>
                                <span id="spt_one"></span></td>
                        </tr>

                        <tr>
                            <td>小类别2：</td>
                             <td><input type="text" path="twokey"  name="twokey" id="twokey" /></td>
                            <td><input type="text" path="two"   name="two" id="two" /></td>
                        </tr>

                        <tr>
                            <td>小类别3：</td>
                            <td><input type="text" path="threekey" name="threekey" id="threekey" /></td>
                            <td><input type="text" path="three" name="three" id="three" /></td>
                        </tr>

                        <tr>
                            <td>小类别4：</td>
                            <td><input type="text" path="fourkey" name="fourkey" id="fourkey" /></td>
                            <td><input type="text" path="four" name="four" id="four" /></td>
                        </tr>

                        <tr>
                            <td>小类别5：</td>
                            <td><input type="text" path="fivekey" name="fivekey" id="fivekey" /></td>
                            <td><input type="text" path="five" name="five" id="five" /></td>
                        </tr>

                        <div style="text-align:center; ">
                            <tr>
                                <td colspan="2"><input type="submit" value="确定" /> 
                                    <input type="reset" value="重置" /></td>
                            </tr>
                        </div>

                    </table>
                </center>
            </div>
        </form>

        <div id="foot" align="center">
            <font size="2px">2014 北京新华社 京ICP证000000号</font>

        </div>

    </body>
    <script language="JavaScript">

        $.jMessageBox.settings.yesButtonText = '确定';
        $('form').jValidate({
            blurvalidate: true
        });
    </script>
</html>

