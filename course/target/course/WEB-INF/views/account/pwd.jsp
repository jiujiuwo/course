<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "pwd");
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>课程系统</title>
<base href="<%=basePath%>" />

<%@ include file="../../shared/importCss.jsp"%>
<%@ include file="../../shared/importdatetimepickercss.jsp"%>

<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet"
	type="text/css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<%@ include file="../../shared/pageHeader.jsp"%>
	

	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">




			<div class="CourseContentHeader">修改密码</div>

			<div class="CourseContentHeaderSeparatorLine"></div>

			


			<form class="form-horizontal" style="overflow: hidden;"
				action="<c:url value="/account/confirmpwd.html"/>" method="post">



				<div class="form-group">

					<label for="user_password" class="col-sm-1 control-label">旧密码</label>
					<div class="col-md-3">
						<input name="old_user_password" type="password"
							class="form-control placeholder-no-fix" value=""
							autocomplete="off" placeholder="旧密码" required/>
					</div>
				</div>
				
				<div class="form-group">

					<label for="user_password" class="col-sm-1 control-label">新密码</label>
					<div class="col-md-3">
						<input name="new_user_password" type="password"
							class="form-control placeholder-no-fix" value=""
							autocomplete="off" placeholder="新密码" required/>
					</div>
				</div>
				
				<div class="form-group">

					<label for="user_password" class="col-sm-1 control-label">确认新密码</label>
					<div class="col-md-3">
						<input name="confirm_user_password" type="password"
							class="form-control placeholder-no-fix" value=""
							autocomplete="off" placeholder="确认新密码" required/>
					</div>
				</div>



				<div class="modal-footer  col-md-4">
					<button type="submit" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default"
						onclick="window.history.back()">取消</button>
				</div>

			</form>
		</div>
	</div>








	

	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/importdatetimepickerjs.jsp"%>


	<%@ include file="../../shared/sysLastInclude.jsp"%>



</body>
</html>
