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
            var str = "<div float:center><tr><td><h4>请选择标准类别</h4></td></tr></div>";
            for (var i = 0; i < data.length; i++) {
                var showData = data[i];
                var category = showData.category;
                str+= "<input type='text' name='category' value='" + category + "'/>"+
                       "<td><a href='addCategoryAction.htm?category=" + category + "'>在"+category+"中增加类别</a><br></td>";
                for (var key in data[i]) {
                    if (key != "category") {
                        str += "<td><input type='text' name='category' value='" + showData[key] + "'/></td><br>";
                    }
                }
            }
            str+="<td><a href='addCategoryAction.htm?category=请添加大类别'>增加一个大的类别</a><br></td>";
            $("#category").html(str);
        });
    </script>
            
    <body>
    <form action="addCategory.htm" method="post" commandName="org">
        <div id="content">
            <center>
                <table  border="0" cellspacing="0" cellpadding="0" width="100%" align="center" id="category">    	
<!--                    <div id="caretory">
                </div>-->
<!--                    <tr>
                        <td>大类别名称：</td>
                        <td><input type="text" path="category" name="category" id="category" jvpattern="^\S" jverrortip="类别名称不能为空." jvtipid="spt_category"/>
                            <span id="spt_category" class="normaltips" jverrorclass="errortips" jvcorrectclass="correcttips"></span></td>
                    </tr>

                    <tr>
                        <td>小类别1：</td>
                        <td><input type="text" path="one" name="one" id="one" jvpattern="^\S" jverrortip="类别1不能为空，其他细分类别可以为空." jvtipid="spt_one"/>
                            <span id="spt_one" class="normaltips" jverrorclass="errortips" jvcorrectclass="correcttips"></span></td>
                    </tr>

                    <tr>
                        <td>小类别2：</td>
                        <td><input type="text" path="two" name="two" id="two" />
                    </tr>

                    <tr>
                        <td>小类别3：</td>
                        <td><input type="text" path="three" name="three" id="three" />
                    </tr>
                    
                    <tr>
                        <td>小类别4：</td>
                        <td><input type="text" path="four" name="four" id="four" />
                    </tr>
                    
                    <tr>
                        <td>小类别5：</td>
                        <td><input type="text" path="five" name="five" id="five" />
                    </tr>

                    <div style="text-align:center; ">
                        <tr>
                            <td colspan="2"><input type="submit" value="确定" /> 
                                <input type="reset" value="重置" /></td>
                        </tr>
                    </div>-->

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
