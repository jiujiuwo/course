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
<body class="home" onLoad="ShowErrMsg()">

	<%@ include file="../../shared/pageHeader.jsp"%>


	<div class="DocumentPage">
		<div class="DocumentPageLeftArea ">
			<%
				//包含左侧工具菜单
			%>
			<%@ include file="../CourseNav.jsp"%>
		</div>

		<div class="DocumentPageRightArea">




			<div class="CourseContentHeader">权限管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>






			<div role="tabpanel" style="overflow: hidden;">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation"><a href="<c:url value="/permission/group/list.html"/>">组</a></li>						
					<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">组-用户</a></li>
					<li role="presentation"><a href="<c:url value="/permission/role/list.html"/>">角色</a></li>
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


			<table style="margin-top: 10px;">


				<tr>

					<td>
						<div class="input-group input-group-sm">
							<select id="groupSelectControl" class="form-control"
								onchange="OnSelectControlChange(this)">
								<c:forEach var="d" items="${pagedGroup.result}">
									<option value="${d.id}">${d.name}</option>
								</c:forEach>
							</select>


						</div>
					</td>

					<td style="width: 10px;"></td>

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



			<c:choose>
				<c:when test="${pagedGroupPersonInfo.totalCount >0}">

					<div class="Course-Table">


						<table class="table table-hover table-bordered"
							style="margin-bottom: -10px;">
							<thead>
								<tr>
									<th>#</th>
									<th>工号或学号</th>
									<th>姓名</th>
									<th>部门</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="index" value="1"></c:set>
								<c:forEach var="dataitem" items="${pagedGroupPersonInfo.result}">
									<tr>
										<th scope="row"><input type="checkbox" value="">
											${(pagedGroupPersonInfo.currentPageNo-1) * pagedGroupPersonInfo.pageSize +index}</th>

										<td><c:if test="${dataitem.teacher!=null}">
											${dataitem.teacher.teacher_num}(工号)
											</c:if> <c:if test="${dataitem.student!=null}">
											${dataitem.student.student_num}(学号)
											</c:if></td>
										<td>${dataitem.userbasicinfo.user_basic_info_name}</td>
										<td>${dataitem.school.name}-${dataitem.department.name}</td>
										<td><button type="button" class="btn btn-default btn-xs"
												onclick="onUpdate('${dataitem.id}')">修改...</button>
											<button type="button" class="btn btn-default btn-xs"
												onclick="onDelete('${dataitem.id}')">删除</button></td>
									</tr>
									<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach>
							</tbody>
						</table>

<c:if
					test="${not empty selectedGroupID}">
					<mathtop:PageBar
						pageUrl="/permission/group-user.html?groupId=${selectedGroupID}"
						pageAttrKey="pagedGroupPersonInfo" />
				</c:if>
				
						

					</div>

				</c:when>

				<c:otherwise>
					<%
						//没有找到记录
					%>
					<div class="alert alert-warning alert-dismissible fade in"
						role="alert" style="margin: 10px;">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h3>没有找到记录,请选择下列操作之一：</h3>

						<div class="list-group">

							<a href="<c:url value="/teacher/add.html"/>"
								class="list-group-item">添加教师</a> <a
								href="<c:url value="/student/add.html"/>"
								class="list-group-item">添加单个学生</a>  <a
								href="<c:url value="/student/addfromexcel.html"/>"
								class="list-group-item">添加多个学生</a> <a
								href="<c:url value="/permission/"/>" class="list-group-item">为组添加用户</a>

						</div>

					</div>
				</c:otherwise>
			</c:choose>



		</div>
	</div>





	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>

	<script>
		var ctrl = document.getElementById("groupSelectControl");
		var groupid = "${selectedGroupID}";
		var index = 0;
		
		

		for (var i = 0; i < ctrl.options.length; i++)
			if (ctrl.options[i].value == (groupid))
				index = i;
		ctrl.selectedIndex = index;
	</script>

	<script>
		function OnSelectControlChange(selectctrl) {

			var url = "<c:url value="/permission/"/>"

			url = url + "group-user.html?groupId=" + selectctrl.value;
			window.location.href = url;

		}

		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onUpdate(id, name, note) {
			var url = "location='<c:url value="/update-"/>" + id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);
			$('#updateModal').find('.modal-body #inputname').val(name);
			$('#updateModal').find('.modal-body #inputnote').val(note);
			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');

		}

		function onDelete(id, name) {
			var url = "location='<c:url value="/DELETE-"/>" + id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			$('#deleteModal').modal('show');

		}
	</script>




	<c:if test="${!empty errorMsg}">
		<script>
			function ShowErrMsg() {
				ShowInfoMsg("${errorMsg}");

			}
		</script>

	</c:if>

	<c:set var="errorMsg" value="null" />

</body>
</html>
