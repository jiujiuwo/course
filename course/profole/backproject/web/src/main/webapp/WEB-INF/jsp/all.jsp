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
<html>
    <head>
        <script>
            function test(){
                var obj=parent.document.getElementById("index");
//                alert(obj.height);
            }
        </script>
    </head>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<frameset rows = "190,*,50" frameborder="no" border="0" framespacing="0">
<frame src = "top.htm" scrolling="no"  id="top" noresize  />
<frame src = "middle.htm" name = "myframe" scrolling="no" id="framemiddle" name="framemiddle"/>
<frame src="bottom.htm" scrolling="yes"/>
</frameset><noframes></noframes>

</html>