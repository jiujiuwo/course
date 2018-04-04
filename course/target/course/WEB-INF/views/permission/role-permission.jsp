<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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

<%@ include file="../../shared/importswitchcss.jsp"%>

<link href="<c:url value='/css/pages/index.css'/>" rel="stylesheet" type="text/css" />

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




			<div class="CourseContentHeader">权限管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>






			<div role="tabpanel" style="overflow: hidden;">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation"><a href="<c:url value="/permission/group/list.html"/>">组</a></li>
					<li role="presentation"><a href="<c:url value="/permission/group-user/list.html"/>">组-用户</a></li>
					<li role="presentation"><a href="<c:url value="/permission/role/list.html"/>">角色</a></li>
					<li role="presentation"><a href="<c:url value="/permission/group-role/list.html"/>"
							role="tab">组-角色</a></li>
					<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
							data-toggle="tab">角色-权限</a></li>
					<li role="presentation"><a href="<c:url value="/permission/resource/list.html"/>"
							role="tab">资源</a></li>
					<li role="presentation"><a href="<c:url value="/permission/operator/list.html"/>"
							role="tab">操作</a></li>
					<li role="presentation"><a
							href="<c:url value="/permission/permission-resource/list.html"/>">权限-资源</a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane fade in active" id="home"></div>

				</div>

			</div>


			<table style="margin-top: 10px;">


				<tr>

					<td>
						<div class="input-group input-group-sm">
							<select id="roleSelectControl" class="form-control" onchange="OnSelectControlChange(this)">
								<c:forEach var="d" items="${pagedRole.result}">
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
								<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown"
									aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
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
							<button type="button" class="btn btn-default btn-sm" data-toggle="modal"
								data-target="#addModal">增加</button>

							<button type="button" class="btn btn-default btn-sm" data-toggle="modal"
								data-target="#myModal">删除</button>


						</div></td>
					<td style="width: 10px;"></td>
					<td><div class="input-group input-group-sm">
							<input type="text" class="form-control" id="SearchText" placeholder="Search for..." required>
							<span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="onSearch()">搜索</button>
							</span>
						</div></td>
						<td style="width: 10px;"></td>
					<td><div class="btn-group" role="group" aria-label="...">
							<!-- Split button -->
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">每页大小</button>
								<button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown"
									aria-expanded="false">
									<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=10">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==10}">
															<i class="icon-ok"></i>
														</c:if>
														默认(10)
													</a></li>
												<li class="divider"></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=20">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==20}">
															<i class="icon-ok"></i>
														</c:if>
														20
													</a></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=30">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==30}">
															<i class="icon-ok"></i>
														</c:if>
														30
													</a></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=40">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==40}">
															<i class="icon-ok"></i>
														</c:if>
														40
													</a></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=50">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==50}">
															<i class="icon-ok"></i>
														</c:if>
														50
													</a></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=60">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==60}">
															<i class="icon-ok"></i>
														</c:if>
														60
													</a></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=70">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==70}">
															<i class="icon-ok"></i>
														</c:if>
														70
													</a></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=80">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==80}">
															<i class="icon-ok"></i>
														</c:if>
														80
													</a></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=90">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==90}">
															<i class="icon-ok"></i>
														</c:if>
														90
													</a></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=100">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize==100}">
															<i class="icon-ok"></i>
														</c:if>
														100
													</a></li>
												<li class="divider"></li>
												<li><a
														href="teachingclass/student-${selectedCourseTeachingClassID}.html?pageSize=1000">
														<c:if test="${sessionScope.USER_CONTEXT.pageSize>100}">
															<i class="icon-ok"></i>
														</c:if>
														不分页
													</a></li>
								</ul>
							</div>
						</div></td>
				</tr>
			</table>


			<form class="form-horizontal" style="overflow: hidden;"
				action="<c:url value="/permission/role-permission/add.html"/>" method="post">


				<div class="Course-Table">

					<input id='selectedRoleID' name='roleId' value='${selectedRoleID}' type='hidden' />


					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th>#</th>
								<th>名称</th>
								<th>描述</th>
								<th>资源</th>
								<th>操作</th>
								<th>是否选中</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="index" value="1"></c:set>
							<c:forEach var="dataitem" items="${pagedPermissionViewData.result}">
								<tr>
									<th scope="row">${(pagedPermissionViewData.currentPageNo-1) * pagedPermissionViewData.pageSize +index}</th>


									<td>${dataitem.permission.name}</td>
									<td>${dataitem.permission.note}</td>
									<td>${dataitem.resource.uri}:${dataitem.resource.uriElement}</td>
									<td>${dataitem.permissionoperator.name}</td>
									<td><input id="permissionswitch${index}" name="permission"
											value="${dataitem.permission.id}" type="checkbox"
											<c:forEach var="rolepermission"
											items="${pagedRolePermissionViewData.result}">
											<c:if test="${rolepermission.permission.id == dataitem.permission.id}">
											checked
										</c:if>					
									</c:forEach>
											data-size="mini" data-on-text="是" data-off-text="否"></td>

								</tr>
								<c:set var="index" value="${index + 1}"></c:set>
							</c:forEach>
						</tbody>
					</table>


				</div>





				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" onclick="window.history.back()">取消</button>
				</div>

			</form>


		</div>
	</div>







	<%@ include file="../../shared/importJs.jsp"%>
	<%@ include file="../../shared/sysLastInclude.jsp"%>
	<%@ include file="../../shared/importswitchjs.jsp"%>


	<script>
		var ctrl = document.getElementById("roleSelectControl");
		var groupid = "${selectedRoleID}";
		var index = 0;

		for (var i = 0; i < ctrl.options.length; i++)
			if (ctrl.options[i].value == (groupid))
				index = i;
		ctrl.selectedIndex = index;

		var grouptext = (ctrl.options[index].text);

		$('#addModal').find('.modal-body #groupid').val(groupid);
		$('#addModal').find('.modal-body #groupname').text(grouptext);
	</script>


	<script>
		function OnSelectControlChange(selectctrl) {

			var url = "<c:url value="/permission/"/>"

			url = url + "role-permission.html?roleId=" + selectctrl.value;
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



</body>
</html>
