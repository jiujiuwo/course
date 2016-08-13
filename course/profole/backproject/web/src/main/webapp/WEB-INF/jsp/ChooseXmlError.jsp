<%-- 
    Document   : ChooseXmlError
    Created on : 2014-10-30, 10:41:18
    Author     : liaojm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <script>
        function choosexml() {
            location.href = "ChoosePath.jsp";
        }
    </script>
     <script>
        function importxml() {
            location.href = "fileUpload.htm";
        }
    </script>
    <body>
        <div align="center">
            <td>正在解压缩文件，请等待</td>
            <td><input type=button name=B1 value="下一步" onClick="importxml();"></td>
        </div>
        <form action="#" method="#" commandName="#">
            <div id="content">
                <center>

                    <table  border="1" cellspacing="0" cellpadding="1" align="center" id="table">
                        ${requestScope['upload.message'] }
                        <a href="ChoosePath.jsp">重新选取导入文件</a>
                        
                    </table>
                </center>
            </div>
        </form>
    </body>
</html>
