<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "permission");
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




			<div class="CourseContentHeader">角色管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>
			
			
			<div role="tabpanel" style="overflow: hidden;">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation"><a href="<c:url value="/permission/group/list.html"/>">组</a></li>						
					<li role="presentation"><a href="<c:url value="/permission/group-user/list.html"/>">组-用户</a></li>
					<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">角色</a></li>	
					<li role="presentation"><a href="<c:url value="/permission/group-role/list.html"/>" role="tab">组-角色</a></li>
					<li role="presentation"><a href="<c:url value="/permission/role-permission/list.html"/>" role="tab">角色-权限</a></li>
					<li role="presentation"><a href="<c:url value="/permission/resource/list.html"/>" role="tab">资源</a></li>
					<li role="presentation"><a href="<c:url value="/permission/operator/list.html"/>" role="tab">操作</a></li>
					<li role="presentation"><a href="<c:url value="/permission/permission-resource/list.html"/>">权限-资源</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane fade in active" id="home">



					</div>

				</div>

			</div>



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
							<th>角色</th>
							<th>描述</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="index" value="1"></c:set>
						<c:forEach var="dataitem" items="${pagedRole.result}">
							<tr>
								<th scope="row"><input type="checkbox" value="">
									${(pagedRole.currentPageNo-1) * pagedRole.pageSize +index}</th>
								<td>${dataitem.name}</td>
								<td>${dataitem.note}</td>
								<td><button type="button" class="btn btn-default btn-xs"
										onclick="onUpdate('${dataitem.id}','${dataitem.name}','${dataitem.note}')">修改...</button>
									<button type="button" class="btn btn-default btn-xs"
										onclick="onDelete('${dataitem.id}','${dataitem.name}')">删除</button></td>
							</tr>
							<c:set var="index" value="${index + 1}"></c:set>
						</c:forEach>
					</tbody>
				</table>

				<mathtop:PageBar pageUrl="/${pagedURI}/list.html"
					pageAttrKey="pagedRole" />

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
					<h4 class="modal-title" id="myModalLabel">添加角色</h4>
				</div>



				<form class="form-signin" action="<c:url value="/${pagedURI}/role/add.html"/>"
					method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">请输入新加角色名称</h4>
						<label for="inputname" class="sr-only">角色名称</label> <input
							type="text" id="inputname" class="form-control" name="name"
							placeholder="角色名称" required autofocus> <label
							for="inputnote" class="sr-only">角色描述</label> <input type="text"
							id="inputnote" class="form-control" name="note"
							placeholder="角色描述">

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
					<h4 class="modal-title" id="myModalLabel">修改角色</h4>
				</div>

				<form class="form-signin"
					action="<c:url value='/${pagedURI}/update.html'/>"
					method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">请修改角色属性</h4>
						<label for="inputname" class="sr-only">角色名称</label> <input
							type="text" id="inputid" class="form-control" name="id" value=""
							placeholder="id" style="display: none;"> <input
							type="text" id="inputname" class="form-control" name="name"
							value="" placeholder="角色名称" required autofocus> <label
							for="inputnote" class="sr-only">角色描述</label> <input type="text"
							id="inputnote" class="form-control" name="note" value=""
							placeholder="角色描述">

					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">修改</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>

				</form>
			</div>
		</div>
	</div>


	<%
		//删除对话框、错误信息对话框
	%>
	

	<%@ include file="../../shared/importJs.jsp"%>
<%@ include file="../../shared/sysLastInclude.jsp"%>

	<script>
		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "${pagedURI}/select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onUpdate(id, name, note) {
			var url = "location='<c:url value="/${pagedURI}/update-"/>" + id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputname').val(name);
			$('#updateModal').find('.modal-body #inputnote').val(note);
			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');
			
		}

		function onDelete(id, name) {
			var url = "location='<c:url value="/${pagedURI}/DELETE-"/>" + id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}
	</script>


</body>
</html>
