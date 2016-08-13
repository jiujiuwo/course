<%-- 
    Document   : ShowTerm
    Created on : 2014-10-9, 13:57:46
    Author     : liaojm
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TermTagging.jsp</title>
        <style type="text/css">
            body{margin: 0px; }
            #head {background-color: #EAEAEA;height:80px;margin:auto 15px ;}
            #content {height:390px;margin-left:36px;}
            #line{margin-top:-10px;margin-left:15px;margin-right:15px}
            #condition{height:90px}
            .results-con{padding:20px 10px;height:350px;border:1px solid #999;border-radius:5px;margin-right:36px}
            .results-con span{width:98%;display:block;background-color: #EAEAEA;}
            #foot{ height: 20px; margin-top: 150px;}
            table{height:50px;border-color:#C0C0C0; width: 98%; }
            td{text-align:center;}
            .show span {background:none}

        </style>
    </head>

    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var data =${jsonarray};
            var name=data[0].name;
            var str = "<div float:center><tr><td colspan='2'></td></tr></div>" +
                    "<div float:center><tr><td><h4>标准 "+name+"  中的术语列表：</h4></td></tr></div>";
            for (var i = 0; i < data.length; i++) {
                var showData = data[i];
                var term = showData.term;
                str = str + "<td><input type='text' name='tag' value='" + term +"'/><br></td>";
            }
            $("#open").html(str);
        });
    </script>
    <body>

        <form action="TermCategory.htm" method="post"/>
                    <table  border="0" cellspacing="0" cellpadding="1" align="center" id="table">
                        <div id="open" >
                        </div>
                    </td></tr>
                    </table>
            </form>
    </body>
</html>