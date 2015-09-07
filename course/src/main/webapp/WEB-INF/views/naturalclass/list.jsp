<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../shared/taglib.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//设置左侧浏览状态
	session.setAttribute("nav", "naturalclass");
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




			<div class="CourseContentHeader">自然班管理</div>

			<div class="CourseContentHeaderSeparatorLine"></div>



			<table>
				<tr>
					<td>
						<div class="input-group input-group-sm">
							<select id="SchoolSelectControl" class="form-control">
								<c:forEach var="school" items="${pagedSchool.result}">
									<option value="${school.id}">${school.name}</option>
								</c:forEach>
							</select>




						</div>
					</td>

					<td style="width: 10px;"></td>
					<td>
						<div class="input-group input-group-sm">
							<select id="departmentSelectControl" name="t_department_id"
								class="form-control input-sm">
								<c:forEach var="d" items="${pagedDepartment.result}">
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
				<c:when test="${pagedDepartmentNaturalClassViewData.totalCount >0}">


					<div class="Course-Table">


						<table class="table table-hover table-bordered"
							style="margin-bottom: -10px;">
							<thead>
								<tr>
									<th>#</th>
									<th>系别</th>
									<th>班级名称</th>
									<th>班级描述</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="index" value="1"></c:set>
								<c:forEach var="dataitem"
									items="${pagedDepartmentNaturalClassViewData.result}">
									<tr>
										<th scope="row"><input type="checkbox" value="">
											${(pagedDepartmentNaturalClassViewData.currentPageNo-1) * pagedDepartmentNaturalClassViewData.pageSize +index}</th>
										<td>${dataitem.department.name}</td>
										<td>${dataitem.naturalclass.name}</td>
										<td></td>
										<td><button type="button" class="btn btn-default btn-xs"
												onclick="onUpdate('${dataitem.naturalclass.id}')">修改...</button>
											<button type="button" class="btn btn-default btn-xs"
												onclick="onDelete('${dataitem.naturalclass.id}','${dataitem.naturalclass.name}')">删除</button></td>
									</tr>
									<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach>
							</tbody>
						</table>

						<mathtop:PageBar pageUrl="/${pagedURI}/list.html"
							pageAttrKey="pagedDepartmentNaturalClassViewData" />

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

							<a href="#" class="list-group-item" data-toggle="modal"
								data-target="#addModal">添加自然班</a> <a
								href="<c:url value="/school/list.html"/>"
								class="list-group-item">添加学院</a> <a
								href="<c:url value="/department/list.html"/>"
								class="list-group-item">添加系部</a>

						</div>

					</div>
				</c:otherwise>
			</c:choose>

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
					<h4 class="modal-title" id="myModalLabel">添加自然班</h4>
				</div>



				<form class="form-signin"
					action="<c:url value="/naturalclass/add.html"/>" method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">请输入新加自然班名称</h4>

						<div class="container-fluid" style="overflow: hidden;">
							<div class="row">
								<div class="col-md-2">学院</div>
								<div class="col-md-10">
									<select id="SchoolSelectControlAdd" class="form-control"
										name="t_school_id"
										onchange="OnSchoolSelectControlAddChange(this)">
										<c:forEach var="school" items="${pagedSchool.result}">
											<option value="${school.id}">${school.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="row">
								<div class="col-md-2">系部</div>
								<div class="col-md-10">
									<select id="DepartmentSelectControlAdd" class="form-control"
										name="t_department_id">
										<c:forEach var="d" items="${pagedDepartment.result}">
											<option value="${d.id}">${d.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>


							<div class="row">
								<div class="col-md-2">名称</div>
								<div class="col-md-10">
									<input type="text" id="inputname" class="form-control"
										name="naturalclassname" placeholder="自然班名称" required autofocus>
								</div>
							</div>

							<div class="row">
								<div class="col-md-2">描述</div>
								<div class="col-md-10">
									<input type="text" id="inputnote" class="form-control"
										name="naturalclassnote" placeholder="自然班描述">
								</div>
							</div>

						</div>


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
					<h4 class="modal-title" id="myModalLabel">修改自然班</h4>
				</div>

				<form class="form-signin"
					action="<c:url value='/${pagedURI}/update.html'/>" method="post">


					<div class="modal-body">
						<h4 class="form-signin-heading">请修改自然班属性</h4>
						<label for="inputname" class="sr-only">自然班名称</label> <input
							type="text" id="inputid" class="form-control" name="id" value=""
							placeholder="id" style="display: none;"> <input
							type="text" id="inputname" class="form-control" name="name"
							value="" placeholder="自然班名称" required autofocus> <label
							for="inputnote" class="sr-only">自然班描述</label> <input type="text"
							id="inputnote" class="form-control" name="note" value=""
							placeholder="自然班描述">

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
	<%@ include file="../../shared/dialog.jsp"%>

	<%@ include file="../../shared/pageFooter.jsp"%>

	<%@ include file="../../shared/importJs.jsp"%>


	<script>
		$(function() {

			//school
			var ctrlschool = document.getElementById("SchoolSelectControl");
			var schooledid = "${selectedt_school_id}";
			
			
			var ctrldepartment = document.getElementById("departmentSelectControl");
			var departmentid = "${selectedt_department_id}";
			
			
			var index = 0;

			if (ctrlschool.options.length > 0) {				

				for (var i = 0; i < ctrlschool.options.length; i++)
					if (ctrlschool.options[i].value == (schooledid))
						index = i;
				ctrlschool.selectedIndex = index;
				var schooltext = (ctrlschool.options[index].text);

				$('#addModal').find('.modal-body #schoolname').text(schooltext);
				$('#updateModal').find('.modal-body #schoolname').text(
						schooltext);
			}

			//department

			
			if (ctrldepartment.options.length > 0) {

				
				for (var i = 0; i < ctrldepartment.options.length; i++)
					if (ctrldepartment.options[i].value == (departmentid))
						index = i;
				ctrldepartment.selectedIndex = index;
				var departmenttext = (ctrldepartment.options[index].text);

				$('#addModal').find('.modal-body #departmentname').text(
						departmenttext);
				$('#updateModal').find('.modal-body #departmentname').text(
						departmenttext);
			}

		});
	</script>

	<script>
		//学院
		function OnDepartmentChange(t_depratment_id) {

			var url = "<c:url value='/naturalclass'/>"
					+ "/list.html?t_depratment_id=" + t_depratment_id;
			window.location.href = url;
		}

		function onSearch() {
			var st = document.getElementById("SearchText").value;

			if (st != null && st.trim().length > 0) {
				var url = "${pagedURI}/select-" + st + ".html";

				window.location.href = url;
			} else
				ShowInfoMsg("搜索内容不能为空");

		}

		function onUpdate(id) {
			var url = "location='${pagedURI}/update-" + id + ".html'";
			$('#updateModal').find('.modal-body #inputid').val(id);

			$('#updateModal').find('.modal-footer #updatebtn').attr("onclick",
					url);

			$('#updateModal').modal('show');

		}

		function onDelete(id, name) {
			var url = "location='<c:url value="/naturalclass/"/>"

			url = url + "DELETE-" + id + ".html'";

			$('#deleteModal').find('.modal-body #deleteinfo').text(name);
			$('#deleteModal').find('.modal-footer #deletebtn').attr("onclick",
					url);

			

			$('#deleteModal').modal('show');

		}
	</script>

	<script type="text/javascript">
		$(function() {
	<%//学院发生变化%>
		$("#SchoolSelectControl").change(
					function() {
						var selt_school_id = $("#SchoolSelectControl").val();
						
						var url = "<c:url value="/department/"/>"

						url = url + "selectbyschool-" + selt_school_id
								+ ".json";
						

						var departmentjson;
						$("#departmentSelectControl").empty();
						$.get(url, function(data, status) {
							if (status == "success") {

								for (var i = 0; i < data.length; i++) {
									$("#departmentSelectControl").append(
											"<option value='"+data[i].id+"'>"
													+ data[i].name
													+ "</option>");
								}
							}
						});

					});
	<%//系部发生变化%>
		$("#departmentSelectControl").change(function() {
				var t_department_id = $("#departmentSelectControl").val();
				OnDepartmentChange(t_department_id);

			});

		});
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
