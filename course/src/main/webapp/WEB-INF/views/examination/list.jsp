<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "school");
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




			<div class="CourseContentHeader">学院管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>
					<td><div class="btn-group" role="group" aria-label="...">
							<!-- Split button -->
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">选择</button>
								<button type="button"
									class="btn btn-default dropdown-toggle btn-sm"
									data-toggle="dropdown" aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle
										Dropdown</span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">全部选择</a></li>
									<li><a href="#">全部不选</a></li>
									<li class="divider"></li>
									<li><a href="#">反选</a></li>
								</ul>
							</div>
						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="btn-group" role="group" aria-label="...">
							<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#addModal">增加</button>

							<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#myModal">删除</button>


						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="input-group input-group-sm">
							<input type="text" class="form-control" id="SearchText"
								placeholder="Search for..." required> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button"
									onclick="onSearch()">搜索</button>
							</span>
						</div></td>
				</tr>
			</table>








			<div class="Course-Table">


				<table class="table table-hover table-bordered"
					style="margin-bottom: -10px;">
					<thead>
						<tr>
							<th>#</th>
							<th>学院名称</th>
							<th>学院描述</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="index" value="1"></c:set>
						<c:forEach var="school" items="${pagedSchool.result}">
							<tr>
								<th scope="row"><input type="checkbox" value="">
									${(pagedSchool.currentPageNo-1) * pagedSchool.pageSize +index}</th>
								<td>${school.name}</td>
								<td>${school.note}</td>
								<td><button type="button" class="btn btn-default btn-xs"
										onclick="onUpdate('${school.id}','${school.name}','${school.note}')">修改...</button>
									<button type="button" class="btn btn-default btn-xs"
										onclick="onDelete('${school.id}','${school.name}')">删除</button></td>
							</tr>
							<c:set var="index" value="${index + 1}"></c:set>
						</c:forEach>
					</tbody>
				</table>

				<mathtop:PageBar pageUrl="/school/list.html"
					pageAttrKey="pagedSchool" />

			</div>

		</div>
	</div>


	<!-- 添加对话框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加学院</h4>
				</div>

				<form class="form-signin" action="<c:url value="/school/add.html"/>"
					method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">请输入新加学院名称</h4>
						<label for="inputname" class="sr-only">学院名称</label> <input
							type="text" id="inputname" class="form-control" name="name"
							placeholder="学院名称" required autofocus> <label
							for="inputnote" class="sr-only">学院描述</label> <input type="text"
							id="inputnote" class="form-control" name="note"
							placeholder="学院描述">

					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" onclick="onAdd()">添加</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>
			</div>
		</div>
	</div>


	<!-- 修改对话框 -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改学院</h4>
				</div>

				<form class="form-signin"
					action="<c:url value="/school/update.html"/>" method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">请修改学院属性</h4>
						<label for="inputname" class="sr-only">学院名称</label> <input
							type="text" id="inputid" class="form-control" name="id" value=""
							placeholder="id" style="display: none;"> <input
							type="text" id="inputname" class="form-control" name="name"
							value="" placeholder="学院名称" required autofocus> <label
							for="inputnote" class="sr-only">学院描述</label> <input type="text"
							id="inputnote" class="form-control" name="note" value=""
							placeholder="学院描述">

					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>
			</div>
		</div>
	</div>



	

	<%@ include file="../../shared/importJs.jsp"%>
<%@ include file="../../shared/sysLastInclude.jsp"%>

	<script>
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		
	</script>


</body>
</html>
