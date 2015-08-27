<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	//设置左侧浏览状态
		session.setAttribute("nav", "home");
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">




<base href="<%=basePath%>" />
<title>课程系统</title>


<link href="<c:url value='/css/pages/login.css'/>" rel="stylesheet"
	type="text/css" />


<meta content="text/html; charset=UTF-8" http-equiv="content-type" />





</head>
<body class="login">

	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form:form modelAttribute="user" class="login-form" method="POST">
			<h3 class="form-title">账户登录</h3>

			<div class="form-group">
				<label class="form-label">学号或工号</label>
				<div class="input-icon">
					<i class="icon-user"></i>
					<form:input path="user_name"
						class="form-control placeholder-no-fix" autocomplete="off"
						placeholder="学号或工号" />
					<br />
					<form:errors path="user_name" class="field-has-error"></form:errors>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="input-icon">
					<i class="icon-lock"></i>
					<form:password path="user_password"
						class="form-control placeholder-no-fix" autocomplete="off"
						placeholder="密码" />
					<br />
					<form:errors path="user_password" class="field-has-error"></form:errors>
				</div>
			</div>

			<c:if test="${!empty errorMsg}">
				<div class="form-group">
					<div style="color: red">${errorMsg}</div>
				</div>
			</c:if>
			<div class="form-actions">

				<button type="submit" class="btn btn-info pull-right">登录</button>
			</div>
		</form:form>
		<!-- END LOGIN FORM -->
	</div>


</body>

</html>
