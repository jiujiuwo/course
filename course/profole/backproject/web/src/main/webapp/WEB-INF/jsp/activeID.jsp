<%-- 
    Document   : activeID
    Created on : 2014-4-9, 14:32:01
    Author     : chenyanliang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>激活协同编辑系统帐号</title>
    </head>
    <body>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function() {
                var state = '${activeresult}';
                if (state === "success") {
                    alert("邮箱验证成功！即将为您跳转到登录页面。");
                    location.href = "login.htm";
                } else if (state === "fail") {
                    alert("邮箱验证失败，可能是您已经激活或激活信息已失效。即将为您跳转到登录页面。");
                    location.href = "login.htm";
                }
            });
        </script>
    </body>

</html>
