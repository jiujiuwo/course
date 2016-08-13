<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
     body{margin-top:20px; border: 1px solid #999}
     #head {background-color: #EAEAEA;height:80px;margin:auto 15px ;}
     #content {height:390px;margin-left:36px;}
     #line{margin-top:-10px;margin-left:15px;margin-right:15px}
     #condition{height:90px}
     .results-con{padding:20px 10px;height:350px;border:1px solid #999;border-radius:5px;margin-right:36px}
     .results-con span{width:98%;display:block;background-color: #EAEAEA;}
      #foot{ height: 20px; margin-top: 300px;}
      table{height:40px;border-color:#C0C0C0;  }
      td{text-align:center;}
     .show span {background:none}
     
</style>
</head>

	<script src="js/jQuery.js" type="text/javascript"></script>
      <script type="text/javascript">
  
    </script>
<body>

      <div id="line" ><hr size="3" color="#080808"></div>
      
      <div id="content">

            
              <br/>
              <div id="results">
                    <div class="results-con">
                          <span><b><font size="4.9px">列表：</font></b></span><br/>
                          <table  border="1" cellspacing="0" cellpadding="1" align="center" id="table" width="98%">
                       		<tr>选择标准</tr>
                       		<tr>
	                          <c:forEach items="${standerList}" var="bean" varStatus="date">
				       			  <td width='10%'><a href="showItem.htm?starderNum=${bean}">${bean}</a></td>
			        		  </c:forEach>
			        		</tr>
                          </table>
                    </div>
              
		                            
              </div>
      </div>
      
	 <div id="foot" align="center">
	          <font size="2px">© 版权所有2014 北京新华社 </font>
     </div>
</body>
</html>